package com.BDI_TESTCliente.Controller;

import com.BDI_TESTCliente.ML.Contrato;
import com.BDI_TESTCliente.ML.NodoEntrega;
import com.BDI_TESTCliente.ML.NodoRecepccion;
import com.BDI_TESTCliente.ML.Result;
import com.BDI_TESTCliente.ML.Transaccion;
import com.BDI_TESTCliente.ML.Usuario;
import com.BDI_TESTCliente.ML.ZonaExtraccion;
import com.BDI_TESTCliente.ML.ZonaInyeccion;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/")
public class PaginaController {

    private final String apiUsuarioUrl = "http://localhost:8081/api/usuario/v1";

    private final String apiContratoUrl = "http://localhost:8081/api/contratos/v1";

    private final String apiNodoUrl = "http://localhost:8081/api/nodos/v1";

    private final String apiTransaccion = "http://localhost:8081/api/transacciones/v1";

    private final String apiZonas = "http://localhost:8081/api/zonas/v1";

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

    @GetMapping("/nodos")
    public String mostrarNodos(Model model) {
        ResponseEntity<Result<NodoRecepccion>> responseRecepccion = restTemplate.exchange(
                apiNodoUrl + "/recepccion",
                HttpMethod.GET,
                HttpEntity.EMPTY,
                new ParameterizedTypeReference<Result<NodoRecepccion>>() {
        });

        Result<NodoRecepccion> resultRecepcion = responseRecepccion.getBody();

        ResponseEntity<Result<NodoEntrega>> responseEntrega = restTemplate.exchange(
                apiNodoUrl + "/entrega",
                HttpMethod.GET,
                HttpEntity.EMPTY,
                new ParameterizedTypeReference<Result<NodoEntrega>>() {
        });

        Result<NodoEntrega> resultEntrega = responseEntrega.getBody();

        if (resultRecepcion != null & resultRecepcion.correct & responseEntrega != null & resultEntrega.correct) {
            List<NodoRecepccion> nodosRecepcion = resultRecepcion.objects;
            model.addAttribute("nodosRecepcion", nodosRecepcion);
            List<NodoEntrega> nodosEntrega = resultEntrega.objects;
            model.addAttribute("nodosEntrega", nodosEntrega);
        } else {
            model.addAttribute("error", resultRecepcion != null ? resultRecepcion.errorMessage : "Error desconocido");
        }
        return "nodos";
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

        Result<Contrato> resultContratos = responseContratos.getBody();

        if (resultContratos != null && resultContratos.correct) {
            List<Contrato> contratos = resultContratos.objects;

            model.addAttribute("contratos", contratos);
        } else {
            model.addAttribute("error", resultContratos != null ? resultContratos.errorMessage : "Error desconocido");
        }
        return "contratos";
    }

    @GetMapping("/transacciones")
    public String mostrarTransacciones(Model model) {
        ResponseEntity<Result<Transaccion>> responseTransacciones = restTemplate.exchange(
                apiTransaccion + "/transacciones",
                HttpMethod.GET,
                HttpEntity.EMPTY,
                new ParameterizedTypeReference<Result<Transaccion>>() {

        });

        Result<Transaccion> resultTransaccion = responseTransacciones.getBody();

        if (resultTransaccion != null && resultTransaccion.correct) {
            List<Transaccion> transacciones = resultTransaccion.objects;
            model.addAttribute("transacciones", transacciones);
        } else {
            model.addAttribute("error", resultTransaccion != null ? resultTransaccion.errorMessage : "Error desconocido");
        }

        return "transacciones";
    }

    @GetMapping("/detalleTransaccion")
    public String mostrarDetalleTransaccion(@RequestParam("idOperacion") int idOperacion, Model model) {
        return "detalleTransaccion";
    }

    @GetMapping("/zonas")
    public String mostrarZonas(Model model) {
        ResponseEntity<Result<ZonaInyeccion>> responseZonaInyeccion = restTemplate.exchange(
                apiZonas + "/zonainyeccion",
                HttpMethod.GET,
                HttpEntity.EMPTY,
                new ParameterizedTypeReference<Result<ZonaInyeccion>>() {
        });

        Result<ZonaInyeccion> resultZonaInyeccion = responseZonaInyeccion.getBody();

        ResponseEntity<Result<ZonaExtraccion>> responseZonaExtraccion = restTemplate.exchange(
                apiZonas + "/zonaextraccion",
                HttpMethod.GET,
                HttpEntity.EMPTY,
                new ParameterizedTypeReference<Result<ZonaExtraccion>>() {
        });

        Result<ZonaExtraccion> resultZonaExtraccion = responseZonaExtraccion.getBody();

        if (resultZonaInyeccion != null && resultZonaInyeccion.correct && resultZonaExtraccion != null && resultZonaExtraccion.correct) {
            List<ZonaInyeccion> zonasInyeccion = resultZonaInyeccion.objects;
            model.addAttribute("zonasInyeccion", zonasInyeccion);
            List<ZonaExtraccion> zonasExtraccion = resultZonaExtraccion.objects;
            model.addAttribute("zonasExtraccion", zonasExtraccion);
        } else {
            model.addAttribute("error", resultZonaExtraccion != null ? resultZonaExtraccion.errorMessage : "Error desconocido");
        }

        return "zonas";
    }

