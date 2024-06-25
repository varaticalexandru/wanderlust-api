package org.alexv.wanderlustapi.model.persistence.repository;

import org.alexv.wanderlustapi.api.dto.user.UserDetailsDto;
import org.alexv.wanderlustapi.api.dto.user.UserInfoDetails;
import org.alexv.wanderlustapi.model.persistence.entity.UserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserInfoRepository extends MongoRepository<UserInfo, String> {

    Optional<UserInfo> findByEmail(String email);

    Optional<UserDetailsDto> findDetailsById(String id);

    Optional<UserDetailsDto> findDetailsByEmail(String email);
}
