package com.aimprosoft.sandbox.controller;

import com.aimprosoft.sandbox.controller.action.Action;
import com.aimprosoft.sandbox.controller.action.ActionManager;
import com.aimprosoft.sandbox.util.database.HibernateUtil;
import com.aimprosoft.sandbox.util.service.DatabaseService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * @author BaLiK on 26.03.19
 */
@WebServlet("/")
//todo:new logger version
//todo:NO HIBERNATE LOGS!
public class MainServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(MainServlet.class);

    private static ActionManager actionManager = null;

    @Override
    public void init() {
        LOG.info("Main servlet init...");
        actionManager = new ActionManager();
        DatabaseService.getInstance();
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
