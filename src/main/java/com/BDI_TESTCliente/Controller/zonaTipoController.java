package com.BDI_TESTCliente.Controller;

import com.BDI_TESTCliente.ML.TipoZona;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/1")
public class zonaTipoController {

    @Autowired
    private TipoZonaClientService ejemploRestFull;

    @GetMapping
    public String mostrarZonas(Model model) {
        List<TipoZona> zonas = ejemploRestFull.obtenerZonas();
        model.addAttribute("zonas", zonas);
        return "zonas";
    }
}
