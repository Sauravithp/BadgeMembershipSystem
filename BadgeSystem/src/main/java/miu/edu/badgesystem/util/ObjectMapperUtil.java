package miu.edu.badgesystem.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;

public class ObjectMapperUtil {
    public static <T> T map(String source, Class<T> destination) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(source, destination);
    }

    public static String convertToJson(Object o) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return mapper.writeValueAsString(o);
    }
}
