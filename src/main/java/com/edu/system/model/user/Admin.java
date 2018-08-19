package com.edu.system.model.user;

import com.edu.system.model.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "admin")
public class Admin extends AbstractEntity {

    private String name;
    private String surname;

    @OneToOne(optional = false)
    private User user;
}
