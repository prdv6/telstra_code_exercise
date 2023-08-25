package com.telstra.codechallenge.githubuser;

import com.telstra.codechallenge.common.SearchParams;
import com.telstra.codechallenge.common.UserDTO;
import com.telstra.codechallenge.error.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final Logger LOGGER =
            LoggerFactory.getLogger(UserService.class);
    @Value("${githubApi.base.url}")
    private String userBaseUrl;
    @Autowired
    private RestTemplate template;

    public List<User> getUsers(SearchParams searchParams) throws UserNotFoundException {
    LOGGER.info("searchParams inside user service {}",searchParams.getQ());


        String baseUrl = userBaseUrl + "/search/users";


        URI uri= UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("q", searchParams.getQ())
                .queryParam("sort", searchParams.getSort().value())
                .queryParam("order", searchParams.getOrder().value())
                .queryParam("page", searchParams.getPage())
                .queryParam("per_page", searchParams.getPerPage())
                .build()
                .encode()
                .toUri();


        UserDTO userDTO = template.getForObject(uri, UserDTO.class);

        if(Objects.nonNull(userDTO) ) {
            if(userDTO.getItems().isEmpty()){
                throw new UserNotFoundException("no user present");
            }


            List<User> userList = userDTO.getItems().stream().
                    map(o -> User.builder()
                            .id(o.getId())
                            .login(o.getLogin())
                            .html_url(o.getHtml_url())
                            .build())
                        .collect(Collectors.toList());
            LOGGER.info("repository size is  {}", userList.size());
            return userList;

        }
        throw new RuntimeException("Service unavailable");
    }
}

