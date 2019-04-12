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
                                   Model model) {
        if (validator.validateId(id)) {
            try {
                service.deleteDepartmentById(id);
            } catch (DatabaseException e) {
                model.addAttribute("dbError", true);
                model.addAttribute("errorMessage", e.getMessage());
            }

            LOG.info("Department {} was removed!", id);

            return "redirect:/departments";
        }

        return "redirect:/errors";
    }

    @PostMapping("/departments/add")
    public String createDepartment(DepartmentData data, Model model) {
        if (validator.validate(data)) {
            try {
                service.createDepartment(data);
                LOG.info("Department {} was added!", data.getName());
            } catch (DatabaseException e) {
                model.addAttribute("dbError", true);
                model.addAttribute("errorMessage", e.getMessage());
            }

        } else {
            model.addAttribute("flag", "invalid-new-department");
            model.addAttribute("name", data.getName());
        }

        return "redirect:/departments";
    }

    //todo:redirect vs forward
    @PostMapping("/departments/edit")
    public String editDepartment(DepartmentData data, Model model) {
        if (validator.validate(data)) {
            try {
                service.updateDepartment(data);
                LOG.info("Department {} was updated!", data.getId());
            } catch (DatabaseException e) {
                model.addAttribute("dbError", true);
                model.addAttribute("errorMessage", e.getMessage());
            }

        } else {
            model.addAttribute("flag", data.getId());
            model.addAttribute("name", data.getName());
        }

        return "redirect:/departments";
    }

}
