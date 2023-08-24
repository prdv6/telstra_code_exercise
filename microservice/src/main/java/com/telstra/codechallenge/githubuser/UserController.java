package com.telstra.codechallenge.githubuser;

import com.telstra.codechallenge.common.Order;
import com.telstra.codechallenge.common.SearchParams;
import com.telstra.codechallenge.error.DataNotFoundException;
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
public class UserController {
    private final Logger LOGGER =
            LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUsers(@RequestParam(defaultValue = "1") Integer pageNumber, @RequestParam(defaultValue = "5") Integer perPage) throws DataNotFoundException {
        SearchParams parameter = new SearchParams();
        parameter.setQ("followers:<=0");
        parameter.setSort("joined");
        parameter.setOrder(Order.ASC);
        parameter.setPage(pageNumber);
        parameter.setPerPage(perPage);


        List<User> userResponse = userService.getUsers(parameter);
        LOGGER.info("response are {}", userResponse);


        return new ResponseEntity<List<User>>(userResponse, HttpStatus.OK);
    } }