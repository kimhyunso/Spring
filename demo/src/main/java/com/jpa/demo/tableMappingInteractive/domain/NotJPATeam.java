package com.jpa.demo.tableMappingInteractive.domain;

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
