package com.aimprosoft.sandbox.controller.data;

import com.aimprosoft.sandbox.util.validator.DataPatterns;
import com.aimprosoft.sandbox.util.validator.annotation.department.UniqueDepartment;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.MinLength;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;

/**
 * @author BaLiK on 29.03.19
 */
@UniqueDepartment
public class DepartmentData {

    @NotNull(message = "Id can not be null")
    @NotEmpty(message = "Id can not be empty")
    @MinLength(value = 1, message = "Id length should be >= 1")
    @javax.validation.constraints.Pattern(regexp = DataPatterns.ID_PATTERN,
            message = "Id should be a number >= 0")
    private final String id;

    @NotNull(message = "Name can not be null")
    @NotEmpty(message = "Name can not be empty")
    @MinLength(value = 6, message = "Name length should be >= 6")
    @Length(max = 20, message = "Name length should be <=20")
    @javax.validation.constraints.Pattern(regexp = DataPatterns.NAME_PATTERN,
            message = "Name must contain ONLY letters of the English alphabet and begin with a capital letter.")
    private final String name;

    public DepartmentData(String name) {
        this.id = "0";
        this.name = name;
    }

    public DepartmentData(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
