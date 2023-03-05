package tech.devinhouse.pharmacySecurity.Dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.devinhouse.pharmacySecurity.Entity.medicamentoEntity;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class listaMedicamentoOutput {
    private int status_code;
    private String mensagem;
    private List<medicamentoEntity> dados;

}
