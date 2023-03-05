package tech.devinhouse.pharmacySecurity.Dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.devinhouse.pharmacySecurity.Entity.farmaciaEntity;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class listaFarmaciaOutput {
    private int status_code;
    private String mensagem;
    private List<farmaciaEntity> dados;

}
