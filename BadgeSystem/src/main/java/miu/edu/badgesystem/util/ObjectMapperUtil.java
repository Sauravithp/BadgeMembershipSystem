package miu.edu.badgesystem.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ObjectMapperUtil {
    public static <T> T map(String source, Class<T> destination) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(source, destination);
    }
}
