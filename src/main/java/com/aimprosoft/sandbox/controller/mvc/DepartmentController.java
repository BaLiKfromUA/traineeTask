package com.aimprosoft.sandbox.controller.mvc;

import com.aimprosoft.sandbox.controller.data.DepartmentData;
import com.aimprosoft.sandbox.exception.DatabaseException;
import com.aimprosoft.sandbox.service.DepartmentService;
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
 * @author BaLiK on 10.04.19
 */
@Controller
public class DepartmentController {
    private static Logger LOG = LogManager.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService service;

    @Autowired
    private OvalValidator validator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @GetMapping("/")
    public String redirect() {
        return "redirect:/departments";
    }

    @GetMapping("/404")
    public String NotFoundPage() {
        return "errorPage";
    }

    @ModelAttribute("departmentModel")
    public DepartmentData initDepartmentModel() {
        return new DepartmentData();
    }

    @ModelAttribute("newDepartmentModel")
    public DepartmentData initNewDepartmentModel() {
        return new DepartmentData();
    }

    @GetMapping("/departments")
    public String getDepartments(@ModelAttribute("flag") String flag,
                                 @ModelAttribute("name") String invalidName,
                                 Model model) {

        if (model.asMap().containsKey("validateResult")) {
            if ("invalid-new-department".equals(flag)) {
                model.addAttribute("org.springframework.validation.BindingResult.newDepartmentModel",
                        model.asMap().get("validateResult"));
            } else {
                model.addAttribute("org.springframework.validation.BindingResult.departmentModel",
                        model.asMap().get("validateResult"));
            }
        }

        try {
            model.addAttribute("departments", service.getAllDepartments().toArray());
        } catch (DatabaseException e) {
            model.addAttribute("dbError", true);
            model.addAttribute("errorMessage", e.getMessage());
        }

        model.addAttribute("incorrect_name", invalidName);
        model.addAttribute("flag", flag);

        return "departmentsPage";
    }

    @PostMapping("/departments/delete")
    public String deleteDepartment(@RequestParam("id") String id,
                                   RedirectAttributes redirectAttributes) {
        if (validator.validateId(id)) {
            try {
                service.deleteDepartmentById(id);
            } catch (DatabaseException e) {
                redirectAttributes.addFlashAttribute("dbError", true);
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            }

            LOG.info("Department {} was removed!", id);

            return "redirect:/departments";
        }

        return "redirect:/404";
    }

    @PostMapping("/departments/add")
    public String createDepartment(@Valid @ModelAttribute("newDepartmentModel") DepartmentData data,
                                   BindingResult validateResult,
                                   RedirectAttributes redirectAttributes) {
        if (!validateResult.hasErrors()) {
            try {
                service.createDepartment(data);
                LOG.info("Department {} was added!", data.getName());
            } catch (DatabaseException e) {
                redirectAttributes.addFlashAttribute("dbError", true);
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            }

        } else {
            redirectAttributes.addFlashAttribute("validateResult", validateResult);
            redirectAttributes.addFlashAttribute("flag", "invalid-new-department");
            redirectAttributes.addFlashAttribute("name", data.getName());
        }

        return "redirect:/departments";
    }

    @PostMapping("/departments/edit")
    public String editDepartment(@Valid @ModelAttribute("departmentModel") DepartmentData data,
                                 BindingResult validateResult,
                                 RedirectAttributes redirectAttributes) {
        if (!validateResult.hasErrors()) {
            try {
                service.updateDepartment(data);
                LOG.info("Department {} was updated!", data.getId());
            } catch (DatabaseException e) {
                redirectAttributes.addFlashAttribute("dbError", true);
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            }

        } else {
            redirectAttributes.addFlashAttribute("validateResult", validateResult);
            redirectAttributes.addFlashAttribute("flag", data.getId());
            redirectAttributes.addFlashAttribute("name", data.getName());
        }

        return "redirect:/departments";
    }

}
