package com.telstra.codechallenge.common;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchParams {

    private String q;
    private Sort sort;
    private Order order;
    private Integer perPage;
    private Integer page ;
}
