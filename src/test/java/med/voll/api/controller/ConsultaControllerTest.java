package med.voll.api.controller;

import med.voll.api.dto.DetalleConsultaDTO;
import med.voll.api.model.consulta.DatosConsulta;
import med.voll.api.model.consulta.ReservaConsultas;
import med.voll.api.model.medico.Especialidad;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LOCAL_DATE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {
    //llamar el controller real
    @Autowired
    private MockMvc mvc;
    @Autowired
    private JacksonTester<DatosConsulta> datosConsultaJson;
    @Autowired
    private JacksonTester<DetalleConsultaDTO> datosConsultaDTO;

    @MockitoBean
    private ReservaConsultas reservaConsultas;

    @Test
    @DisplayName("HTTP 400, cuando no hay datos en el requests")
    @WithMockUser  //as como si ya nos logiamos
    void reservarEscenario1() throws Exception {
        var response =mvc.perform(post("/consultas"))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

    }

    @Test
    @DisplayName("HTTP 200, cuando se reciba un json valido")
    @WithMockUser  //as como si ya nos logiamos
    void reservarEscenario2() throws Exception {
        var fecha = LocalDateTime.now().plusHours(1);
        var especialidad = Especialidad.CARDIOLOGIA;
        var datosDetalle = new DetalleConsultaDTO(null, 2L, 5L, fecha);
        when(reservaConsultas.reservar(any())).thenReturn(datosDetalle);

        var response =mvc.perform(post("/consultas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(datosConsultaJson.write(
                                new DatosConsulta(2L, 5L, fecha,especialidad)
                        ).getJson()))
                .andReturn().getResponse();
        var jsonEsperado = datosConsultaDTO.write(datosDetalle).getJson();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);

    }
}