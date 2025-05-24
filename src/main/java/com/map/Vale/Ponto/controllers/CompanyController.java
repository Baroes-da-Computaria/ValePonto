package com.map.Vale.Ponto.controllers;

import com.map.Vale.Ponto.model.company.CompanyRequestDTO;
import com.map.Vale.Ponto.model.company.CompanyResponseDTO;
import com.map.Vale.Ponto.services.CompanyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/valeponto/company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<CompanyResponseDTO> geById(@PathVariable("id") Long id) {

        var response = companyService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @GetMapping
    public ResponseEntity<Page<CompanyResponseDTO>> getAll(
            @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize
    ) {

        // instancia do pageable para a busca paginada
        var pageable = PageRequest.of(pageNumber, pageSize);

        // chama o service para a busca paginada
        var page = companyService.findAll(pageable);

        // retorna a pagina de product response
        return ResponseEntity.status(HttpStatus.OK).body(page);

    }

    @PostMapping
    public ResponseEntity<CompanyResponseDTO> create(@RequestBody CompanyRequestDTO dto) {

        var response = companyService.save(dto);

        // retorna o product response
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(
            value = "/{id}"
    )
    public ResponseEntity<CompanyResponseDTO> update(@PathVariable("id") Long id, @RequestBody CompanyRequestDTO dto) {

        var response = companyService.update(id, dto);

        // retorna o curso response
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {

        // chama o service para deletar o client
        companyService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
