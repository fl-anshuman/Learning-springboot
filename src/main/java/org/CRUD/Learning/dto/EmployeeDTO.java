package org.CRUD.Learning.dto;

import org.CRUD.Learning.annotation.MaskedField;
import org.CRUD.Learning.annotation.MaskingStrategy;
import lombok.Data;

@Data
public class EmployeeDTO {
    private int id;
    private String name;

    @MaskedField(strategy = MaskingStrategy.EMAIL)
    private String email;

    @MaskedField(strategy = MaskingStrategy.PHONE)
    private String phone;
}
