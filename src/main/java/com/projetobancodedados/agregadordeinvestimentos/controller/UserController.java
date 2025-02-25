package com.projetobancodedados.agregadordeinvestimentos.controller;

import com.projetobancodedados.agregadordeinvestimentos.controller.dto.AccountResponseDto;
import com.projetobancodedados.agregadordeinvestimentos.controller.dto.CreateAccountDto;
import com.projetobancodedados.agregadordeinvestimentos.controller.dto.CreateUserDto;
import com.projetobancodedados.agregadordeinvestimentos.controller.dto.UpadeUserDto;
import com.projetobancodedados.agregadordeinvestimentos.entity.User;

import com.projetobancodedados.agregadordeinvestimentos.service.UserService;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/v1/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserDto createUserDto){
        var userId = userService.createUser(createUserDto);
        return ResponseEntity.created(URI.create("/v1/users/"+userId.toString())).build();
    }
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId){
        var user = userService.getUserById(userId);
        if(user.isPresent())
            return ResponseEntity.ok(user.get());
        return ResponseEntity.notFound().build();
    }
    @GetMapping
    public ResponseEntity<List<User>> listUsers(){
        var users = userService.listUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUserById(@PathVariable("userId") String userId,
                                               @RequestBody UpadeUserDto upadeUserDto){
        userService.updateUserById(userId,upadeUserDto);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deletyById(@PathVariable("userId") String userId){
        userService.deletarById(userId);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/{userId}/accounts")
    public ResponseEntity<Void> updateUserById(@PathVariable("userId") String userId,
                                               @RequestBody CreateAccountDto createAccountDto){
        userService.createAccount(userId,createAccountDto);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{userId}/accounts")
    public ResponseEntity<List <AccountResponseDto>> listAccounts(@PathVariable("userId") String userId){
        var accounts = userService.listAccounts(userId)
                .stream()
                .map(ac -> new AccountResponseDto(ac.getAccountId().toString(), ac.getDescription()))
                .toList();
        return ResponseEntity.ok(accounts);
    }
}
