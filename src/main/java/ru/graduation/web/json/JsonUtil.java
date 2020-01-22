package ru.graduation.web.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import java.io.IOException;
import java.util.List;

import static ru.graduation.web.json.JacksonObjectMapper.getMapper;

public class JsonUtil {

    public static <T> List<T> readValues(String json,Class<T> clazz){
        ObjectReader reader=getMapper().readerFor(clazz);
        try {
            return reader.<T>readValues(json).readAll();
        }
        catch(IOException e){
            throw new IllegalArgumentException("Некорректное чтение списка из JSON: "+json,e);
        }
    }

    public static <T> T readValue(String json,Class<T> clazz){
        try{
            return getMapper().readValue(json,clazz);
        }
        catch(IOException e){
            throw new IllegalArgumentException("Некорректное чтение значения из JSON: "+json,e);
        }
    }

    public static <T> String writeValue(T obj){
        try{
            return getMapper().writeValueAsString(obj);
        }
        catch(JsonProcessingException e){
            throw new IllegalArgumentException("Некорректная запись объекта в JSON: "+obj,e);
        }
    }


}
