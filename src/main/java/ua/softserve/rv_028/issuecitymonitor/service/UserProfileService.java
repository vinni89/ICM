package ua.softserve.rv_028.issuecitymonitor.service;

public interface UserProfileService {
    void updatePassword(String email, String oldPassword, String newPassword);
    void updateContactsInfo(String email, String fistName, String lastName, String phone);
}
