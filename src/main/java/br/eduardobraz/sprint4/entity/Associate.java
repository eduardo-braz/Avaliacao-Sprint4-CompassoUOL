package br.eduardobraz.sprint4.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Associate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private CargoPoliticoEnum cargo;
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;      // Sexo
    @Basic                          // Rever anotação em caso de erro em datas
    @Temporal(TemporalType.DATE)
    private Date birthDate;         // Data de nascimento


}
