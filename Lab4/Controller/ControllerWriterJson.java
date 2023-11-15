package Lab4.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ControllerWriterJson<T> {
    private Class<T> type;

    public void setType(Class<T> type) {
        this.type = type;
    }

    public void escribirArchivo(List<T> data, String fileName) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileName), data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
