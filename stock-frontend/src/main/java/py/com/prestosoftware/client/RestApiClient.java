package py.com.prestosoftware.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestApiClient {

    private final RestTemplate restTemplate;
    private final String backendUrl;

    public RestApiClient(RestTemplate restTemplate,
            @Value("${app.backend.url:http://localhost:8081}") String backendUrl) {
        this.restTemplate = restTemplate;
        this.backendUrl = backendUrl.endsWith("/")
                ? backendUrl.substring(0, backendUrl.length() - 1)
                : backendUrl;
    }

    public <T> List<T> findAll(String resource, Class<T[]> arrayType) {
        T[] entities = restTemplate.getForObject(url(resource), arrayType);
        return entities == null ? Collections.emptyList() : Arrays.asList(entities);
    }

    public <T> T findById(String resource, Object id, Class<T> responseType) {
        return restTemplate.getForObject(url(resource) + "/" + id, responseType);
    }

    public long count(String resource) {
        Long count = restTemplate.getForObject(url(resource) + "/count", Long.class);
        return count == null ? 0L : count;
    }

    public Long findLastResourceValue(String resource) {
        return restTemplate.getForObject(url(resource), Long.class);
    }

    public <T> T save(String resource, T entity, Class<T> responseType) {
        return restTemplate.postForObject(url(resource), entity, responseType);
    }

    public <T> T post(String resource, Object body, Class<T> responseType) {
        return restTemplate.postForObject(url(resource), body, responseType);
    }

    public <T> T update(String resource, Object id, T entity, Class<T> responseType) {
        return restTemplate.exchange(url(resource) + "/" + id,
                HttpMethod.PUT, new HttpEntity<>(entity), responseType).getBody();
    }

    public void remove(String resource, Object id) {
        restTemplate.delete(url(resource) + "/" + id);
    }

    public List<Object[]> findObjectRows(String resource, Object... parameters) {
        org.springframework.web.util.UriComponentsBuilder builder =
                org.springframework.web.util.UriComponentsBuilder.fromHttpUrl(url(resource));
        for (Object parameter : parameters) {
            builder.queryParam("p", parameter);
        }
        Object[][] rows = restTemplate.getForObject(builder.build().encode().toUri(), Object[][].class);
        return rows == null ? Collections.emptyList() : Arrays.asList(rows);
    }

    private String url(String resource) {
        String normalizedResource = resource.startsWith("/") ? resource : "/" + resource;
        return backendUrl + normalizedResource;
    }
}
