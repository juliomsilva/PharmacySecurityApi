package tech.devinhouse.pharmacySecurity.Service;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tech.devinhouse.pharmacySecurity.Dto.ErrorDto;
import tech.devinhouse.pharmacySecurity.Dto.usuarioOutput;

import tech.devinhouse.pharmacySecurity.Entity.cargoEntity;
import tech.devinhouse.pharmacySecurity.Entity.usuarioEntity;

import tech.devinhouse.pharmacySecurity.Repository.usuarioRepository;

import javax.validation.Valid;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class usuarioService implements UserDetailsService {

    @Autowired
    private usuarioRepository usuarioRepository;

    public usuarioEntity buscarPorEmailESenhaRepo(String email, String senha){

       return usuarioRepository.findByEmailAndSenha(email,senha);

    }
    public ResponseEntity<?> cadastrarUsuario(@Valid @RequestBody usuarioEntity usuario) {
        cargoEntity cargoEntity = new cargoEntity();

        usuarioOutput usuarioOutput = new usuarioOutput();
        if (validarUsuario(usuario)) {
            if (usuarioRepository.existsByEmail(usuario.getEmail())) {
                return new ResponseEntity<>(new ErrorDto(Response.SC_CONFLICT, "Erro!", HttpStatus.BAD_REQUEST, "Este e-mail já possui cadastro em nosso sistema!"), HttpStatus.CONFLICT);
            } else {
              
                usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getPassword()));
                usuarioOutput.setDados(usuario);
                usuarioRepository.save(usuario);
                return new ResponseEntity<>(new usuarioOutput(Response.SC_CREATED, "Novo usuario criado!", usuarioOutput.getDados()), HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<>(new ErrorDto(Response.SC_BAD_REQUEST,"Erro!", HttpStatus.BAD_REQUEST,"Falta preencher usuário ou senha"),HttpStatus.BAD_REQUEST);
    }
    public Boolean validarUsuario(@RequestBody usuarioEntity usuario){
        if(usuario.getEmail() == null || usuario.getSenha() == null)  {
            return false;
        }else if(usuario.getEmail() == "" || usuario.getSenha() == "") {
            return false;
        }else {
            return true;
        }
    }
    @GetMapping("/login")
    public ResponseEntity buscarPorEmailEsenha(@RequestParam("email") String email, @RequestParam("senha") String senha){
        usuarioEntity usuarioEntity = buscarPorEmailESenhaRepo(email,senha);
        usuarioOutput usuarioOutput = new usuarioOutput();
        usuarioOutput.setDados(usuarioEntity);

        if(usuarioOutput.getDados() == null){
            return new ResponseEntity<>(new ErrorDto(Response.SC_NOT_FOUND,"Erro!",HttpStatus.NOT_FOUND,"Usuário ou senha inválidos!"),HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(new usuarioOutput(Response.SC_OK,"Dado encontrado!",usuarioOutput.getDados()),HttpStatus.OK);
        }


    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        usuarioEntity usuario = usuarioRepository.findUserByLogin(email);

        if(usuario == null){
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        return new User(usuario.getEmail(), usuario.getPassword(), usuario.getAuthorities());

    }
}

