package input.visualization.infrastructure.persistence;

import input.visualization.domain.DataRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileRepository implements DataRepository {

    @Value("classpath:input/data")
    private Resource data;

    @Override
    public List<String> loadData() {
        try {
            Path path = Paths.get(data.getURI());
            return Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

}
