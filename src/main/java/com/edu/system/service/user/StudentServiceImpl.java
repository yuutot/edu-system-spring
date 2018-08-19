package com.edu.system.service.user;

import com.edu.system.model.user.Student;
import com.edu.system.model.user.User;
import com.edu.system.repository.StudentRepository;
import com.edu.system.service.GenericServiceImpl;
import com.edu.system.service.group.StudentGroupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentServiceImpl extends GenericServiceImpl<Student, StudentRepository> implements StudentService {

    private final StudentGroupService studentGroupService;

    public StudentServiceImpl(StudentRepository dao, StudentGroupService studentGroupService) {
        super(dao);
        this.studentGroupService = studentGroupService;
    }

    @Override
    @Transactional
    public Student create(String name, String surname, Integer groupId, User user) {

        Student student = new Student();
        student.setName(name);
        student.setSurname(surname);
        student.setGroup(studentGroupService.getById(groupId));
        student.setUser(user);
        return getDao().save(student);
    }
}
