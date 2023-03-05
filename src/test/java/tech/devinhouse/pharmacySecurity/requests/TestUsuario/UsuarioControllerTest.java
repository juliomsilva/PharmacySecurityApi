package tech.devinhouse.pharmacySecurity.requests.TestUsuario;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UsuarioControllerTest {

    private URI path;
    private MockHttpServletRequest request;
    private ResultMatcher expectedResult;

    @Autowired
    private MockMvc mock;

    public String token;

    @Before
    public void setUp() throws Exception {
        String acesso = "{\"email\": \"julio@gmail.com\", \"senha\": \"123teste\"}";
        path = new URI("/auth");

        MockHttpServletRequestBuilder request;
        request = MockMvcRequestBuilders.post(path)
                .contentType(MediaType.APPLICATION_JSON).content(acesso);

        expectedResult = MockMvcResultMatchers.status().isOk();

        String response = mock.perform(request).andExpect(expectedResult).andReturn().getResponse()
                .getContentAsString();


        JSONObject data = new JSONObject(response);

        token = data.getString("Token Gerado");
    }

    @Test

    public void CadastrarUsuario() throws Exception {
        String usuario = "{" +
                "\"email\": \"jeni233@gmail.com\"," +
                "\"senha\": \"venv\"," +
                "\"cargos\": [" +
                "{" +
                "\"cargo\":\"ROLE_ADMINISTRADOR\"" +
                "}" +
                "]" +
                "}";


        path = new URI("/usuario/cadastro");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(path)
                .content(usuario)
                .header("Content-Type", "application/json")
                .header("Authorization", token);

        expectedResult = MockMvcResultMatchers.status().isCreated();

        mock.perform(request).andExpect(expectedResult);

    }
    @Test

    public void CadastrarUsuarioJaExistentePorEmail() throws Exception {
        String usuario = "{" +
                "\"email\": \"jeni233@gmail.com\"," +
                "\"senha\": \"venv\"," +
                "\"cargos\": [" +
                "{" +
                "\"cargo\":\"ROLE_ADMINISTRADOR\"" +
                "}" +
                "]" +
                "}";



        path = new URI("/usuario/cadastro");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(path)
                .content(usuario)
                .header("Content-Type", "application/json")
                .header("Authorization", token);



        expectedResult = MockMvcResultMatchers.status().isConflict();

        mock.perform(request).andExpect(expectedResult);



    }
}