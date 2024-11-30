package com.kafka.producer.domain;

import lombok.*;
import org.apache.kafka.common.serialization.Serializer;

import java.io.Serializable;
import java.util.Objects;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Foo {
    private String name;
    private int age;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Foo foo = (Foo) o;
        return age == foo.age && Objects.equals(name, foo.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
