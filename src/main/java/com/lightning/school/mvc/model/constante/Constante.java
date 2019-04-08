package com.lightning.school.mvc.model.constante;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CONSTANTE")
public class Constante implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_CONSTANTE")
    private Integer constanteId;
    @Column(name = "ID_T_CONSTANTE")
    private Integer constenteTypeId;
    @Column(name = "LIBELLE")
    private String label;
    @Column(name = "VALUE")
    private String value;

    public ConstanteTypeEnum getConstanteType(){
        return ConstanteTypeEnum.INT;
    }

}
