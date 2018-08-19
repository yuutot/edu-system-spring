package com.edu.system.service.user;

import com.edu.system.controller.user.password.ChangePasswordRequest;
import com.edu.system.controller.user.password.CreatePasswordRequest;
import com.edu.system.controller.user.login.LoginRequest;
import com.edu.system.controller.user.login.LoginResult;
import com.edu.system.controller.user.register.RegisterRequest;
import com.edu.system.controller.user.register.RegisterResponse;
import com.edu.system.exception.NotFoundException;
import com.edu.system.exception.SecurityException;
import com.edu.system.exception.ServiceException;
import com.edu.system.model.user.Student;
import com.edu.system.model.user.User;
import com.edu.system.model.user.UserRole;
import com.edu.system.model.user.UserSessionInfo;
import com.edu.system.repository.UserRepository;
import com.edu.system.service.GenericServiceImpl;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserServiceImpl extends GenericServiceImpl<User, UserRepository> implements UserService {

    private final UserSessionInfo userSessionInfo;
    private final StudentService studentService;

    public UserServiceImpl(UserRepository dao, UserSessionInfo userSessionInfo, StudentService studentService) {
        super(dao);
        this.userSessionInfo = userSessionInfo;
        this.studentService = studentService;
    }

    @Override
    public LoginResult login(LoginRequest request) {
        User user = getDao().findByLogin(request.getLogin()).orElseThrow(() -> new NotFoundException("Invalid login or password"));
        String password = DigestUtils.md5Hex(request.getPassword());
        if (!user.getPassword().equals(password)) {
            throw new NotFoundException("Invalid login or password");
        }

        System.out.println(userSessionInfo.getLogin());

        String csrf = UUID.randomUUID().toString();

        fillUserSession(user, csrf);

        return new LoginResult(user.getLogin(), csrf, user.getUserRole());
    }

    @Override
    @Transactional
    public RegisterResponse register(RegisterRequest request) {
        if (request.getRole() == UserRole.ADMIN) {
            throw new ServiceException("Admin role is now allowed");
        }
        getDao().findByLogin(request.getLogin()).ifPresent(user -> {
            throw new SecurityException("User " + user.getLogin() + " already exists");
        });

        String accessCode = UUID.randomUUID().toString();

        User user = new User();
        user.setLogin(request.getLogin());
        user.setUserRole(request.getRole());
        user.setAccessCode(accessCode);
        user.setEmail(request.getEmail());
        getDao().save(user);

        Student student = studentService.create(request.getName(), request.getSurname(), request.getGroupId(), user);

        return RegisterResponse
                .builder()
                .login(user.getLogin())
                .email(user.getEmail())
                .name(student.getName())
                .surname(student.getSurname())
                .group(student.getGroup())
                .role(user.getUserRole())
                .accessCode(user.getAccessCode())
                .build();
    }

    @Override
    @Transactional
    public LoginResult createPassword(CreatePasswordRequest request) {
        User user = getDao().findByAccessCodeAndPasswordIsNull(request.getAccessCode()).orElseThrow(() -> new NotFoundException("Invalid access code"));
        user.setPassword(DigestUtils.md5Hex(request.getPassword()));
        user.setAccessCode(null);
        getDao().save(user);

        String csrf = UUID.randomUUID().toString();

        fillUserSession(user, csrf);

        return new LoginResult(user.getLogin(), csrf, user.getUserRole());
    }

    @Override
    @Transactional
    public void changePassword(ChangePasswordRequest request) {
        User user = getDao().findByLogin(userSessionInfo.getLogin()).orElseThrow(() -> new NotFoundException("Can't find user in database"));

        if (!user.getPassword().equals(DigestUtils.md5Hex(request.getOld()))) {
            throw new SecurityException("Invalid old password");
        }

        user.setPassword(DigestUtils.md5Hex(request.getPassword()));
        getDao().save(user);
        userSessionInfo.invalidate();
    }

    private void fillUserSession(User user, String csrf) {
        userSessionInfo.setLogin(user.getLogin());
        userSessionInfo.setPassword(user.getPassword());
        userSessionInfo.setRole(user.getUserRole());
        userSessionInfo.setCsrf(csrf);
    }
}
