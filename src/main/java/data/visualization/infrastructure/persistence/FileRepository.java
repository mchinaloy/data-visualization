package data.visualization.infrastructure.persistence;

import data.visualization.domain.DataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class FileRepository implements DataRepository {

    private static final Logger LOG = LoggerFactory.getLogger(FileRepository.class);
    private static final int CHUNK_SIZE = 8;

    private BufferedReader bufferedReader;

    @Override
    public String loadData() {
        String data = "";
        try {
            setupReader();
            char[] buffer = new char[CHUNK_SIZE];
            if(bufferedReader.read(buffer) == -1){
                bufferedReader.reset();
            }
            data = String.valueOf(buffer);
        } catch (IOException e) {
            LOG.error("An error occurred whilst reading data chunk from file:", e);
        }
        return data;
    }

    private void setupReader() throws IOException {
        if(bufferedReader == null) {
            bufferedReader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/input/image.txt")));
        }
    }

}
