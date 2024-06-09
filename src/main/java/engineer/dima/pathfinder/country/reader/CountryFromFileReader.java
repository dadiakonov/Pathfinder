package engineer.dima.pathfinder.country.reader;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import engineer.dima.pathfinder.country.Country;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Set;

@Service
@Slf4j
public class CountryFromFileReader implements CountryReader {
    private final Gson gson;
    private final ResourceLoader resourceLoader;
    private final String dataPath;

    public CountryFromFileReader(
            Gson gson,
            ResourceLoader resourceLoader,
            @Value("${country.reader.data.path:data/countries.json}") String dataPath) {
        this.gson = gson;
        this.resourceLoader = resourceLoader;
        this.dataPath = dataPath;
    }

    @Override
    public Set<Country> getAll() {
        Resource resource = resourceLoader.getResource("classpath:" + dataPath);

        try (Reader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            Type setOfCountriesType = new TypeToken<Set<Country>>(){}.getType();

            return gson.fromJson(reader, setOfCountriesType);
        } catch (IOException e) {
            log.error("Error while reading countries from file.", e);

            // Handle exception properly in real application
            throw new RuntimeException(e);
        }
    }
}
