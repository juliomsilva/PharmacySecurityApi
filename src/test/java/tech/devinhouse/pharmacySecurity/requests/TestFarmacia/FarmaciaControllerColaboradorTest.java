package tech.devinhouse.pharmacySecurity.requests.TestFarmacia;

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
public class FarmaciaControllerColaboradorTest {

    private URI path;
    private MockHttpServletRequest request;
    private ResultMatcher expectedResult;

    @Autowired
    private MockMvc mock;

    public String token;

    @Before
    public void setUp() throws Exception{
        String acesso = "{\"email\": \"will@gmail.com\", \"senha\": \"batera\"}";
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

    public void test_1CadastrarFarmacia() throws Exception {
        String farmacia = "{" +
                "\"razao_social\": \"Cia latino atualizada\"," +
                "\"cnpj\": \"teste80\"," +
                "\"nome_fantasia\": \"teste\"," +
                "\"email\": \"testes\"," +
                "\"telefone\": \"teste\"," +
                "\"celular\": \"teste\"," +
                "\"endereco\": {" +
                "\"cep\": \"05545080\"," +
                "\"logradouro\": \"Rua José Xavier Guimarães\"," +
                "\"numero\": 490," +
                "\"bairro\": \"Jardim Lúcia\"," +
                "\"cidade\": \"São Paulo\"," +
                "\"estado\": \"SP\"," +
                "\"complemento\": \"Em frente ao posto de gasolina\"," +
                "\"latitude\": \"1\"," +
                "\"longitude\": \"80000\"" +
                "}" +
                "}";


        path = new URI("/farmacia");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(path)
                .content(farmacia)
                .header("Content-Type", "application/json")
                .header("Authorization", token);

        expectedResult = MockMvcResultMatchers.status().isCreated();

        mock.perform(request).andExpect(expectedResult);

    }
    @Test

    public void test_2ListarFarmacias() throws Exception {


        path = new URI("/farmacia");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(path)
                .header("Content-Type", "application/json")
                .header("Authorization", token);

        expectedResult = MockMvcResultMatchers.status().isOk();

        mock.perform(request).andExpect(expectedResult);

    }
    @Test
    public void test_3AlterarFarmaciaSemAcesso() throws Exception {
        String farmacia = "{" +
                "\"razao_social\": \"Cia latino atualizada\"," +
                "\"cnpj\": \"teste80\"," +
                "\"nome_fantasia\": \"teste\"," +
                "\"email\": \"testes\"," +
                "\"telefone\": \"teste\"," +
                "\"celular\": \"teste\"," +
                "\"endereco\": {" +
                "\"cep\": \"05545080\"," +
                "\"logradouro\": \"Rua José Xavier Guimarães\"," +
                "\"numero\": 490," +
                "\"bairro\": \"Jardim Lúcia\"," +
                "\"cidade\": \"São Paulo\"," +
                "\"estado\": \"SP\"," +
                "\"complemento\": \"Em frente ao posto de gasolina\"," +
                "\"latitude\": \"1\"," +
                "\"longitude\": \"80000\"" +
                "}" +
                "}";


        path = new URI("/farmacia/1");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put(path)
                .content(farmacia)
                .header("Content-Type", "application/json")
                .header("Authorization", token);

        expectedResult = MockMvcResultMatchers.status().isForbidden();

        mock.perform(request).andExpect(expectedResult);

    }
    @Test
    public void test_4DeletarFarmaciaSemAcesso() throws Exception {



        path = new URI("/farmacia/1");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete(path)
                .header("Content-Type", "application/json")
                .header("Authorization", token);

        expectedResult = MockMvcResultMatchers.status().isForbidden();

        mock.perform(request).andExpect(expectedResult);

    }

    @Test
    public void test_5DeletarFarmaciaInexistenteSemAcesso() throws Exception {



        path = new URI("/farmacia/1");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete(path)
                .header("Content-Type", "application/json")
                .header("Authorization", token);

        expectedResult = MockMvcResultMatchers.status().isForbidden();

        mock.perform(request).andExpect(expectedResult);

    }





}
