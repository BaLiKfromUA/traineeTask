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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author BaLiK on 10.04.19
 */
@Controller
public class DepartmentController {
    private static Logger LOG = LogManager.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService service;

    @Autowired
    OvalValidator validator;

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

    @GetMapping("/departments")
    public String getDepartments(@ModelAttribute("flag") String flag,
                                 @ModelAttribute("name") String invalidName,
                                 Model model) {

        if (!"".equals(flag)) {
            String invalidId = flag;

            if (!validator.validateId(invalidId)) {
                invalidId = "0";
            }

            if (invalidName != null) {
                model.addAttribute("errorMessages", validator.getErrors(new DepartmentData(invalidId, invalidName)).toArray());
            }
        }

        try {
            model.addAttribute("departments", service.getAllDepartments().toArray());
        } catch (DatabaseException e) {
            model.addAttribute("dbError", true);
            model.addAttribute("errorMessage", e.getMessage());
        }

        model.addAttribute("name", invalidName);
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
    public String createDepartment(DepartmentData data, RedirectAttributes redirectAttributes) {
        if (validator.validate(data)) {
            try {
                service.createDepartment(data);
                LOG.info("Department {} was added!", data.getName());
            } catch (DatabaseException e) {
                redirectAttributes.addFlashAttribute("dbError", true);
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            }

        } else {
            redirectAttributes.addFlashAttribute("flag", "invalid-new-department");
            redirectAttributes.addFlashAttribute("name", data.getName());
        }

        return "redirect:/departments";
    }

    //todo:redirect vs forward
    @PostMapping("/departments/edit")
    public String editDepartment(DepartmentData data, RedirectAttributes redirectAttributes) {
        if (validator.validate(data)) {
            try {
                service.updateDepartment(data);
                LOG.info("Department {} was updated!", data.getId());
            } catch (DatabaseException e) {
                redirectAttributes.addFlashAttribute("dbError", true);
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            }

        } else {
            redirectAttributes.addFlashAttribute("flag", data.getId());
            redirectAttributes.addFlashAttribute("name", data.getName());
        }

        return "redirect:/departments";
    }

}
