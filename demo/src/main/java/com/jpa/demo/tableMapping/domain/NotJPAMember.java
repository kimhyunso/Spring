package com.jpa.demo.tableMapping.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class NotJPAMember {
    private String id;
    private String username;

    private NotJPATeam team;

    public void setTeam(NotJPATeam team) {
        this.team = team;
    }
}
