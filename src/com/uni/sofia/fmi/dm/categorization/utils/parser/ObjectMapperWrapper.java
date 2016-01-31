package com.uni.sofia.fmi.dm.categorization.utils.parser;

import java.io.File;
import java.io.IOException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

/**
 *
 * @author Dimitar
 */
public class ObjectMapperWrapper {

    public static void writeObject(Object object, File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
        writer.writeValue(file, object);
    }

    public static Object readFile(File file, Class clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(file, clazz);
    }

}
