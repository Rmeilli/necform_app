package org.sid.necform.service.impl;



import lombok.RequiredArgsConstructor;

import org.sid.necform.dto.request.CreateFormationRequest;
import org.sid.necform.dto.response.FormationResponse;
import org.sid.necform.entity.Formation;
import org.sid.necform.mapper.FormationMapper;
import org.sid.necform.repository.FormationRepository;
import org.sid.necform.service.FormationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class FormationServiceImpl
        implements FormationService {

    private final FormationRepository repository;

    @Override
    public FormationResponse create(
            CreateFormationRequest request
    ){

        Formation formation =
                FormationMapper.toEntity(
                        request
                );

        repository.save(
                formation
        );

        return FormationMapper
                .toResponse(
                        formation
                );

    }

    @Override
    public List<FormationResponse> getAll(){

        return repository
                .findAll()
                .stream()
                .map(
                        FormationMapper::toResponse
                )
                .toList();

    }

    @Override
    public FormationResponse getById(
            UUID id
    ){

        return repository
                .findById(id)

                .map(
                        FormationMapper::toResponse
                )

                .orElseThrow();

    }

    @Override
    public void delete(
            UUID id
    ){

        repository
                .deleteById(
                        id
                );

    }

}
