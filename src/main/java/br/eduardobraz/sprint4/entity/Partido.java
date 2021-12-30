package br.eduardobraz.sprint4.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Partido {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String initials;        // Sigla
    @Enumerated(EnumType.STRING)
    private IdeologyEnum ideology;
    @Basic                          // Rever anotação em caso de erro em datas
    @Temporal(TemporalType.DATE)
    private Date foundationDate;
}
