package com.edu.system.model.user;

import com.edu.system.model.AbstractEntity;
import com.edu.system.model.StudentGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "student")
public class Student extends AbstractEntity {

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;

    @ManyToOne
    private StudentGroup group;

    @OneToOne(optional = false)
    private User user;
}
