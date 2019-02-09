package com.edu.system.controller.group;

import com.edu.system.model.StudentGroup;
import com.edu.system.model.user.UserRole;
import com.edu.system.security.CheckCsrf;
import com.edu.system.security.RolesAllowed;
import com.edu.system.service.group.StudentGroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("groups")
public class StudentGroupController {

    private final StudentGroupService studentGroupService;

    public StudentGroupController(StudentGroupService studentGroupService) {
        this.studentGroupService = studentGroupService;
    }

    @CheckCsrf
    @RolesAllowed({UserRole.ADMIN})
    @PostMapping
    public StudentGroup create(@RequestBody StudentGroup studentGroup) {
        return studentGroupService.save(studentGroup);
    }

    @GetMapping
    @RolesAllowed({UserRole.STUDENT, UserRole.TEACHER, UserRole.ADMIN})
    public List<StudentGroup> getAll() {
        return studentGroupService.getAll();
    }

    @DeleteMapping("{id}")
    @RolesAllowed({UserRole.ADMIN})
    public void delete(@PathVariable("id") Integer id) {
        studentGroupService.deleteById(id);
    }

    @PutMapping
    @RolesAllowed({UserRole.ADMIN})
    @CheckCsrf
    public StudentGroup update(@RequestBody StudentGroup studentGroup) {
        return studentGroupService.update(studentGroup);
    }
}
