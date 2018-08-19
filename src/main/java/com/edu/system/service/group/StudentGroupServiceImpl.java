package com.edu.system.service.group;

import com.edu.system.model.StudentGroup;
import com.edu.system.repository.StudentGroupRepository;
import com.edu.system.service.GenericServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class StudentGroupServiceImpl extends GenericServiceImpl<StudentGroup, StudentGroupRepository> implements StudentGroupService {

    public StudentGroupServiceImpl(StudentGroupRepository dao) {
        super(dao);
    }

    @Override
    public StudentGroup create(String name) {
        StudentGroup studentGroup = new StudentGroup();
        studentGroup.setName(name);
        return getDao().save(studentGroup);
    }
}
