package com.BDI_TESTCliente.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/")
public class PaginaController {

    private String urlBase = "http://localhost:8081/api/pagina/v1";

    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/index")
    public String index() {
        return "index";
    }
    
//    @GetMapping("/usuarios")
//    public String mostrarUsuarios(){
//        return ""
//    }

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
