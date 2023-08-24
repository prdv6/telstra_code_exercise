package com.telstra.codechallenge.githubapirepository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Repository {

    private String html_url;
    private Long watchers_count;
    private String language;
    private String description;
    private String name;
}
