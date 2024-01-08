package com.jpa.demo.valueType.domain3;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class PhoneServiceProvider {

    @Id
    private String name;
}
