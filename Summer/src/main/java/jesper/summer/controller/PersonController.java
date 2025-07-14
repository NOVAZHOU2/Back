package jesper.summer.controller;

import jesper.summer.dto.*;
import jesper.summer.exception.BusinessException;
import jesper.summer.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<?> createPerson(@RequestBody PersonCreateDTO dto) {
        try {
            PersonResponseDTO response = personService.createPerson(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (BusinessException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("系统错误");
        }
    }
    @DeleteMapping("/by-name")
    public ResponseEntity<OperationResultDTO> deleteByName(
            @Valid @RequestBody PersonDeleteDTO request) {

        String name = request.getName();
        OperationResultDTO response;

        try {
            personService.deleteByName(name);

            response = new OperationResultDTO(
                    "DELETE",
                    name,
                    HttpStatus.OK.value(),
                    "成功删除姓名为 [" + name + "] 的记录",
                    LocalDateTime.now()
            );
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response = new OperationResultDTO(
                    "DELETE",
                    name,
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "系统错误: 删除操作未完成",
                    LocalDateTime.now()
            );
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @PatchMapping("/by-name")
    public ResponseEntity<PersonResponseDTO> updateByName(
            @Valid @RequestBody PersonUpdateByNameDTO dto
    ) throws BusinessException {
        return ResponseEntity.ok(personService.updatePersonByName(dto));
    }
}