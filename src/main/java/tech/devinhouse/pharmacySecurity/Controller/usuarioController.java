package tech.devinhouse.pharmacySecurity.Controller;

import tech.devinhouse.pharmacySecurity.Entity.usuarioEntity;
import tech.devinhouse.pharmacySecurity.Service.usuarioService;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000 ")
@RequestMapping("/usuario")
public class usuarioController {


    private final usuarioService usuarioService;

    public usuarioController(tech.devinhouse.pharmacySecurity.Service.usuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrarUsuario(@Valid @RequestBody usuarioEntity usuario){
     return usuarioService.cadastrarUsuario(usuario);
    }

    @GetMapping("/login")
    public ResponseEntity<?> buscarPorEmailEsenha(@RequestParam ("email") String email, @RequestParam("senha") String senha){
        return usuarioService.buscarPorEmailEsenha(email,senha);
    }
}
