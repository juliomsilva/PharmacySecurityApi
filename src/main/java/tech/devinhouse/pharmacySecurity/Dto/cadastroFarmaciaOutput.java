package tech.devinhouse.pharmacySecurity.Dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.devinhouse.pharmacySecurity.Entity.farmaciaEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class cadastroFarmaciaOutput {


    private int status_code;
    private String mensagem;

    private farmaciaEntity dados;


}
