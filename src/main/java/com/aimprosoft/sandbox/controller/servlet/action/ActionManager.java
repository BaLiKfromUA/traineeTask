package com.aimprosoft.sandbox.controller.servlet.action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author BaLiK on 27.03.19
 */
@Component
public class ActionManager {
    private static final Logger LOG = LogManager.getLogger(ActionManager.class);
    private Map<String, Action> actions;

    public ActionManager() {

    }

    @Required
    public void setActions(Map<String, Action> actions) {
        this.actions = actions;
        LOG.info("Action map injected!");
    }

    public Action getAction(String tag) {
        return actions.get(tag);
    }
}
