package com.csi.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Employee")

public class Employee {
    @Id
    @GeneratedValue

    private long empId;
    @NotNull
    @Size(min=2,message = "Employee FistName Should Be Atleast 2 Character")
    private String empFirstName;

    private String empLastName;

    private String empAddress;
    @Range(min=1000000000, max = 9999999999L, message = "Employee Contact Number must be 10 digit")
    @Column(unique = true)
    private long empContactNumber;

    private int empAge;

    private double empSalary;


    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date empDOB;

    @Email(message = "Employee EmailID Must Be Valid")
    @Column(unique = true)
    private String empEmailId;

    @Size(min = 6,message ="Employee Password Should Be Atleast 6 Character")
    private String empPassword;

}
