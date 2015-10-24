package com.gooki.dao.hibernate;

import com.gooki.dao.UserDao;
import com.gooki.model.Address;
import com.gooki.model.FavOrder;
import com.gooki.model.User;
import org.displaytag.pagination.Pagination;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * This class interacts with Hibernate session to save/delete and
 * retrieve User objects.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 *   Modified by <a href="mailto:dan@getrolling.com">Dan Kibler</a>
 *   Extended to implement Acegi UserDetailsService interface by David Carter david@carter.net
 *   Modified by <a href="mailto:bwnoll@gmail.com">Bryan Noll</a> to work with
 *   the new BaseDaoHibernate implementation that uses generics.
 *   Modified by jgarcia (updated to hibernate 4)
*/
@Repository("userDao")
public class UserDaoHibernate extends GenericDaoHibernate<User, Long> implements UserDao, UserDetailsService {

    /**
     * Constructor that sets the entity to User.class.
     */
    public UserDaoHibernate() {
        super(User.class);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<User> getUsers() {
        Query qry = getSession().createQuery("from User u order by upper(u.username)");
        return qry.list();
    }

    /**
     * {@inheritDoc}
     */
    public User saveUser(User user) {
        if (log.isDebugEnabled()) {
            log.debug("user's id: " + user.getId());
        }

        FavOrder buyerFavOrder = user.getBuyerFavOrder();

        if(buyerFavOrder != null && buyerFavOrder.getBuyer() == null) {
            user.setBuyerFavOrder(null);
            getSession().delete(buyerFavOrder);
        }
        getSession().saveOrUpdate(user);
        // necessary to throw a DataIntegrityViolation and catch it in UserManager
        getSession().flush();
        return user;
    }

    /**
     * Overridden simply to call the saveUser method. This is happening
     * because saveUser flushes the session and saveObject of BaseDaoHibernate
     * does not.
     *
     * @param user the user to save
     * @return the modified user (with a primary key set if they're new)
     */
    @Override
    public User save(User user) {
        return this.saveUser(user);
    }

    /**
     * {@inheritDoc}
    */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List users = getSession().createCriteria(User.class).add(Restrictions.eq("username", username)).list();
        if (users == null || users.isEmpty()) {
            throw new UsernameNotFoundException("user '" + username + "' not found...");
        } else {
            return (UserDetails) users.get(0);
        }
    }

    /**
     * {@inheritDoc}
    */
    public String getUserPassword(Long userId) {
        JdbcTemplate jdbcTemplate =
                new JdbcTemplate(SessionFactoryUtils.getDataSource(getSessionFactory()));
        Table table = AnnotationUtils.findAnnotation(User.class, Table.class);
        return jdbcTemplate.queryForObject(
                "select password from " + table.name() + " where id=?", String.class, userId);
    }

    @Override
    public List<User> findSellersByAddress(Address address, int page) {
        Query q = getSession().createQuery("select distinct u " +
                "from User u join u.items as i " +
                "where u.address.province = :province " +
                "and u.address.city = :city " +
                "and u.address.area = :area " +
                "and u.isBuyer = :isBuyer " +
                "order by u.id desc");

        q.setParameter("province", address.getProvince());
        q.setParameter("city", address.getCity());
        q.setParameter("area", address.getArea());
        q.setParameter("isBuyer", false);
        q.setFirstResult((page - 1) * 5);
        q.setMaxResults(6);

//        Criteria criteria = getSession().createCriteria(User.class);
//        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//        criteria.add(Restrictions.eq("isBuyer", false));
//        criteria.add(Restrictions.eq("address.province", address.getProvince()));
//        criteria.add(Restrictions.eq("address.city", address.getCity()));
//        criteria.add(Restrictions.eq("address.area", address.getArea()));
//        criteria.addOrder(Order.asc("id"));
//        criteria.setFirstResult((page - 1) * 5);
//        criteria.setMaxResults(55);

        return (List<User>) q.list();
    }
}
