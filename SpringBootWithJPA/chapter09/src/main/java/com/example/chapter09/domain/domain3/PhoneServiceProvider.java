package com.example.chapter09.domain.domain3;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Builder
public class PhoneServiceProvider {

    @Id
    private String name;
}
