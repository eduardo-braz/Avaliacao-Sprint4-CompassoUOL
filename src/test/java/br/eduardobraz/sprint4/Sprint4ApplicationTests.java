package br.eduardobraz.sprint4;

import br.eduardobraz.sprint4.dto.AssociateDTO;
import br.eduardobraz.sprint4.dto.PartidoDTO;
import br.eduardobraz.sprint4.entity.CargoPoliticoEnum;
import br.eduardobraz.sprint4.entity.GenderEnum;
import br.eduardobraz.sprint4.entity.IdeologyEnum;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootTest
class Sprint4ApplicationTests {

	final Long id = (long) 15;
	final String name = "Partido de Teste";
	final String initial = "PDTE";
	final IdeologyEnum ideology = IdeologyEnum.Direita;
	final Date dateFoundation = new Date("2021-28-12");

	@Bean
	public ModelMapper mapper(){
		return  new ModelMapper();
	}

	@Bean
	public PartidoDTO createPartidoDto(){
		return new PartidoDTO(id, name, initial, ideology, dateFoundation);
	}

	@Bean
	public AssociateDTO createAssociateDto(){
		return new AssociateDTO(
				(long)5,"Fulano de tal", CargoPoliticoEnum.Nenhum,
				GenderEnum.Masculino, new Date("1910-25-12"));
	}

	@Test
	void contextLoads() {

	}

}
