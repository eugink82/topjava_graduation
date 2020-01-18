package ru.graduation.web.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

public class JacksonObjectMapper extends ObjectMapper {

    private JacksonObjectMapper(){
        registerModule(new Hibernate5Module());
    }
}
