package br.eduardobraz.sprint4.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssociatePartido {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @ManyToOne
        private Associate associate;
        @ManyToOne
        private Partido partido;

        public void setIdAssociate(Long id){
                this.associate.setId(id);
        }

        public void setIdPartido(Long id){
                this.partido.setId(id);
        }

}
