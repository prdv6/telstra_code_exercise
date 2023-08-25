package com.telstra.codechallenge.githubuser;

import com.telstra.codechallenge.common.Order;
import com.telstra.codechallenge.common.SearchParams;
import com.telstra.codechallenge.common.Sort;
import com.telstra.codechallenge.error.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(value = UserController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private List<User> userList = new ArrayList<>();

    private SearchParams parameter;


    @BeforeEach
    void setUp() throws UserNotFoundException {
        User user1 = User.builder()
                .id(123L)
                .login("mattetti")
                .html_url("https://github.com/mattetti")
                .build();

        User user2 = User.builder()
                .id(456L)
                .login("sevenwire")
                .html_url("https://github.com/sevenwire")
                .build();

        userList.add(user1);
        userList.add(user2);


        parameter = SearchParams.builder()
                .q("followers:<=0")
                .sort(Sort.JOINED)
                .order(Order.ASC)
                .page(1)
                .perPage(15).build();

        Mockito.when(userService.getUsers(parameter))
                .thenReturn(userList);


    }

    @Test
    @DisplayName("Get Users from Controller")
    public void testGetUsers() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders.get("/search/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].login", is("mattetti")))
                .andExpect(jsonPath("$").exists());


    }


}