package ua.softserve.rv_028.issuecitymonitor;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import ua.softserve.rv_028.issuecitymonitor.dao.UserDao;
import ua.softserve.rv_028.issuecitymonitor.dto.UserDto;
import ua.softserve.rv_028.issuecitymonitor.entity.User;
import ua.softserve.rv_028.issuecitymonitor.entity.enums.UserStatus;
import ua.softserve.rv_028.issuecitymonitor.service.UserService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTest {


    private User user;

    @Autowired
    private UserDao userDao;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Before
    public void setup(){
        user = userDao.findAllByOrderByIdAsc().get(1);
    }

    @Test
    public void testGetUser(){
        ResponseEntity<UserDto> responseEntity = testRestTemplate.
                getForEntity("/api/users/" + user.getId(), UserDto.class);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        UserDto responseObject = responseEntity.getBody();
        assertNotNull(responseObject);
        assertEquals(user.getFirstName(), responseObject.getFirstName());
        assertEquals(user.getUserStatus(), responseObject.getUserStatus());
    }

    @Test
    public void testEditUser(){
        String updatedName = "testUpdateName";
        UserStatus updatedStatus = UserStatus.ACTIVE;
        UserDto userDto = new UserDto(user);
        userDto.setFirstName(updatedName);
        userDto.setUserStatus(updatedStatus);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<UserDto> httpEntity = new HttpEntity<>(userDto,httpHeaders);
        ResponseEntity<UserDto> responseEntity = testRestTemplate.exchange("/api/users/" + userDto.getId(),
                HttpMethod.PUT, httpEntity, UserDto.class);

        System.out.println(httpEntity.toString());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        UserDto responseObject = responseEntity.getBody();
        assertNotNull(responseObject);
        assertEquals(updatedName, responseObject.getFirstName());
        assertEquals(updatedStatus, responseObject.getUserStatus());
    }

    @Test
    public void testDeleteUser(){
        long prevCount = userDao.count();
        testRestTemplate.delete("/api/users/delete/" + user.getId());
        assertEquals(prevCount-1, userDao.count());
    }

//    @Test
//    public void testUserNotFound(){
//        ResponseEntity<UserDto> responseEntity = testRestTemplate.
//                getForEntity("/api/users/0", UserDto.class);
//        assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
//    }
}
