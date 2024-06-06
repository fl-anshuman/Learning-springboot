package org.CRUD.Learning.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Employee {
    @Id
    private  int id ;
    private String name;
    private String email;
    private String phone;
    // checking
}