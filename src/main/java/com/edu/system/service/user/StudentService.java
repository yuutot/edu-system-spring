package com.edu.system.service.user;

import com.edu.system.model.user.Student;
import com.edu.system.model.user.User;
import com.edu.system.service.GenericService;

public interface StudentService extends GenericService<Student> {
    Student create(String name, String surname, Integer groupId, User user);
}
