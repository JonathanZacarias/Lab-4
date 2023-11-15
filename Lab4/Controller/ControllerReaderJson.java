package Lab4.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import Lab4.bean.Usuario;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ControllerReaderJson<T> {
    private final Class<T> type;

    public ControllerReaderJson(Class<T> type) {
        this.type = type;
    }

    public T readFromJson(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(new File(filePath), type);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Usuario> leerArchivo(String string) {
        return null;
    }
}