    @GetMapping("/detalleZonaInyeccion")
    public String mostrarDetalleZonaInyeccion(@RequestParam("nombreZona") String nombreZona, Model model) {
        Result result = new Result();
        try {
            ResponseEntity<Result<Usuario>> responseUsuario = restTemplate.exchange(
                    apiNodoUrl + "/buscarPorZonaInyeccion?nombreZona=" + nombreZona,
                    HttpMethod.GET,
                    HttpEntity.EMPTY,
                    new ParameterizedTypeReference<Result<Usuario>>() {
            });

            Result<Usuario> resultUsuario = responseUsuario.getBody();

            if (resultUsuario != null && resultUsuario.correct) {
                model.addAttribute("usuarios", resultUsuario.objects);
            } else {
                model.addAttribute("error", resultUsuario != null ? resultUsuario.errorMessage : "Usuario no encontrado.");
            }
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return "detalleZona";
    }

    @GetMapping("/detalleZonaExtraccion")
    public String mostrarDetalleZonaExtraccion(@RequestParam("nombreZona") String nombreZona, Model model) {
        Result result = new Result();
        try {
            ResponseEntity<Result<Usuario>> responseUsuario = restTemplate.exchange(
                    apiNodoUrl + "/buscarPorZonaExtraccion?nombreZona=" + nombreZona,
                    HttpMethod.GET,
                    HttpEntity.EMPTY,
                    new ParameterizedTypeReference<Result<Usuario>>() {
            });

            Result<Usuario> resultUsuario = responseUsuario.getBody();

            if (resultUsuario != null && resultUsuario.correct) {
                model.addAttribute("usuarios", resultUsuario.objects);
            } else {
                model.addAttribute("error", resultUsuario != null ? resultUsuario.errorMessage : "Usuario no encontrado.");
            }
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return "detalleZona";
    }

    @GetMapping("/detalleNodoComercial")
    public String mostrarDetalleNodoComercial(@RequestParam("codigoNodo") String codigoNodo, Model model) {
        Result result = new Result();
        char evaluarCodigoNodo = codigoNodo.charAt(0);
        try {
            if (evaluarCodigoNodo == 'V') {
                result.correct = true;
                System.out.println("Se entro en los nodos de Recepcción\nLetra evaluada: " + evaluarCodigoNodo);
                ResponseEntity<Result<Usuario>> responseUsuario = restTemplate.exchange(
                        apiNodoUrl + "/buscarPorNodoRecepccion?codigoNodo=" + codigoNodo,
                        HttpMethod.GET,
                        HttpEntity.EMPTY,
                        new ParameterizedTypeReference<Result<Usuario>>() {
                });

                Result<Usuario> resultUsuario = responseUsuario.getBody();

                if (resultUsuario != null && resultUsuario.correct) {
                    model.addAttribute("usuarios", resultUsuario.objects);
                } else {
                    model.addAttribute("error", resultUsuario != null ? resultUsuario.errorMessage : "Usuario no encontrado.");
                }

            } else if (evaluarCodigoNodo == 'E' || evaluarCodigoNodo == 'N') {
                result.correct = true;
                System.out.println("Se entro en los nodos de Entrega\nLetra evaluada: " + evaluarCodigoNodo);
                ResponseEntity<Result<Usuario>> responseUsuario = restTemplate.exchange(
                        apiNodoUrl + "/buscarPorNodoEntrega?codigoNodo=" + codigoNodo,
                        HttpMethod.GET,
                        HttpEntity.EMPTY,
                        new ParameterizedTypeReference<Result<Usuario>>() {
                });

                Result<Usuario> resultUsuario = responseUsuario.getBody();
                if (resultUsuario != null && resultUsuario.correct) {
                    model.addAttribute("usuarios", resultUsuario.objects);
                } else {
                    model.addAttribute("error", resultUsuario != null ? resultUsuario.errorMessage : "Usuario no encontrado.");
                }

            } else {
                result.correct = false;
                result.errorMessage = "No se contro ningun nodo con el codigo: " + codigoNodo;
            }
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return "detalleNodoComercial";
    }

    @GetMapping("/detalleContrato")
    public String obtenerContratoPorCodigo(@RequestParam("idContrato") int idContrato, Model model) {
        try {

            ResponseEntity<Result<Contrato>> response = restTemplate.exchange(
                    apiContratoUrl + "/por-id-contrato?idContrato=" + idContrato,
                    HttpMethod.GET,
                    HttpEntity.EMPTY,
                    new ParameterizedTypeReference<Result<Contrato>>() {
            }
            );

            Result<Contrato> resultContrato = response.getBody();

            ResponseEntity<Result<Transaccion>> responseTransaccion = restTemplate.exchange(
                    apiTransaccion + "/por-id-contrato?idContrato=" + idContrato,
                    HttpMethod.GET,
                    HttpEntity.EMPTY,
                    new ParameterizedTypeReference<Result<Transaccion>>() {
            });

            Result<Transaccion> resultTransaccion = responseTransaccion.getBody();

            if (resultContrato != null && resultContrato.correct && resultTransaccion != null && resultTransaccion.correct) {
                model.addAttribute("contrato", resultContrato.object);
                model.addAttribute("transacciones", resultTransaccion.objects);
            } else {
                model.addAttribute("error", resultContrato != null ? resultContrato.errorMessage : "Contrato no encontrado.");
            }

        } catch (Exception ex) {
            model.addAttribute("error", "Error al obtener contrato: " + ex.getMessage());
        }

        return "detalleContrato";
    }

    @GetMapping("/detalleUsuario")
    public String mostrarDetalleUsuario(@RequestParam("nombre") String nombre, Model model) {
        try {
            ResponseEntity<Result<Usuario>> responseUsuario = restTemplate.exchange(
                    apiUsuarioUrl + "/por-nombre?nombre=" + nombre,
                    HttpMethod.GET,
                    HttpEntity.EMPTY,
                    new ParameterizedTypeReference<Result<Usuario>>() {
            });

            Result<Usuario> resultUsuario = responseUsuario.getBody();

            ResponseEntity<Result<Contrato>> responseContrato = restTemplate.exchange(
                    apiContratoUrl + "/por-usuario-nombre?nombre=" + nombre,
                    HttpMethod.GET,
                    HttpEntity.EMPTY,
                    new ParameterizedTypeReference<Result<Contrato>>() {
            });

            Result<Contrato> resultContrato = responseContrato.getBody();

            if (resultUsuario != null && resultUsuario.correct && resultContrato != null && resultContrato.correct) {
                model.addAttribute("usuario", resultUsuario.object);
                model.addAttribute("contratos", resultContrato.objects);
            } else {
                model.addAttribute("error", resultUsuario != null ? resultUsuario.errorMessage : "Usuario no encontrado.");
            }
        } catch (Exception ex) {
            model.addAttribute("error", "Error al obtener usuario: " + ex.getMessage());
        }
        return "detalleUsuario";
    }

    @GetMapping("/detalleUsuarioId")
    public String mostrarDetalleUsuarioPorId(@RequestParam("idUsuario") int idUsuario, Model model) {
        try {
            ResponseEntity<Result<Usuario>> responseUsuario = restTemplate.exchange(
                    apiUsuarioUrl + "/por-id?idUsuario=" + idUsuario,
                    HttpMethod.GET,
                    HttpEntity.EMPTY,
                    new ParameterizedTypeReference<Result<Usuario>>() {
            });

            Result<Usuario> resultUsuario = responseUsuario.getBody();

            ResponseEntity<Result<Contrato>> responseContrato = restTemplate.exchange(
                    apiContratoUrl + "/por-usuario-id-Vista?idUsuario=" + idUsuario,
                    HttpMethod.GET,
                    HttpEntity.EMPTY,
                    new ParameterizedTypeReference<Result<Contrato>>() {
            });

            Result<Contrato> resultContratos = responseContrato.getBody();

            if (resultUsuario != null && resultUsuario.correct && resultContratos != null && resultContratos.correct) {
                model.addAttribute("usuario", resultUsuario.object);
                model.addAttribute("contratos", resultContratos.objects);
            } else {
                model.addAttribute("error", resultUsuario != null ? resultUsuario.errorMessage : "Usuario no encontrado.");
            }

        } catch (Exception ex) {
            model.addAttribute("error", "Error al obtener el usuario:" + ex.getMessage());
        }
        return "detalleUsuario";
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
