package jesper.summer.service;

import jesper.summer.dto.PersonCreateDTO;
import jesper.summer.dto.PersonResponseDTO;
import jesper.summer.dto.PersonUpdateByNameDTO;
import jesper.summer.exception.BusinessException;
import org.springframework.transaction.annotation.Transactional;

public interface PersonService {
    @Transactional
    PersonResponseDTO createPerson(PersonCreateDTO dto) throws BusinessException;

    void deleteByName(String name);


    PersonResponseDTO updatePersonByName(PersonUpdateByNameDTO dto) throws BusinessException;

    PersonResponseDTO getPersonDetailsByName(String name) throws BusinessException;
}