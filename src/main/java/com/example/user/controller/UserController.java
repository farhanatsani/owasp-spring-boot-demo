package com.example.user.controller;

import com.example.base.constants.RspMsgConstants;
import com.example.base.rest.BaseController;
import com.example.base.rest.BaseResponseDTO;
import com.example.user.dto.ChangePasswordDto;
import com.example.user.dto.UserDto;
import com.example.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserController extends BaseController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @Operation(
            summary = "Register new user",
            tags = {"User-API"}
    )
    @ResponseStatus(CREATED)
    @PostMapping
    public ResponseEntity<BaseResponseDTO> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.status(CREATED)
                .body(createResponse(
                        userService.saveUser(userDto),
                        CREATED.value(),
                        RspMsgConstants.constructMessage(userDto.getEmail(), RspMsgConstants.$_SUCCESSFULLY_SAVE)
                ));
    }

    @Operation(
            summary = "Change current password of user",
            tags = {"User-API"}
    )
    @ResponseStatus(OK)
    @PostMapping("/{id}/changepassword")
    public ResponseEntity<BaseResponseDTO> changePassword(@PathVariable("id") UUID id, @RequestBody ChangePasswordDto changePasswordDto) {
        userService.changePassword(id, changePasswordDto.getOldPassword(), changePasswordDto.getNewPassword());
        return ResponseEntity.status(OK)
                .body(createResponse(id.toString(),
                        OK.value(),
                        RspMsgConstants.constructMessage(
                                id.toString(),
                                RspMsgConstants.$_SUCCESSFULLY_CHANGED
                )));
    }

    @Operation(
            summary = "Retrieves list of users",
            tags = {"User-API"}
    )
    @ResponseStatus(OK)
    @GetMapping
    public ResponseEntity<BaseResponseDTO> findAllUsers() {
        List<UserDto> users = userService.findAll();

        if(users.isEmpty()) {
            throw new NullPointerException(RspMsgConstants.DATA_NOT_AVAILABLE);
        }

        return ResponseEntity.status(OK)
                .body(createResponse(
                        users,
                        users.size(),
                        OK.value(),
                        RspMsgConstants.DATA_AVAILABLE
                ));
    }

    @Operation(
            summary = "Get user by ID",
            tags = {"User-API"}
    )
    @ResponseStatus(OK)
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponseDTO> findUserById(@PathVariable("id") UUID id) {
        Optional<UserDto> users = userService.findUserById(id);

        if(users.isEmpty()) {
            throw new NullPointerException(RspMsgConstants.DATA_NOT_AVAILABLE);
        }

        return ResponseEntity.status(OK)
                .body(createResponse(
                        users,
                        OK.value(),
                        RspMsgConstants.DATA_AVAILABLE)
                );
    }

}
