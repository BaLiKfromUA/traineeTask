package com.aimprosoft.sandbox.controller.mvc;

import com.aimprosoft.sandbox.exception.DatabaseException;
import com.aimprosoft.sandbox.service.EmployeeService;
import com.aimprosoft.sandbox.util.validator.OvalValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author BaLiK on 11.04.19
 */
@Controller
public class EmployeeController {
    private static Logger LOG = LogManager.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService service;

    @Autowired
    OvalValidator validator;

    @GetMapping("/employees")
    public String getEmployees(@RequestParam(value = "department_id", required = false) String departmentId, Model model) {

        if (validator.validateId(departmentId)) {
            try {
                model.addAttribute("employees", service.getAllByDepartmentId(departmentId).toArray());
            } catch (DatabaseException e) {
                model.addAttribute("dbError", true);
                model.addAttribute("errorMessage", e.getMessage());
            }

        } else {
            model.addAttribute("reason", "invalid");
            model.addAttribute("errorMessages", new String[]{("Invalid department id!")});
        }

        return "employeesPage";
    }


}
