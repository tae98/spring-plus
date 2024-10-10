package org.example.expert.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.example.expert.domain.common.annotation.Auth;
import org.example.expert.domain.common.dto.AuthUser;
import org.example.expert.domain.user.dto.request.UserChangePasswordRequest;
import org.example.expert.domain.user.dto.response.UserResponse;
import org.example.expert.domain.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable long userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @PutMapping("/users")
    public void changePassword(@AuthenticationPrincipal AuthUser authUser, @RequestBody UserChangePasswordRequest userChangePasswordRequest) {
        userService.changePassword(authUser.getId(), userChangePasswordRequest);
    }
    //랜덤 유저 100만개 생성
    @PostMapping("/users/create/million")
    public void createMillionUsers(){
        userService.createMillionUsers();
    }

    @GetMapping("/users/get/advanced/{nickname}")
    public ResponseEntity<Long> getUserFromMillion(@PathVariable String nickname) {
        return ResponseEntity.ok(userService.getUserFromMillion(nickname));
    }

    @GetMapping("/users/get/{nickname}")
    public ResponseEntity<Long> getUserByNickname(@PathVariable String nickname) {
        return ResponseEntity.ok(userService.getUserByNickname(nickname));
    }
}
