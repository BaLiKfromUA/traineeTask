package com.aimprosoft.sandbox.controller.mvc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @author BaLiK on 12.04.19
 */
@ControllerAdvice
public class AdviceController {
    private static Logger LOG = LogManager.getLogger(DepartmentController.class);

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle(Exception ex) {
        LOG.error("Exception in url controllers:\n{}", ex.getMessage());
        return "redirect:/404";
    }
}
