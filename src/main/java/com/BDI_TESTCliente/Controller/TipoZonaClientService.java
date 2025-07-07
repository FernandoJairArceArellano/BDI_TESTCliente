package com.BDI_TESTCliente.Controller;

import com.BDI_TESTCliente.ML.TipoZona;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TipoZonaClientService {

    private final String URL_API = "http://localhost:8081/api/tipoZona/v1";

    private final RestTemplate restTemplate = new RestTemplate();

    public List<TipoZona> obtenerZonas() {
        ResponseEntity<List<TipoZona>> response = restTemplate.exchange(
                URL_API, // Usa la URL real de tu endpoint
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TipoZona>>() {
        }
        );

        return response.getBody();
    }
}
