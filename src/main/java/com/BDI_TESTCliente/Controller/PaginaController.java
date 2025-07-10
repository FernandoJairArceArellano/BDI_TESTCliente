package com.BDI_TESTCliente.Controller;

import com.BDI_TESTCliente.ML.Contrato;
import com.BDI_TESTCliente.ML.Result;
import com.BDI_TESTCliente.ML.Usuario;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/")
public class PaginaController {

    private final String apiUsuarioUrl = "http://localhost:8081/api/usuario/v1";

    private final String apiContratoUrl = "http://localhost:8081/api/contratos/v1";

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/index")
    public String index() {
        return "index";
    }
    
    @GetMapping("/usuarios")
    public String mostrarUsuarios(Model model) {
        // GET de usuarios
        ResponseEntity<Result<Usuario>> responseUsuarios = restTemplate.exchange(
                apiUsuarioUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Result<Usuario>>() {
        }
        );

        // GET de contratos
        ResponseEntity<Result<Contrato>> responseContratos = restTemplate.exchange(
                apiContratoUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Result<Contrato>>() {
        }
        );

        Result<Usuario> resultUsuario = responseUsuarios.getBody();
        Result<Contrato> resultContrato = responseContratos.getBody();

        if (resultUsuario != null && resultUsuario.correct && resultContrato != null && resultContrato.correct) {
            List<Usuario> usuarios = resultUsuario.objects;
            List<Contrato> contratos = resultContrato.objects;

            Map<Integer, Long> contratosPorUsuario = contratos.stream()
                    .filter(c -> c.getUsuario() != null)
                    .collect(Collectors.groupingBy(c -> c.getUsuario().getIdUsuario(), Collectors.counting()));

            model.addAttribute("usuarios", usuarios);
            model.addAttribute("contratosPorUsuario", contratosPorUsuario);
        } else {
            model.addAttribute("error", resultUsuario != null ? resultUsuario.errorMessage : "Error desconocido");
        }

        return "usuarios";
    }

    @GetMapping("/contratos")
    public String mostrarContratos(Model model) {
        ResponseEntity<Result<Contrato>> responseContratos = restTemplate.exchange(
                apiContratoUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Result<Contrato>>() {
        }
        );

        Result<Contrato> resultContrato = responseContratos.getBody();

        if (resultContrato != null && resultContrato.correct) {
            List<Contrato> contratos = resultContrato.objects;

            model.addAttribute("contratos", contratos);
        } else {
            model.addAttribute("error", resultContrato != null ? resultContrato.errorMessage : "Error desconocido");
        }
        return "contratos";
    }

    @GetMapping("/detalleContrato/{idContrato}")
    public String mostrarDetalleContrato(@PathVariable int idContrato, Model model) {
        // Buscar contrato por ID
        // Agregarlo al modelo
        return "detalleContrato"; // nombre de tu vista HTML
    }

    @GetMapping("/cargaMasiva")
    public String mostrarVistaCargaMasiva() {
        return "cargaMasiva";
    }

    @PostMapping("/cargaMasiva")
    public String leerArchivoCargaMasiva() {
        // Procesamiento lectura del archivo y guardarlo en un punto del sistema
        return null;
    }
}
