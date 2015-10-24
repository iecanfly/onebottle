package com.gooki.dao.hibernate;

import com.gooki.dao.GenericDao;
import com.gooki.dao.ItemDAO;
import com.gooki.model.Item;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 * Created by iecanfly on 5/16/14.
 */
@Repository("itemDAO")
public class ItemDAOHibernate extends GenericDaoHibernate<Item, Long> implements ItemDAO {
    public ItemDAOHibernate() {
        super(Item.class);
    }

}
