package miu.edu.badgesystem.util;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ModelMapperUtils {

    public static <T> T map(Object source, Class<T> destination) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(source, destination);
    }

}
