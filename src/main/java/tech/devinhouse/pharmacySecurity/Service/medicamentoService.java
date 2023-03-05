package tech.devinhouse.pharmacySecurity.Service;

import tech.devinhouse.pharmacySecurity.Repository.medicamentoRepository;
import tech.devinhouse.pharmacySecurity.Dto.*;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import tech.devinhouse.pharmacySecurity.Entity.medicamentoEntity;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class medicamentoService {

    @Autowired
    private medicamentoRepository medicamentoRepository;

    public ResponseEntity<?> cadastrarMedicamento(@Valid @RequestBody medicamentoEntity medicamento) {
        medicamentoOutput medicamentoOutput = new medicamentoOutput();
        if (validarMedicamento(medicamento)) {
            medicamentoOutput.setDados(medicamento);
            medicamentoRepository.save(medicamento);
            return new ResponseEntity<>(new medicamentoOutput(Response.SC_CREATED, "Novo medicamento adicionado", medicamentoOutput.getDados()), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(new ErrorDto(Response.SC_BAD_REQUEST, "Erro!", HttpStatus.CONFLICT, "Verifique se foram preenchidos os campos: nome_medicamento, nome_laboratorio e dosagem"), HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<?> listarMedicamentos() {
        listaMedicamentoOutput listaMedicamentoOutput = new listaMedicamentoOutput();
        List<medicamentoEntity> listamedicamentos = medicamentoRepository.findAll();
        listaMedicamentoOutput.setDados(listamedicamentos);


        if (listamedicamentos.isEmpty()) {
            return new ResponseEntity<>(new ErrorDto(Response.SC_NOT_FOUND, "Erro!", HttpStatus.NOT_FOUND, "Não existem medicamentos cadastrados"), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(new listaMedicamentoOutput(Response.SC_OK, "Dado encontrado!", listaMedicamentoOutput.getDados()), HttpStatus.OK);
        }
    }

    public ResponseEntity<?> atualizarMedicamento(@PathVariable Long id, @RequestBody medicamentoEntity medicamento) {
        medicamentoEntity atualizarMedicamento = medicamentoRepository.findById(id).get();
        medicamentoOutput medicamentoOutput = new medicamentoOutput();

        atualizarMedicamento.setNome_medicamento(medicamento.getNome_medicamento());
        atualizarMedicamento.setNome_laboratorio(medicamento.getNome_laboratorio());
        atualizarMedicamento.setDosagem(medicamento.getDosagem());
        atualizarMedicamento.setDesc_medicamento(medicamento.getDesc_medicamento());
        atualizarMedicamento.setPreco_unitario(medicamento.getPreco_unitario());
        atualizarMedicamento.setTipo_medicamento(medicamento.getTipo_medicamento());

        medicamentoOutput.setDados(atualizarMedicamento);
        medicamentoRepository.save(atualizarMedicamento);

        return new ResponseEntity<>(new medicamentoOutput(Response.SC_OK, "Medicamento Atualizado!", medicamentoOutput.getDados()), HttpStatus.OK);
    }

    public ResponseEntity<?> buscarMedicamentoporID(@PathVariable Long id) {

        medicamentoidOutput medicamentoidOutput = new medicamentoidOutput();
        if (medicamentoRepository.existsById(id)) {
            Optional<medicamentoEntity> buscarmedicamento = medicamentoRepository.findById(id);
            medicamentoidOutput.setDados(buscarmedicamento);

            return new ResponseEntity<>(new medicamentoidOutput(Response.SC_OK, "Dado encontrado!", medicamentoidOutput.getDados()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ErrorDto(Response.SC_NOT_FOUND, "Erro!", HttpStatus.NOT_FOUND, "Não existe medicamento com o id: " + id), HttpStatus.NOT_FOUND);
        }
    }

    public Boolean validarMedicamento(@RequestBody medicamentoEntity medicamento) {
        if (medicamento.getNome_medicamento() == null || medicamento.getNome_laboratorio() == null || medicamento.getDosagem() == null) {
            return false;
        } else if (medicamento.getNome_medicamento() == "" || medicamento.getNome_laboratorio() == "" || medicamento.getDosagem() == "") {
            return false;
        } else {
            return true;
        }
    }

    public ResponseEntity<?> deletarPorID(@PathVariable Long id) {

        if (medicamentoRepository.existsById(id)) {
            medicamentoRepository.deleteById(id);
            return ResponseEntity.accepted().body("Medicamento deletado com sucesso! ID deletado: " + id);

        } else {
            return new ResponseEntity<>(new ErrorDto(Response.SC_NOT_FOUND, "Erro!", HttpStatus.NOT_FOUND, "Não existe medicamento com o id: " + id), HttpStatus.NOT_FOUND);
        }

    }
}
