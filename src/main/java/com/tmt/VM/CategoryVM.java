package com.tmt.VM;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CategoryVM {

    @NotBlank(message = "categoryName cannot blank")
    private String categoryName;
}
