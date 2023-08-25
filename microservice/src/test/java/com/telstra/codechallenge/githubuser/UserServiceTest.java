package com.telstra.codechallenge.githubuser;

import com.telstra.codechallenge.common.Order;
import com.telstra.codechallenge.common.SearchParams;
import com.telstra.codechallenge.common.Sort;
import com.telstra.codechallenge.error.UserNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    private SearchParams parameter;

    private List<User> userList = new ArrayList<>();


    @Test
    @DisplayName("Get Valid Users  from Service")
    public void testGetUsersValid() throws UserNotFoundException {
        parameter = SearchParams.builder()
                .q("followers:<=0")
                .sort(Sort.JOINED)
                .order(Order.ASC)
                .page(1)
                .perPage(15).build();

        userList = userService.getUsers(parameter);

        assertEquals(userList.size(), 15);
    }


    @Test
    @DisplayName("Get Not a Valid Users from Service")
    public void testGetUsersNotValid() throws UserNotFoundException {
        SearchParams tempParameter = SearchParams.builder()
                .q("followers:<0")
                .sort(Sort.JOINED)
                .order(Order.ASC)
                .page(1)
                .perPage(15).build();

        Throwable thrown = assertThrows(UserNotFoundException.class, () -> userService.getUsers(tempParameter));
        assertEquals("no user present", thrown.getMessage());
    }


}