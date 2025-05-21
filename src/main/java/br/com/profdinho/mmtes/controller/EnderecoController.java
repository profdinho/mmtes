package br.com.profdinho.mmtes.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.profdinho.mmtes.domain.Endereco;
import br.com.profdinho.mmtes.service.CepService;
import lombok.extern.slf4j.Slf4j;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import static org.springframework.http.ResponseEntity.notFound;


@RestController
@Validated
@RequestMapping({"/enderecos"})
@CrossOrigin(originPatterns = "http://localhost*:*")
@Slf4j
public class EnderecoController {
    @Autowired
    CepService cepService;

    @GetMapping(path = "/cep/{cep}")
    public ResponseEntity<Endereco> findCep(@PathVariable("cep") @NotEmpty @Pattern(regexp = "\\d{8}", message = "CEP deve possuir 8 dígitos") String cep) {
        log.info("Entrou no Endpoint /enderecos/cep/{cep}");
        return Optional
                .ofNullable( cepService.findAddress(cep) )
                .map( ResponseEntity::ok )
                .orElse( notFound().build() );
    }
}
