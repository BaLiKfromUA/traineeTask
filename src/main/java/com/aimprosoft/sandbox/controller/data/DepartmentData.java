package com.aimprosoft.sandbox.controller.data;

import com.aimprosoft.sandbox.util.validator.DataPatterns;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.MinLength;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;

/**
 * @author BaLiK on 29.03.19
 */
public class DepartmentData {

    @NotNull
    @NotEmpty
    @MinLength(value = 1)
    @Length(max = 3)
    @javax.validation.constraints.Pattern(regexp = DataPatterns.ID_PATTERN)
    private final String id;

    @NotNull
    @NotEmpty
    @MinLength(value = 6)
    @Length(max = 20)
    @javax.validation.constraints.Pattern(regexp = DataPatterns.NAME_PATTERN)
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
