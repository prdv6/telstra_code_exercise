package com.telstra.codechallenge.common;

import com.telstra.codechallenge.githubapirepository.Repository;
import com.telstra.codechallenge.githubuser.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private long total_count;
    private boolean incomplete_results;
    private List<User> items;
}
