package tech.devinhouse.pharmacySecurity.Dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.devinhouse.pharmacySecurity.Entity.usuarioEntity;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class usuarioOutput {

    private int status_code;
    private String mensagem;
    private usuarioEntity dados;



}
