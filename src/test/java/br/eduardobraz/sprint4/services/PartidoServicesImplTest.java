package br.eduardobraz.sprint4.services;

import br.eduardobraz.sprint4.controller.AssociateControllerTest;
import br.eduardobraz.sprint4.controller.PartidoControllerTest;
import br.eduardobraz.sprint4.dto.PartidoDTO;
import br.eduardobraz.sprint4.entity.IdeologyEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
@AutoConfigureMockMvc
public class PartidoServicesImplTest {

    @Autowired
    private IdeologyEnum ideologyEnum;

    @Autowired
    private AssociateControllerTest assController;

    @Autowired
    private PartidoControllerTest ptController;


}
