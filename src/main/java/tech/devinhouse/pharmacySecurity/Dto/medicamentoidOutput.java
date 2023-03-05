package tech.devinhouse.pharmacySecurity.Dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.devinhouse.pharmacySecurity.Entity.medicamentoEntity;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class medicamentoidOutput {
    private int status_code;
    private String mensagem;
    private Optional<medicamentoEntity> dados;

}
