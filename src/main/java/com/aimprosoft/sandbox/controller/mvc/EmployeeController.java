package com.aimprosoft.sandbox.controller.mvc;

import com.aimprosoft.sandbox.controller.data.EmployeeData;
import com.aimprosoft.sandbox.exception.DatabaseException;
import com.aimprosoft.sandbox.service.EmployeeService;
import com.aimprosoft.sandbox.util.validator.OvalValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author BaLiK on 11.04.19
 */
@Controller
//todo:show db errors
//todo:@validate
public class EmployeeController {
    private static Logger LOG = LogManager.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService service;

    @Autowired
    OvalValidator validator;


    @ModelAttribute("employeeModel")
    public EmployeeData initEmployeeModel() {
        return new EmployeeData();
    }


    @GetMapping("/employees")
    public String getEmployees(@RequestParam(value = "department_id") String departmentId,
                               @ModelAttribute(value = "reason") String reason,
                               @ModelAttribute(value = "flag") String flag,
                               @ModelAttribute("employeeModel") EmployeeData data,
                               Model model) {

        if (validator.validateId(departmentId)) {
            data.setDepartmentId(departmentId);

            if ("invalid".equals(reason)) {
                model.addAttribute("errorMessages", validator.getErrors(data).toArray());
            }

            model.addAttribute("login", data.getLogin());
            model.addAttribute("email", data.getEmail());
            model.addAttribute("rank", data.getRank());
            model.addAttribute("date", data.getDate());
            model.addAttribute("flag", flag);
            model.addAttribute("reason", reason);


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

    @PostMapping("/employees/add")
    public String createEmployee(@ModelAttribute("employeeModel") EmployeeData data,
                                 RedirectAttributes redirectAttributes) {
        if (validator.validate(data)) {
            try {
                service.createEmployee(data);
                LOG.info("Employee {} was added!", data.getLogin());
            } catch (DatabaseException e) {
                redirectAttributes.addFlashAttribute("dbError", true);
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            }

        } else {
            redirectAttributes.addFlashAttribute("employeeModel", data);
            redirectAttributes.addFlashAttribute("flag", "invalid-new-employee");
            redirectAttributes.addFlashAttribute("reason", "invalid");
        }

        return String.format("redirect:/employees?department_id=%s", data.getDepartmentId());
    }

    @PostMapping("/employees/delete")
    public String deleteEmployee(@RequestParam("id") String id,
                                 @RequestParam(value = "department_id") String departmentId,
                                 RedirectAttributes redirectAttributes) {

        if (validator.validateId(id) && validator.validateId(departmentId)) {
            try {
                service.deleteEmployeeById(id);
            } catch (DatabaseException e) {
                redirectAttributes.addFlashAttribute("dbError", true);
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            }

            LOG.info("Employee {} was removed!", id);

            return String.format("redirect:/employees?department_id=%s", departmentId);
        } else {
            return "redirect:/404";
        }
    }

    @PostMapping("/employees/edit")
    public String editEmployee(@ModelAttribute("employeeModel") EmployeeData data,
                               RedirectAttributes redirectAttributes) {

        if (validator.validate(data)) {
            try {
                service.updateEmployee(data);
                LOG.info("Employee {} was updated!", data.getId());
            } catch (DatabaseException e) {
                redirectAttributes.addFlashAttribute("dbError", true);
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            }
        } else {
            redirectAttributes.addFlashAttribute("employeeModel", data);
            redirectAttributes.addFlashAttribute("flag", data.getId());
            redirectAttributes.addFlashAttribute("reason", "invalid");
        }

        return String.format("redirect:/employees?department_id=%s", data.getDepartmentId());
    }
}
