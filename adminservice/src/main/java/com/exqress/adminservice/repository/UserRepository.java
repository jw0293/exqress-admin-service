package com.exqress.adminservice.repository;

import com.exqress.adminservice.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    @Override
    Iterable<UserEntity> findAll();

    UserEntity findByUserId(String userId);
}
