package com.telstra.codechallenge.githubapirepository;

import com.telstra.codechallenge.common.RepositoryDTO;
import com.telstra.codechallenge.common.SearchParams;
import com.telstra.codechallenge.error.DataNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Value;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RepositoryService {
    private final Logger LOGGER =
            LoggerFactory.getLogger(RepositoryService.class);
    @Value("${githubApi.base.url}")
    private String repositoryBaseUrl;
    @Autowired
    private RestTemplate template;

  public List<Repository> getRepositories(SearchParams searchParams) throws DataNotFoundException {
      LOGGER.info("searchParams inside respositry service {}",searchParams.getQ());

      String baseUrl = repositoryBaseUrl + "/search/repositories";


      URI uri= UriComponentsBuilder.fromHttpUrl(baseUrl)
              .queryParam("q", searchParams.getQ())
              .queryParam("sort", searchParams.getSort())
              .queryParam("order",searchParams.getOrder().value())
              .queryParam("page", searchParams.getPage())
              .queryParam("per_page", searchParams.getPerPage())
              .build()
              .encode()
              .toUri();


     RepositoryDTO repositoryDTO = template.getForObject(uri, RepositoryDTO.class);

      if(Objects.nonNull(repositoryDTO) ) {
            if(repositoryDTO.getItems().isEmpty()){
              throw new DataNotFoundException("No repository present");
          }


          List<Repository> repoList = repositoryDTO.getItems().stream().
                  map(o -> Repository.builder().name(o.getName())
                                          .language(o.getLanguage())
                                            .description(o.getDescription())
                                           .html_url(o.getHtml_url())
                                  .watchers_count(o.getWatchers_count())

                          .build())

                  .collect(Collectors.toList());

          LOGGER.info("repository size  {}", repoList.size());
          return repoList;

      }
      throw new RuntimeException("Service unavailable");
  }
}
