package org.mak.service;

import org.dozer.DozerBeanMapper;
import org.mak.domain.RegisterRecord;
import org.mak.model.RegisterDTO;
import org.mak.repository.RegisterRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MakService {
    @Autowired
    RegisterRecordRepository registerRecordRepository;

    DozerBeanMapper mapper;

    Logger logger = LoggerFactory.getLogger(MakService.class);

    public void signUp(RegisterDTO registerDTO)
    {
        mapper=new DozerBeanMapper();
        RegisterRecord registerRecord=mapper.map(registerDTO,RegisterRecord.class);
        registerRecordRepository.save(registerRecord);

    }
}
