package com.edu.system.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "student_group")
public class StudentGroup extends AbstractEntity {

    @Column(unique = true)
    private String name;

    private String faculty;
}
