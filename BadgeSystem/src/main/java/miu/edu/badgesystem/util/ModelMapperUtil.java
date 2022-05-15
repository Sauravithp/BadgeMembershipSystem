package miu.edu.badgesystem.util;

public class ModelMapperUtil {
    public static <T> T map(Object source, Class<T> destination) {
        ModelMapperUtil modelMapper = new ModelMapperUtil();
        return modelMapper.map(source, destination);
    }
}
