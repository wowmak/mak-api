package org.mak.repository;

import org.mak.domain.UserRecord;
import org.springframework.data.repository.CrudRepository;

public interface UserRecordRepository extends CrudRepository<UserRecord,Integer> {
   UserRecord findByUsername(String username);
}
