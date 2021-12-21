package examples.model;


import java.util.Date;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import jakarta.validation.constraints.Past;

@Embeddable
public class EmployeeInfo {
    @Past
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Embedded
    private PersonInfo spouse;
}