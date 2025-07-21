package jesper.summer.service.impl;

import jesper.summer.config.BaiduFaceResultHandler;
import jesper.summer.dto.PersonCreateDTO;
import jesper.summer.dto.PersonResponseDTO;
import jesper.summer.dto.PersonUpdateByNameDTO;
import jesper.summer.entity.*;
import jesper.summer.exception.BaiduApiException;
import jesper.summer.exception.BusinessException;
import jesper.summer.repository.*;
import jesper.summer.service.PersonService;
import jesper.summer.utils.FaceUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonDetailRepository personDetailRepository;
    private final FaceDataRepository faceDataRepository;
    private final AccessLogRepository accessLogRepository;
    private final FaceUtils faceUtils;

    public PersonServiceImpl(PersonRepository personRepository,
                             PersonDetailRepository personDetailRepository,
                             FaceDataRepository faceDataRepository, AccessLogRepository accessLogRepository, FaceUtils faceUtils) {
        this.personRepository = personRepository;
        this.personDetailRepository = personDetailRepository;
        this.faceDataRepository = faceDataRepository;
        this.accessLogRepository = accessLogRepository;
        this.faceUtils = faceUtils;
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchDeleteByName(List<String> names) throws BusinessException, BaiduApiException {
        if (CollectionUtils.isEmpty(names)) {
            throw new BusinessException("姓名列表不能为空");
        }

        int totalDeleted = 0;
        for (String name : names) {
            // 1. 查询主表ID（避免重复查询）
            FaceData faceData = faceDataRepository.findByPersonName(name);
            BaiduFaceResultHandler.handleResult(faceUtils.deleteFace(String.valueOf(faceData.getPersonId()),faceData.getGroupId(),faceData.getFaceToken()));
            accessLogRepository.deleteByPersonName(name);
            faceDataRepository.deleteFaceDataByName(name);
            personDetailRepository.deleteDetailByName(name);

            // 最后删除主表数据
            personRepository.deleteByName(name);
        }
        return totalDeleted;
    }
    @Override
    @Transactional
    public PersonResponseDTO createPerson(PersonCreateDTO dto) throws BusinessException {
        // 检查身份证号是否已存在
        if (personDetailRepository.existsByIdCard(dto.getIdCard())) {
            throw new BusinessException("身份证号已存在");
        }

        // 保存Person
        Person person = new Person();
        person.setName(dto.getName());
        person = personRepository.save(person);

        // 保存PersonDetail
        PersonDetail detail = new PersonDetail();
        BeanUtils.copyProperties(dto, detail);
        detail.setPerson(person);
        detail.setRegisterTime(LocalDateTime.now());
        detail.setUpdateTime(LocalDateTime.now());
        personDetailRepository.save(detail);

        // 返回结果
        PersonResponseDTO response = new PersonResponseDTO();
        BeanUtils.copyProperties(detail, response);
        response.setPersonId(person.getId());
       // response.setFaceData(new PersonResponseDTO.FaceDataDTO(faceData.getFaceToken(), faceData.getGroupId(), faceData.getLogId()));
        return response;
    }

    @Transactional(readOnly = true)
    public PersonResponseDTO getPersonDetailsByName(String name) throws BusinessException {
        // 单次查询获取所有关联数据
        Long id = personRepository.findIdByName(name)
                .orElseThrow(() -> new BusinessException("用户不存在"));
      //  Person person = personRepository.findByNameWithAssociations(name);

        return convertToResponseDTO(Objects.requireNonNull(personRepository.findById(id).orElse(null)));
    }
    @Override
    @Transactional
    public void deleteByName(String name) throws BaiduApiException {
        // 先删除关联数据
        FaceData faceData = faceDataRepository.findByPersonName(name);
        BaiduFaceResultHandler.handleResult(faceUtils.deleteFace(String.valueOf(faceData.getPersonId()),faceData.getGroupId(),faceData.getFaceToken()));
        accessLogRepository.deleteByPersonName(name);
        faceDataRepository.deleteFaceDataByName(name);
        personDetailRepository.deleteDetailByName(name);

        // 最后删除主表数据
        personRepository.deleteByName(name);
    }

    @Override
    public PersonResponseDTO updatePersonByName(PersonUpdateByNameDTO dto) throws BusinessException {
        // 1. 验证用户存在并获取ID
        Long personId = personRepository.findIdByName(dto.getName())
                .orElseThrow(() -> new BusinessException("用户不存在"));

        // 2. 更新PersonDetail（仅更新非空字段）
        personDetailRepository.updateSelectiveByPersonId(
                personId,
                dto.getGender(),
                dto.getIdCard(),
                dto.getPhone(),
                dto.getPosition(),
                dto.getStatus()
        );

        // 3. 处理FaceData（存在则更新，不存在则插入）
        if (dto.getFaceData() != null) {
            int updatedRows = faceDataRepository.upsertFaceData(
                    personId,
                    dto.getFaceData().getFaceToken(),
                    dto.getFaceData().getGroupId(),
                    dto.getFaceData().getLogId(),
                    dto.getFaceData().getFaceUrl()
            );
        }
        return convertToResponseDTO(Objects.requireNonNull(personRepository.findById(personId).orElse(null)));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PersonResponseDTO> getAllPersons(Pageable pageable) {
        Page<Person> personPage = personRepository.findAllWithAssociations(pageable);
        return personPage.map(this::convertToResponseDTO);
    }
    private PersonResponseDTO convertToResponseDTO(Person person) {
        PersonDetail detail = person.getDetail();
        FaceData faceData = faceDataRepository.findByPersonId(person.getId()).orElse(null);

        return PersonResponseDTO.builder()
                .personId(person.getId())
                .name(person.getName())
                .gender(detail.getGender())
                .idCard(detail.getIdCard())
                .phone(detail.getPhone())
                .position(detail.getPosition())
                .status(detail.getStatus())
                .registerTime(detail.getRegisterTime())
                .faceData(faceData != null ?
                        new PersonResponseDTO.FaceDataDTO(
                                faceData.getFaceToken(),
                                faceData.getGroupId(),
                                faceData.getLogId(),
                                faceData.getFaceUrl()
                        ) : null
                )
                .build();
    }
}
