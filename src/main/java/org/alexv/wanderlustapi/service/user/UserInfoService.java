package org.alexv.wanderlustapi.service.user;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.alexv.wanderlustapi.api.dto.user.*;
import org.alexv.wanderlustapi.api.exception.ResourceNotFoundException;
import org.alexv.wanderlustapi.model.persistence.entity.UserInfo;
import org.alexv.wanderlustapi.model.persistence.repository.UserInfoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class UserInfoService implements UserDetailsService {

    UserInfoRepository userInfoRepository;
    PasswordEncoder passwordEncoder;
    ModelMapper modelMapper;

    @Autowired
    public void setUserInfoRepository(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<UserInfo> userInfo = userInfoRepository.findByEmail(email);

        return userInfo.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
    }

    public UserAddResponseDTO addUser(UserInfoDto userInfoDTO) {

        Optional<UserInfo> existingUser = userInfoRepository.findByEmail(userInfoDTO.getEmail());

        if (existingUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with this email already exists: " + userInfoDTO.getEmail());
        }

        UserInfo userInfo = modelMapper.map(userInfoDTO, UserInfo.class);
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfo.setRoles("ROLE_USER");
        UserInfo savedUser = userInfoRepository.save(userInfo);

        return modelMapper.map(savedUser, UserAddResponseDTO.class);
    }

    public UserDetailsDto updateUser(String id, UserUpdateDto userUpdateDto) {
        UserInfo userInfo = userInfoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found: " + id));

        if (!Objects.equals(userInfo.getPassword(), passwordEncoder.encode(userUpdateDto.getCurrentPassword()))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid password");
        }

        userInfo.setId(id);
        userInfo.setFirstName(userUpdateDto.getFirstName());
        userInfo.setLastName(userUpdateDto.getLastName());
        userInfo.setEmail(userUpdateDto.getEmail());
        userInfo.setPassword(passwordEncoder.encode(userUpdateDto.getNewPassword()));

        UserInfo savedUser = userInfoRepository.save(userInfo);
        return modelMapper.map(savedUser, UserDetailsDto.class);
    }



    public UserDto getUserInfoByEmail(String email) {
        UserInfo foundUser = userInfoRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
        return modelMapper.map(foundUser, UserDto.class);
    }

    public UserDto getUserInfoDetailsByEmail(String email) {
        UserInfo foundUser = userInfoRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
        return modelMapper.map(foundUser, UserDto.class);
    }

    public UserDetailsDto getUserInfoById(String id) {
        UserInfo foundUser = userInfoRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found: " + id));
        UserDetailsDto userDetailsDto = modelMapper.map(foundUser, UserDetailsDto.class);
        log.info("User details: {}", userDetailsDto);

        return userDetailsDto;
    }
}



