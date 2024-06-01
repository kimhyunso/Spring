package com.example.chapter05.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class NotJPATeam {

    private String id;
    private String name;

}
