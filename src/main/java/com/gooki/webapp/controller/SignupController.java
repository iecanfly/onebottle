package com.gooki.webapp.controller;

import com.gooki.Constants;
import com.gooki.model.User;
import com.gooki.service.RoleManager;
import com.gooki.service.UserExistsException;
import com.gooki.webapp.util.location.LocationUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * Controller to signup new users.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
@Controller
@RequestMapping("/signup*")
public class SignupController extends BaseFormController {
    private RoleManager roleManager;

    @Autowired
    public void setRoleManager(final RoleManager roleManager) {
        this.roleManager = roleManager;
    }

    public SignupController() {
        setCancelView("redirect:login");
        setSuccessView("redirect:home");
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showForm(@ModelAttribute User user, final HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView();

        String location = StringUtils.isEmpty(request.getParameter("location")) ? LocationUtil.DEFAULT_LOCATION : request.getParameter("location");
        LocationUtil.GeocoderSearchResponse.Result.AddressComponent ac = LocationUtil.getBaiduAddressComponent(location);
        user.getAddress().setProvince(LocationUtil.getProvinceCode(ac));
        user.getAddress().setCity(LocationUtil.getCityCode(ac));
        user.getAddress().setArea(LocationUtil.getAreaCode(ac));

        mv.addObject("provinceList", LocationUtil.getDefaultProvinceMap());
        mv.addObject("cityList", LocationUtil.getAddressMap(user.getAddress().getProvince()));
        mv.addObject("areaList", LocationUtil.getAddressMap(user.getAddress().getCity()));
        mv.addObject("user", user);

        return mv;
    }


    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(final User user, final BindingResult errors, final HttpServletRequest request, final HttpServletResponse response)
            throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(user, errors);

            if (StringUtils.isBlank(user.getPassword())) {
                errors.rejectValue("password", "errors.required", new Object[]{getText("user.password", request.getLocale())},
                        "Password is a required field.");
            }
            if (StringUtils.isBlank(user.getAddress().getAddress())) {
                errors.rejectValue("address.address", "errors.required", new Object[]{getText("user.address.address", request.getLocale())},
                        "Address is a required field.");
            }

            if (errors.hasErrors()) {
                putAddressParamForUser(user, request);
                return "signup";
            }
        }

        final Locale locale = request.getLocale();

        user.setEnabled(true);

        // Set the default user role on this new user
        user.addRole(roleManager.getRole(Constants.USER_ROLE));

        // unencrypted users password to log in user automatically
        final String password = user.getPassword();

        try {
            this.getUserManager().saveUser(user);
        } catch (final AccessDeniedException ade) {
            // thrown by UserSecurityAdvice configured in aop:advisor userManagerSecurity
            log.warn(ade.getMessage());
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return null;
        } catch (final UserExistsException e) {
            errors.rejectValue("username", "errors.existing.user",
                    new Object[]{user.getUsername()}, "duplicate user");
            putAddressParamForUser(user, request);
            return "signup";
        }

        saveMessage(request, getText("user.registered", user.getUsername(), locale));
        request.getSession().setAttribute(Constants.REGISTERED, Boolean.TRUE);

        // log user in automatically
        final UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                user.getUsername(), password, user.getAuthorities());
        auth.setDetails(user);
        SecurityContextHolder.getContext().setAuthentication(auth);


        return getSuccessView();
    }

    protected void putAddressParamForUser(User user, HttpServletRequest request) throws Exception {
        request.setAttribute("provinceList", LocationUtil.getDefaultProvinceMap());
        request.setAttribute("cityList", user.getAddress().getProvince() == null ? LocationUtil.getDefaultCityMap() : LocationUtil.getAddressMap(user.getAddress().getProvince()));
        request.setAttribute("areaList", user.getAddress().getCity() == null ? LocationUtil.getDefaultAreaMap() : LocationUtil.getAddressMap(user.getAddress().getCity()));
    }

}