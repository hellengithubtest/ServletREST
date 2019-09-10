package com.servlet.app.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
/*

@Data
@Entity
@Table(name = "person")
@SecondaryTable(name = "car", pkJoinColumns = @PrimaryKeyJoinColumn(name = "CAR_OWNERID", referencedColumnName = "PERSON_ID"))
public class PersonWithCars implements Serializable {

    @Column(table = "person")
    private Long id;
    @Column(table = "person")
    private String name;
    @Column(table = "person")
    private Date birthdate;

    @OneToMany
    @JoinColumn(name = "CAR_OWNERID", table = "car", referencedColumnName = "PERSON_ID")
    private Car [] cars;
}
*/
