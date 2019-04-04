package com.aimprosoft.sandbox.controller;

import com.aimprosoft.sandbox.controller.action.Action;
import com.aimprosoft.sandbox.controller.action.ActionManager;
import com.aimprosoft.sandbox.util.database.HibernateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


/**
 * @author BaLiK on 26.03.19
 */
@Controller
//todo: annotation cheatsheet
public class MainServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(MainServlet.class);

    @Autowired
    private ActionManager actionManager;

    @Override
    public void init() throws ServletException {
        LOG.info("Main servlet init...");
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String action = Optional.ofNullable(request.getParameter("action-get")).orElse("default");
        final Action actionToDo = Optional.ofNullable(actionManager.getAction(action)).orElse(actionManager.getAction("error"));
        actionToDo.execute(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        final String action = Optional.ofNullable(request.getParameter("action-post")).orElse("error");
        final Action actionToDo = Optional.ofNullable(actionManager.getAction(action)).orElse(actionManager.getAction("error"));
        actionToDo.execute(request, response);
    }

    @Override
    public void destroy() {
        LOG.info("Main servlet destroy...");
        HibernateUtil.shutdown();
    }
}
