package com.schema.multitenancy.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schema.multitenancy.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

}
