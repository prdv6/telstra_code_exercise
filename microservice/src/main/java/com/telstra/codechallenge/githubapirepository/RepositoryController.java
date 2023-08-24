package com.telstra.codechallenge.githubapirepository;

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

import java.time.LocalDate;
import java.util.List;

@RestController
public class RepositoryController {
    private final Logger LOGGER =
            LoggerFactory.getLogger(RepositoryController.class);
    @Autowired
    private RepositoryService repoService;

    @RequestMapping(path = "/repositories", method = RequestMethod.GET)
    public ResponseEntity<List<Repository>> repositories(@RequestParam(defaultValue = "1") Integer pageNumber, @RequestParam(defaultValue = "5") Integer perPage) throws DataNotFoundException {

        LocalDate startDate = LocalDate.now().minusDays(7);

        SearchParams parameter = new SearchParams();
        parameter.setQ("webos created:>"+startDate);
        parameter.setSort("stars");
        parameter.setOrder(Order.DESC);
        parameter.setPage(pageNumber);
        parameter.setPerPage(perPage);




        List<Repository> repoResponse = repoService.getRepositories(parameter);
        LOGGER.info("response are {}",repoResponse);


        return new ResponseEntity<List<Repository>>(repoResponse, HttpStatus.OK);
    }
}
