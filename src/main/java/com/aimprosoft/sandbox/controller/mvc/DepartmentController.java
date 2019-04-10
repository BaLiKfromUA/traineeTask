package com.aimprosoft.sandbox.controller.mvc;

import com.aimprosoft.sandbox.exception.DatabaseException;
import com.aimprosoft.sandbox.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author BaLiK on 10.04.19
 */
@Controller
public class DepartmentController {
    @Autowired
    private DepartmentService service;

    @GetMapping("/departments")
    public String getDepartments(Model model) {
        try {
            model.addAttribute("departments", service.getAllDepartments().toArray());
        } catch (DatabaseException e) {
            model.addAttribute("dbError", true);
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "departmentsPage";
    }
}
