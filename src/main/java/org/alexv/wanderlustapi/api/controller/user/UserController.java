package org.alexv.wanderlustapi.api.controller.user;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.alexv.wanderlustapi.api.dto.user.*;
import org.alexv.wanderlustapi.service.jwt.JwtService;
import org.alexv.wanderlustapi.service.user.UserInfoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserInfoService userInfoService;
    JwtService jwtService;
    AuthenticationManager authenticationManager;

    @GetMapping("welcome")
    public String welcome() {
        return "Welcome this endpoint is not secured";
    }

    @PostMapping("/register")
    public UserAddResponseDTO addNewUser(@RequestBody UserInfoDto userInfo) {
        return userInfoService.addUser(userInfo);
    }

    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userProfile() {
        return "Welcome to User Profile";
    }

    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }

    @GetMapping("/user/{id}")
    public UserDetailsDto getUserById(@PathVariable String id) {
        return userInfoService.getUserInfoById(id);
    }

    @GetMapping("/user/email/{email}")
    public UserDto getUserByEmail(@PathVariable String email) {
        return userInfoService.getUserInfoByEmail(email);
    }

    @PutMapping("/user/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public UserDetailsDto updateUser(@PathVariable String id, @RequestBody UserUpdateDto userInfo) {
        return userInfoService.updateUser(id, userInfo);
    }

    @PostMapping("/login")
    public AuthResponseDTO authenticateAndGetToken(@RequestBody AuthRequestDTO authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            UserDto user = userInfoService.getUserInfoByEmail(authRequest.getEmail());
            return new AuthResponseDTO(jwtService.generateToken(authRequest.getEmail()), user.getId(), user.getEmail());
        } else {
            throw new UsernameNotFoundException("Invalid user request !");
        }
    }

}