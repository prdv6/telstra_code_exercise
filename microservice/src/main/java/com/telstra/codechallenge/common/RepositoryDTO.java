package com.telstra.codechallenge.common;

import com.telstra.codechallenge.githubapirepository.Repository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepositoryDTO {

    private long total_count;
    private boolean incomplete_results;
    private List<Repository> items;
}
