package engineer.dima.pathfinder.country.reader;

import com.google.gson.Gson;
import engineer.dima.pathfinder.country.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CountryFromFileReaderTest {
    private static final String FILE_PATH = "data/test.json";
    private static final String FILE_CONTENT = """
                [
                    {
                        "cca2": "CA",
                        "ccn3": "124",
                        "cca3": "CAN",
                        "region": "Americas",
                        "borders": [
                            "USA"
                        ],
                        "area": 9984670
                    },
                    {
                        "cca2": "US",
                        "ccn3": "840",
                        "cca3": "USA",
                        "region": "Americas",
                        "borders": [
                            "CAN",
                            "MEX"
                        ],
                        "area": 9372610
                    },
                    {
                        "cca2": "MX",
                        "ccn3": "484",
                        "cca3": "MEX",
                        "region": "Americas",
                        "borders": [
                            "USA"
                        ],
                        "area": 1964375
                    },
                    {
                        "cca2": "AU",
                        "ccn3": "036",
                        "cca3": "AUS",
                        "region": "Oceania",
                        "borders": [],
                        "area": 7692024
                    }
                ]""";

    @Mock
    private ResourceLoader resourceLoader;

    private CountryFromFileReader countryReader;

    @BeforeEach
    void setUp() {
        countryReader = new CountryFromFileReader(new Gson(), resourceLoader, FILE_PATH);
    }

    @Test
    void testGetAll() {
        InputStream inputStream = new ByteArrayInputStream(FILE_CONTENT.getBytes(StandardCharsets.UTF_8));
        Resource resource = new InputStreamResource(inputStream);

        when(resourceLoader.getResource("classpath:" + FILE_PATH)).thenReturn(resource);

        Set<Country> fetchedCountries = countryReader.getAll();

        Set<Country> expectedCountries = Set.of(
                new Country("CAN", Set.of("USA")),
                new Country("USA", Set.of("CAN", "MEX")),
                new Country("MEX", Set.of("USA")),
                new Country("AUS", Set.of())
        );

        assertEquals(expectedCountries, fetchedCountries);
    }
}
