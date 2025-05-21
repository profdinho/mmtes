package br.com.profdinho.mmtes.service;

import br.com.profdinho.mmtes.domain.Endereco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class CepServiceTest {

    private RestTemplate restTemplate;
    private CepService cepService;

    @BeforeEach
    void setUp() {
        restTemplate = mock(RestTemplate.class);
        cepService = new CepService();
        // Injetar o mock via reflexão, pois o campo não é final nem privado
        cepService.restTemplate = restTemplate;
    }

    @Test
    void testFindAddressReturnsEndereco() {
        String cep = "01001000";
        Endereco enderecoMock = new Endereco();
        enderecoMock.setCep(cep);
        enderecoMock.setLogradouro("Praça da Sé");
        enderecoMock.setLocalidade("São Paulo");

        when(restTemplate.getForObject(
                ArgumentMatchers.contains(cep),
                eq(Endereco.class)
        )).thenReturn(enderecoMock);

        Endereco result = cepService.findAddress(cep);

        assertNotNull(result);
        assertEquals(cep, result.getCep());
        assertEquals("Praça da Sé", result.getLogradouro());
        assertEquals("São Paulo", result.getLocalidade());
        verify(restTemplate, times(1)).getForObject(anyString(), eq(Endereco.class));
    }

    @Test
    void testFindAddressReturnsNullWhenNotFound() {
        String cep = "99999999";
        when(restTemplate.getForObject(
                ArgumentMatchers.contains(cep),
                eq(Endereco.class)
        )).thenReturn(null);

        Endereco result = cepService.findAddress(cep);

        assertNull(result);
        verify(restTemplate, times(1)).getForObject(anyString(), eq(Endereco.class));
    }
}