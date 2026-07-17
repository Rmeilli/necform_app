package org.sid.necform.controller;


import lombok.RequiredArgsConstructor;

import org.sid.necform.dto.request.CreateFormationRequest;
import org.sid.necform.dto.response.FormationResponse;
import org.sid.necform.service.FormationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController

@RequestMapping("/api/formations")

@RequiredArgsConstructor

public class FormationController {

    private final FormationService service;

    @PostMapping
    public FormationResponse create(

            @RequestBody
            CreateFormationRequest request

    ){

        return service
                .create(
                        request
                );

    }

    @GetMapping
    public List<FormationResponse> getAll(){

        return service
                .getAll();

    }

    @GetMapping(
            "/{id}"
    )
    public FormationResponse getById(

            @PathVariable
            UUID id

    ){

        return service
                .getById(
                        id
                );

    }

    @DeleteMapping(
            "/{id}"
    )
    public void delete(

            @PathVariable
            UUID id

    ){

        service
                .delete(
                        id
                );

    }

}
