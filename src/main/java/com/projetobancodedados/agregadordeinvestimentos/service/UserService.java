package com.projetobancodedados.agregadordeinvestimentos.service;

import com.projetobancodedados.agregadordeinvestimentos.controller.dto.CreateAccountDto;
import com.projetobancodedados.agregadordeinvestimentos.controller.dto.CreateUserDto;
import com.projetobancodedados.agregadordeinvestimentos.controller.dto.UpadeUserDto;
import com.projetobancodedados.agregadordeinvestimentos.entity.Account;
import com.projetobancodedados.agregadordeinvestimentos.entity.BillingAddress;
import com.projetobancodedados.agregadordeinvestimentos.entity.User;
import com.projetobancodedados.agregadordeinvestimentos.repository.AccountRepository;
import com.projetobancodedados.agregadordeinvestimentos.repository.BillingAddressRepository;
import com.projetobancodedados.agregadordeinvestimentos.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.Objects.isNull;

@Service
public class UserService {
    private UserRepository userRepository;
    private AccountRepository accountRepository;
    private BillingAddressRepository billingAddressRepository;
    public UserService(UserRepository userRepository, AccountRepository accountRepository, BillingAddressRepository billingAddressRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.billingAddressRepository = billingAddressRepository;
    }

    public UUID createUser(CreateUserDto createUserDto){
        // DTO -> entity
        var entity = new User(
                UUID.randomUUID(),
                createUserDto.username(),
                createUserDto.email(),
                createUserDto.password(),
                Instant.now(),
                null
        );
        var userSaved = userRepository.save(entity);
        return userSaved.getUserid();
    }
    public Optional<User> getUserById(String userId){
        return userRepository.findById(UUID.fromString(userId));

    }
    public List<User> listUsers(){
        return userRepository.findAll();
    }
    public void updateUserById(String userId,
                               UpadeUserDto upadeUserDto){
        var id = UUID.fromString(userId);
        var userEntity = userRepository.findById(id);
        if(userEntity.isPresent())
        {
            var user = userEntity.get();
            if(upadeUserDto.username() != null)
                user.setUsername(upadeUserDto.username());
            if(upadeUserDto.password() != null)
                user.setPassword(upadeUserDto.password());
            userRepository.save(user);
        }
    }
    public void deletarById(String userId){
        var id = UUID.fromString(userId);
        var userExitents = userRepository.existsById(id);
        if(userExitents)
            userRepository.deleteById(id);
    }

    public void createAccount(String userId, CreateAccountDto createAccountDto) {
        var user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (isNull(user.getAccountList())) {
            user.setAccountList(new ArrayList<>());
        }

        var account = new Account(
                UUID.randomUUID(),
                user,
                null,
                createAccountDto.description(),
                new ArrayList<>()
        );


        var accountCreated = accountRepository.save(account);


        var bilingAdress = new BillingAddress(
                accountCreated.getAccountId(),
                accountCreated,
                createAccountDto.street(),
                createAccountDto.number()
        );


        billingAddressRepository.save(bilingAdress);
    }

    public List<Account> listAccounts(String userId) {
        var user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND));
        return user.getAccountList();
    }
}
