package com.example.admin.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StringUtil {

    private static Logger logger = LoggerFactory.getLogger(StringUtil.class);

    public static boolean isOnlyChineseCharacter(String string) {
        char[] chars = string.toCharArray();
        for (Character character : chars) {
            if (Character.UnicodeScript.of(character) != Character.UnicodeScript.HAN) return false;
        }
        return true;
    }

    public static String parsePOJOtoJSON(Object pojo) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.writeValueAsString(pojo);
        } catch (JsonProcessingException ex) {
            logger.error(ex.getMessage());
            return null;
        }
    }

    public static <T> T parseJSONtoPOJO(String json, Class<T> fromClass, Class... paramClasses) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            objectMapper.registerModule(new JavaTimeModule());
            if (paramClasses.length > 0) {
                JavaType javaType = objectMapper.getTypeFactory().constructParametricType(fromClass, paramClasses);
                return objectMapper.readValue(json, javaType);
            }
            return objectMapper.readValue(json, fromClass);
        } catch (IOException ex) {
            logger.error(ex.getMessage());
            return null;
        }
    }

    public static <T> List<List<T>> fixedGrouping2(List<T> source, int n) {

        if (null == source || source.size() == 0 || n <= 0) {
            return null;
        }
        List<List<T>> result = new ArrayList<List<T>>();
        int remainder = source.size() % n;
        int size = (source.size() / n);
        for (int i = 0; i < size; i++) {
            List<T> subset = null;
            subset = source.subList(i * n, (i + 1) * n);
            result.add(subset);
        }
        if (remainder > 0) {
            List<T> subset = null;
            subset = source.subList(size * n, size * n + remainder);
            result.add(subset);
        }
        return result;
    }
}
