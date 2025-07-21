package jesper.summer.service;

import jesper.summer.dto.PersonCreateDTO;
import jesper.summer.dto.PersonResponseDTO;
import jesper.summer.dto.PersonUpdateByNameDTO;
import jesper.summer.exception.BaiduApiException;
import jesper.summer.exception.BusinessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PersonService {
    @Transactional
    PersonResponseDTO createPerson(PersonCreateDTO dto) throws BusinessException;

    void deleteByName(String name) throws BaiduApiException;


    PersonResponseDTO updatePersonByName(PersonUpdateByNameDTO dto) throws BusinessException;

    PersonResponseDTO getPersonDetailsByName(String name) throws BusinessException;

    Page<PersonResponseDTO> getAllPersons(Pageable pageable);

    int batchDeleteByName(List<String> names) throws BusinessException, BaiduApiException;
}