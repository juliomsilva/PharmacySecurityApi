package tech.devinhouse.pharmacySecurity.Controller;

import tech.devinhouse.pharmacySecurity.Entity.medicamentoEntity;
import tech.devinhouse.pharmacySecurity.Service.medicamentoService;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000 ")
@RequestMapping("/medicamentos")
public class medicamentoController {


    private final medicamentoService medicamentoService;

    public medicamentoController(tech.devinhouse.pharmacySecurity.Service.medicamentoService medicamentoService) {
        this.medicamentoService = medicamentoService;
    }


    @PostMapping
    public ResponseEntity<?> cadastrarMedicamento(@Valid @RequestBody medicamentoEntity medicamento) {
       return medicamentoService.cadastrarMedicamento(medicamento);
    }

    @GetMapping
    public ResponseEntity<?> listarMedicamentos() {
        return medicamentoService.listarMedicamentos();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarMedicamento(@PathVariable Long id, @RequestBody medicamentoEntity medicamento) {
        return medicamentoService.atualizarMedicamento(id,medicamento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarMedicamentoporID(@PathVariable Long id) {
        return medicamentoService.buscarMedicamentoporID(id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarPorID(@PathVariable Long id) {

        return medicamentoService.deletarPorID(id);
    }
}