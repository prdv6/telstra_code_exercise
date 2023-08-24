package com.telstra.codechallenge.common;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchParams {

    private String q;
    private String sort;
    private Order order;
    private Integer perPage;
    private Integer page ;
}
