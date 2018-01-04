package ua.softserve.rv_028.issuecitymonitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.softserve.rv_028.issuecitymonitor.dao.UserDao;
import ua.softserve.rv_028.issuecitymonitor.dto.*;
import ua.softserve.rv_028.issuecitymonitor.entity.Event;
import ua.softserve.rv_028.issuecitymonitor.entity.Issue;
import ua.softserve.rv_028.issuecitymonitor.entity.User;
import ua.softserve.rv_028.issuecitymonitor.entity.Petition;

@Service
public class MapperService {

    UserDao userDao;

    @Autowired
    public MapperService (UserDao userDao){
        this.userDao = userDao;
    }

    public UserDto fromEntityToDto(User userEntity){
        UserDto dto = new UserDto();

        dto.setId(userEntity.getId());
        dto.setUserRole(userEntity.getUserRole());
        dto.setRegistrationDate(userEntity.getRegistrationDate());
        dto.setFirstName(userEntity.getFirstName());
        dto.setLastName(userEntity.getLastName());
        dto.setPassword(userEntity.getPassword());
        dto.setEmail(userEntity.getUsername());
        dto.setPhone(userEntity.getPhone());
        dto.setUserAgreement(userEntity.isUserAgreement());
        dto.setUserStatus(userEntity.getUserStatus());
        dto.setDeleteDate(userEntity.getDeleteDate());
        dto.setAvatarUrl(userEntity.getAvatarUrl());
        return dto;
    }

    public User fromDtoToEntity(UserDto userDto){
        User user = new User();

        user.setId(userDto.getId());
        user.setUserRole(userDto.getUserRole());
        user.setRegistrationDate(userDto.getRegistrationDate());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        user.setUsername(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setUserAgreement(userDto.isUserAgreement());
        user.setUserStatus(userDto.getUserStatus());
        user.setDeleteDate(userDto.getDeleteDate());
        user.setAvatarUrl(userDto.getAvatarUrl());
        return user;
    }

    public EventDto fromEntityToDto(Event eventEntity){
        EventDto event = new EventDto();

        event.setId(eventEntity.getId());
        event.setUserDto(fromEntityToDto(eventEntity.getUser()));
        event.setTitle(eventEntity.getTitle());
        event.setDescription(eventEntity.getDescription());
        event.setInitialDate(eventEntity.getInitialDate());
        event.setLatitude(eventEntity.getLatitude());
        event.setLongitude(eventEntity.getLongitude());
        event.setEndDate(eventEntity.getEndDate());
        event.setCategory(eventEntity.getCategory());
        return event;
    }

    public Event fromEntityToDto(EventDto eventDto){
        Event event = new Event();

        event.setId(eventDto.getId());
        event.setUser(fromDtoToEntity(eventDto.getUserDto()));
        event.setTitle(eventDto.getTitle());
        event.setDescription(eventDto.getDescription());
        event.setInitialDate(eventDto.getInitialDate());
        event.setLatitude(eventDto.getLatitude());
        event.setLongitude(eventDto.getLongitude());
        event.setEndDate(eventDto.getEndDate());
        event.setCategory(eventDto.getCategory());
        return event;
    }

    public IssueDto fromEntityToDto(Issue issueEntity){
        IssueDto issueDto = new IssueDto();

        issueDto.setId(issueEntity.getId());
        issueDto.setUserDto(fromEntityToDto(issueEntity.getUser()));
        issueDto.setTitle(issueEntity.getTitle());
        issueDto.setDescription(issueEntity.getDescription());
        issueDto.setInitialDate(issueEntity.getInitialDate());
        issueDto.setLatitude(issueEntity.getLatitude());
        issueDto.setLongitude(issueEntity.getLongitude());
        issueDto.setCategory(issueEntity.getCategory());
        return issueDto;
    }

    public Issue fromDtoToEntity(IssueDto issueDto){
        Issue issue = new Issue();

        issue.setId(issueDto.getId());
        issue.setUser(fromDtoToEntity(issueDto.getUserDto()));
        issue.setTitle(issueDto.getTitle());
        issue.setDescription(issueDto.getDescription());
        issue.setInitialDate(issueDto.getInitialDate());
        issue.setLatitude(issueDto.getLatitude());
        issue.setLongitude(issueDto.getLongitude());
        issue.setCategory(issueDto.getCategory());
        return issue;
    }

    public IssueLocationDto fromEntityToMapDto(Issue issueEntity){
        IssueLocationDto issueDto = new IssueLocationDto();

        issueDto.setId(issueEntity.getId());
        issueDto.setLatitude(issueEntity.getLatitude());
        issueDto.setLongitude(issueEntity.getLongitude());
        return issueDto;
    }

    public Issue fromDtoToEntity(IssueLocationDto issueDto){
        Issue issue = new Issue();

        issue.setId(issueDto.getId());
        issue.setLatitude(issueDto.getLatitude());
        issue.setLongitude(issueDto.getLongitude());
        return issue;
    }


    public PetitionDto fromEntityToDto(Petition petitionEntity){
        PetitionDto petition = new PetitionDto();

        petition.setId(petitionEntity.getId());
        petition.setUserDto(fromEntityToDto(petitionEntity.getUser()));
        petition.setTitle(petitionEntity.getTitle());
        petition.setDescription(petitionEntity.getDescription());
        petition.setInitialDate(petitionEntity.getInitialDate());
        petition.setCategory(petitionEntity.getCategory());
        return petition;
    }

    public Petition fromEntityToDto(PetitionDto petitionDto){
        Petition petition = new Petition();

        petition.setId(petitionDto.getId());
        petition.setUser(fromDtoToEntity(petitionDto.getUserDto()));
        petition.setTitle(petitionDto.getTitle());
        petition.setDescription(petitionDto.getDescription());
        petition.setInitialDate(petitionDto.getInitialDate());
        petition.setCategory(petitionDto.getCategory());
        return petition;
    }
}
