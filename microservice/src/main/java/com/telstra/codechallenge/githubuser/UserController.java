package com.telstra.codechallenge.githubuser;

import com.telstra.codechallenge.common.Order;
import com.telstra.codechallenge.common.SearchParams;
import com.telstra.codechallenge.common.Sort;
import com.telstra.codechallenge.error.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class UserController {
    private final Logger LOGGER =
            LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUsers(@RequestParam(defaultValue = "1") Integer pageNumber, @RequestParam(defaultValue = "15") Integer perPage) throws UserNotFoundException {


        SearchParams parameter=   SearchParams.builder()
                .q("followers:<=0")
                .sort(Sort.JOINED)
                .order(Order.ASC)
                .page(pageNumber)
                .perPage(perPage).build();

        List<User> userList = userService.getUsers(parameter);
        LOGGER.info("response are {}", userList);

        return new ResponseEntity<List<User>>(userList, HttpStatus.OK);

    }

}