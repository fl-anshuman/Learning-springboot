package org.CRUD.Learning.dto;

import lombok.Data;
import org.CRUD.Learning.annotation.MaskedField;
import org.CRUD.Learning.annotation.MaskingStrategy;

@Data
public class CompanyDTO {
    private int id;
    private String name;
    private String address;
// do not mask here otherwise response will be masked from companyService :)
    private String email;
    private double valuation;
}
