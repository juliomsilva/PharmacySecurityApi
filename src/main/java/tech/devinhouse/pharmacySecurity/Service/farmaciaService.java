package tech.devinhouse.pharmacySecurity.Service;

import tech.devinhouse.pharmacySecurity.Dto.ErrorDto;
import tech.devinhouse.pharmacySecurity.Dto.*;
import tech.devinhouse.pharmacySecurity.Entity.enderecoEntity;
import tech.devinhouse.pharmacySecurity.Entity.farmaciaEntity;
import tech.devinhouse.pharmacySecurity.Repository.farmaciaRepository;
import tech.devinhouse.pharmacySecurity.Repository.enderecoRepository;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class farmaciaService {
    @Autowired
    private farmaciaRepository farmaciaRepository;

    @Autowired
    private enderecoRepository enderecoRepository;




    public ResponseEntity<?> cadastrarFarmacia(@RequestBody farmaciaEntity farmacia){
        enderecoEntity enderecoEntity = new enderecoEntity();
        cadastroFarmaciaOutput cadastroFarmaciaOutput = new cadastroFarmaciaOutput();
        if(farmacia.getEndereco().getCep() == null){
            return new ResponseEntity<>(new ErrorDto(Response.SC_BAD_REQUEST,"Erro!", HttpStatus.BAD_REQUEST,"Preencha o cep da empresa"),HttpStatus.CONFLICT);
        }else{

            enderecoEntity.setNumero(farmacia.getEndereco().getNumero());
            enderecoEntity.setLogradouro(farmacia.getEndereco().getLogradouro());
            enderecoEntity.setComplemento(farmacia.getEndereco().getComplemento());
            enderecoEntity.setBairro(farmacia.getEndereco().getBairro());
            enderecoEntity.setCidade(farmacia.getEndereco().getCidade());
            enderecoEntity.setEstado(farmacia.getEndereco().getEstado());
            enderecoEntity.setCep(farmacia.getEndereco().getCep());
            enderecoEntity.setLongitude(farmacia.getEndereco().getLongitude());
            enderecoEntity.setLatitude(farmacia.getEndereco().getLatitude());
            farmacia.setEndereco(enderecoEntity);
            cadastroFarmaciaOutput.setDados(farmacia);

            cadastroFarmaciaOutput.setDados(farmacia);
        }

        if(farmaciaRepository.existsByCnpj(farmacia.getCnpj())){
            return new ResponseEntity<>(new ErrorDto(Response.SC_CONFLICT,"Erro!", HttpStatus.CONFLICT,"Já existe uma empresa cadastrada com este CNPJ: "+ farmacia.getCnpj()),HttpStatus.CONFLICT);
        }else{
            if (validarFarmacia(farmacia, enderecoEntity)) {
                enderecoRepository.save(enderecoEntity);
                farmaciaRepository.save(farmacia);
                return new ResponseEntity<>(new cadastroFarmaciaOutput(Response.SC_CREATED, "Nova farmácia adicionada!", cadastroFarmaciaOutput.getDados()), HttpStatus.CREATED);
            }
           else{
                return new ResponseEntity<>(new ErrorDto(Response.SC_BAD_REQUEST,"Erro!", HttpStatus.CONFLICT,"Verifique se todos os dados estão preenchidos, dados opcionais de preenchimento: telefone da empresa e complemento, o restante é obrigatorio!"),HttpStatus.BAD_REQUEST);
            }

        }
    }
    public ResponseEntity<?> buscarFarmacia(){
        listaFarmaciaOutput listaFarmaciaOutput = new listaFarmaciaOutput();
        List<farmaciaEntity> listaFarmacias = farmaciaRepository.findAll();
        listaFarmaciaOutput.setDados(listaFarmacias);


       if (listaFarmacias.isEmpty()){
           return new ResponseEntity<>(new ErrorDto(Response.SC_NOT_FOUND,"Erro!",HttpStatus.NOT_FOUND,"Não existem farmácias cadastradas!"),HttpStatus.NOT_FOUND);
       }else{
           return new ResponseEntity<>(new listaFarmaciaOutput(Response.SC_OK,"Dado encontrado!",listaFarmaciaOutput.getDados()),HttpStatus.OK);
       }
    }
    public ResponseEntity<?> buscarFarmaciaPorID(@PathVariable Long id){
       farmaciaidOutput farmaciaidOutput = new farmaciaidOutput();
        if(farmaciaRepository.existsById(id)){
            Optional<farmaciaEntity> buscarFarmacia = farmaciaRepository.findById(id);
            farmaciaidOutput.setDados(buscarFarmacia);

            return new ResponseEntity<>(new farmaciaidOutput(Response.SC_OK,"Dado encontrado!",farmaciaidOutput.getDados()),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new ErrorDto(Response.SC_NOT_FOUND,"Erro!", HttpStatus.NOT_FOUND,"Não existe a farmácia com o id: "+id),HttpStatus.NOT_FOUND);
        }
    }
    public ResponseEntity<?> deletarPorID(@PathVariable Long id) {

        if(farmaciaRepository.existsById(id)){
            farmaciaRepository.deleteById(id);
            return ResponseEntity.accepted().body("Farmácia deletada com sucesso! ID deletado: "+id);

        }else{
            return new ResponseEntity<>(new ErrorDto(Response.SC_NOT_FOUND,"Erro!",HttpStatus.NOT_FOUND,"Não existe farmácia com o id: "+id),HttpStatus.NOT_FOUND);
        }

    }
    public ResponseEntity<?> atualizarFarmacia(@PathVariable Long id,@RequestBody farmaciaEntity farmacia){
        cadastroFarmaciaOutput cadastroFarmaciaOutput = new cadastroFarmaciaOutput();

        farmaciaEntity atualizarFarmacia = farmaciaRepository.findById(id).get();
        atualizarFarmacia.setRazao_social(farmacia.getRazao_social());
        atualizarFarmacia.setCnpj(farmacia.getCnpj());
        atualizarFarmacia.setNome_fantasia(farmacia.getNome_fantasia());
        atualizarFarmacia.setEmail(farmacia.getEmail());
        atualizarFarmacia.setTelefone(farmacia.getTelefone());
        atualizarFarmacia.setCelular(farmacia.getCelular());
        atualizarFarmacia.getEndereco().setCep(farmacia.getEndereco().getCep());
        atualizarFarmacia.getEndereco().setNumero(farmacia.getEndereco().getNumero());
        atualizarFarmacia.getEndereco().setLogradouro(farmacia.getEndereco().getLogradouro());
        atualizarFarmacia.getEndereco().setComplemento(farmacia.getEndereco().getComplemento());
        atualizarFarmacia.getEndereco().setBairro(farmacia.getEndereco().getBairro());
        atualizarFarmacia.getEndereco().setCidade(farmacia.getEndereco().getCidade());
        atualizarFarmacia.getEndereco().setEstado(farmacia.getEndereco().getEstado());
        atualizarFarmacia.getEndereco().setLongitude(farmacia.getEndereco().getLongitude());
        atualizarFarmacia.getEndereco().setLatitude(farmacia.getEndereco().getLatitude());
        cadastroFarmaciaOutput.setDados(atualizarFarmacia);

        farmaciaRepository.save(atualizarFarmacia);
        return new ResponseEntity<>(new cadastroFarmaciaOutput(Response.SC_CREATED, "Farmacia Atualizada!", cadastroFarmaciaOutput.getDados()), HttpStatus.CREATED);

    }
    public Boolean validarFarmacia(@RequestBody farmaciaEntity farmacia, @RequestBody enderecoEntity endereco){
        if(farmacia.getRazao_social() == null || farmacia.getCnpj() == null || farmacia.getNome_fantasia() == null || farmacia.getEmail() == null || farmacia.getCelular() == null || endereco.getNumero() == null || endereco.getLatitude() == null || endereco.getLongitude() == null)  {
          return false;
    }else if(farmacia.getRazao_social() == "" || farmacia.getCnpj() == "" || farmacia.getNome_fantasia() == "" || farmacia.getEmail() == "" || farmacia.getCelular() == "" || endereco.getLatitude() == "" || endereco.getLongitude() == "") {
            return false;
        }else {
         return true;
        }
}
}
