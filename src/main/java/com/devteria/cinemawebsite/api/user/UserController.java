package com.devteria.cinemawebsite.api.user;

import com.devteria.cinemawebsite.api.user.dto.request.UserCreationRequest;
import com.devteria.cinemawebsite.api.user.dto.response.UserResponse;
import com.devteria.cinemawebsite.api.user.service.UserService;
import com.devteria.cinemawebsite.base.ApiResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserController {
    UserService userService;

    @PostMapping("/register")
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(request))
                .build();
    }

}
