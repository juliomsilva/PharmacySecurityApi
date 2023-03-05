package tech.devinhouse.pharmacySecurity.Dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.devinhouse.pharmacySecurity.Entity.farmaciaEntity;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class farmaciaidOutput {
    private int status_code;
    private String mensagem;
    private Optional<farmaciaEntity> dados;

}
