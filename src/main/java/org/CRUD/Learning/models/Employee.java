package org.CRUD.Learning.models;
import lombok.Data;
import org.CRUD.Learning.annotation.EncryptedField;
import org.CRUD.Learning.annotation.MaskedField;
import org.CRUD.Learning.annotation.MaskingStrategy;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
// @Document annotation is used to define the name of the collection in which the data will be stored.
// @Data annotation is used to generate getters and setters for all fields, toString method, and hashCode and equals implementations.
// @NoArgsConstructor annotation is used to generate a constructor with no parameters.
// @AllArgsConstructor annotation is used to generate a constructor with all parameters.
// @Id annotation is used to define the primary key.

@Document
@Data
public class Employee {
    @Id
    private  int id ;
    private String name;
    @EncryptedField
//    @MaskedField(strategy = MaskingStrategy.EMAIL)
    private String email;
    private String phone;
    // checking
}
//can we manually stop a bean creation
//yes we can stop a bean creation by using @Conditional annotation
//what is the use of @Conditional annotation
//The @Conditional annotation is used to indicate that a component is only eligible for registration when all specified conditions are met.