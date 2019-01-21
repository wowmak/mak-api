package org.mak.service;

import org.dozer.DozerBeanMapper;
import org.mak.domain.UserRecord;
import org.mak.error.DuplicateUsernameException;
import org.mak.model.UserDTO;
import org.mak.repository.UserRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class MakService {
    @Autowired
    UserRecordRepository userRecordRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    DozerBeanMapper mapper;

    Logger logger = LoggerFactory.getLogger(MakService.class);

    public void signUp(UserDTO userDTO) throws DuplicateUsernameException {
        mapper = new DozerBeanMapper();
        UserRecord userRecord = mapper.map(userDTO, UserRecord.class);
        userRecord.setPassword(bCryptPasswordEncoder.encode(userRecord.getPassword()));
        if (userRecordRepository.findByUsername(userDTO.getUsername()) != null) {
            throw new DuplicateUsernameException(userDTO.getUsername());
        }
        userRecordRepository.save(userRecord);

    }
}
