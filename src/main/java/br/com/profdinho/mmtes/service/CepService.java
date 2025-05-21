package br.com.profdinho.mmtes.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.profdinho.mmtes.domain.Endereco;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CepService {
    private final static String URL = "https://viacep.com.br/ws/";
    private final static String URL_CEP = URL + "{cep}/json";
    RestTemplate restTemplate = new RestTemplate();
    public Endereco findAddress(String cep) {
        log.info("Entrou no método CepService.findAddress(cep)");
        log.info("Aula de Métodos");
        return restTemplate.getForObject(URL_CEP.replace("{cep}", cep), Endereco.class);
    }
}
