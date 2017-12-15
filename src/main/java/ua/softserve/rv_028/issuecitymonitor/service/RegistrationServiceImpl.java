package ua.softserve.rv_028.issuecitymonitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.softserve.rv_028.issuecitymonitor.dao.UserDao;
import ua.softserve.rv_028.issuecitymonitor.dto.UserDto;
import ua.softserve.rv_028.issuecitymonitor.entity.User;

@Service
public class RegistrationServiceImpl implements RegistrationService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private EmailService emailService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public boolean isUserExist(String email) {
        User user = userDao.findUserByUsername(email);
        return user != null && user.getUsername().equals(email);
    }

    @Override
    public void registrationUser(UserDto dto) {
        try {
            userDao.save(new User(dto.getFirstName(), dto.getLastName(), dto.getEmail(),
                    passwordEncoder.encode(dto.getPassword())));
            emailService.sendGreetingEmail(dto.getEmail(), dto.getFirstName(), dto.getLastName());
        } catch (RuntimeException e){
            throw new IllegalArgumentException("Registration Failed");
        }
    }
}
