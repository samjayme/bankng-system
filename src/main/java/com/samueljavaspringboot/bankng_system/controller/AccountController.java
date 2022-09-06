package com.samueljavaspringboot.bankng_system.controller;

import com.samueljavaspringboot.bankng_system.UserDto;
import com.samueljavaspringboot.bankng_system.dto.AccountDTO;
import com.samueljavaspringboot.bankng_system.dto.DepositForm;
import com.samueljavaspringboot.bankng_system.dto.WithdrawalForm;
import com.samueljavaspringboot.bankng_system.service.AccountService;
import com.samueljavaspringboot.bankng_system.service.AccountServiceImp;
import com.samueljavaspringboot.bankng_system.service.UserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

    private final AccountService  accountService;
    private final UserService userService;

    public AccountController(final AccountServiceImp accountServiceImp, UserService userService) {
        this.accountService = accountServiceImp;
        this.userService = userService;
    }
    @PostMapping("/signUp")
    public ResponseEntity<Void> signUp (@RequestBody UserDto userDto){
        userService.createUser(userDto);
        return ResponseEntity.ok().build();

    }
    @PostMapping("/deposit")
    public ResponseEntity<Void> deposit(@RequestBody DepositForm depositForm){
        accountService.deposit(depositForm);
        return  ResponseEntity.ok().build();

    }
    @PostMapping("/withdraw")
    public ResponseEntity<Void> withdraw(@RequestBody WithdrawalForm withdrawalForm){
        accountService.withdraw(withdrawalForm);
        return  ResponseEntity.ok().build();

    }



    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        return ResponseEntity.ok(accountService.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable final Long id) {
        return ResponseEntity.ok(accountService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createAccount(@RequestBody @Valid final AccountDTO accountDTO) {
        return new ResponseEntity<>(accountService.create(accountDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAccount(@PathVariable final Long id,
            @RequestBody @Valid final AccountDTO accountDTO) {
        accountService.update(id, accountDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteAccount(@PathVariable final Long id) {
        accountService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
