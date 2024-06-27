package com.example.demo.process;

import com.example.demo.domain.Person;
import org.springframework.batch.item.ItemProcessor;

public class PersonItemProcessor implements ItemProcessor<Person, Person> {


    @Override
    public Person process(final Person person) throws Exception {
        String firstName = person.firstName().toUpperCase();
        String lastName = person.lastName().toUpperCase();

        Person transformPerson = new Person(firstName, lastName);

        return person;
    }
}
