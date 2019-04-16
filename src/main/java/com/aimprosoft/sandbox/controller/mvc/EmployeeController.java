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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @author BaLiK on 11.04.19
 */
@Controller
//todo:show db errors
public class EmployeeController {
    private static Logger LOG = LogManager.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService service;

    @Autowired
    OvalValidator validator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @ModelAttribute("employeeModel")
    public EmployeeData initEmployeeModel() {
        return new EmployeeData();
    }

    @ModelAttribute("newEmployeeModel")
    public EmployeeData initNewEmployeeModel() {
        return new EmployeeData();
    }

    @GetMapping("/employees")
    public String getEmployees(@RequestParam(value = "department_id") String departmentId,
                               @ModelAttribute(value = "flag") String flag,
                               @ModelAttribute("employeeModel") EmployeeData data,
                               Model model) {

        if (validator.validateId(departmentId)) {
            data.setDepartmentId(departmentId);

            if (model.asMap().containsKey("validateResult")) {
                if ("invalid-new-employee".equals(flag)) {
                    model.addAttribute("org.springframework.validation.BindingResult.newEmployeeModel",
                            model.asMap().get("validateResult"));
                } else {
                    model.addAttribute("org.springframework.validation.BindingResult.employeeModel",
                            model.asMap().get("validateResult"));
                }
            }

            model.addAttribute("login", data.getLogin());
            model.addAttribute("email", data.getEmail());
            model.addAttribute("rank", data.getRank());
            model.addAttribute("date", data.getDate());
            model.addAttribute("flag", flag);


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
    public String createEmployee(@Valid @ModelAttribute("newEmployeeModel") EmployeeData data,
                                 BindingResult validateResult,
                                 RedirectAttributes redirectAttributes) {
        if (!validateResult.hasErrors()) {
            try {
                service.createEmployee(data);
                LOG.info("Employee {} was added!", data.getLogin());
            } catch (DatabaseException e) {
                redirectAttributes.addFlashAttribute("dbError", true);
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            }

        } else {
            redirectAttributes.addFlashAttribute("validateResult", validateResult);
            redirectAttributes.addFlashAttribute("employeeModel", data);
            redirectAttributes.addFlashAttribute("flag", "invalid-new-employee");
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
    public String editEmployee(@Valid @ModelAttribute("employeeModel") EmployeeData data,
                               BindingResult validateResult,
                               RedirectAttributes redirectAttributes) {

        if (!validateResult.hasErrors()) {
            try {
                service.updateEmployee(data);
                LOG.info("Employee {} was updated!", data.getId());
            } catch (DatabaseException e) {
                redirectAttributes.addFlashAttribute("dbError", true);
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            }
        } else {
            redirectAttributes.addFlashAttribute("validateResult", validateResult);
            redirectAttributes.addFlashAttribute("employeeModel", data);
            redirectAttributes.addFlashAttribute("flag", data.getId());
        }

        return String.format("redirect:/employees?department_id=%s", data.getDepartmentId());
    }
}
