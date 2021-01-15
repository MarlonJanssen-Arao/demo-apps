package tech.arao.minibank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.arao.minibank.data.domain.Account;
import tech.arao.minibank.data.repository.AccountRepository;
import tech.arao.minibank.exception.BadRequestException;
import tech.arao.minibank.exception.ConflictException;
import tech.arao.minibank.exception.ResourceNotFoundException;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bank/accounts")
public class AccountRESTController {

    @Autowired
    private AccountRepository repository;


    @GetMapping("/all")
    public List<Account> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable(value = "id") Long accountId) {
        return repository.findById(accountId).orElseThrow(()
                -> new ResourceNotFoundException("Account", "id", accountId));
    }

    @PostMapping("/create")
    public Account create(@Valid @RequestBody Account account) {
        return repository.save(account);
    }

    @PostMapping("/{id}/deposit")
    public Account deposit(@PathVariable(value = "id") Long accountId, @RequestParam("amount") BigDecimal amount) {
        Optional<Account> foundAccount = repository.findById(accountId);

        if (foundAccount.isPresent()) {
            Account account = foundAccount.get();

            account.setBalance(account.getBalance().add(amount, new MathContext(5)));

            Account updatedAccount = repository.save(account);

            return updatedAccount;
        }

        throw new ResourceNotFoundException("Account", "id", accountId);
    }

    @PostMapping("/{id}/withdraw")
    public Account withdraw(@PathVariable(value = "id") Long accountId, @RequestParam("amount") BigDecimal amount) {
        Optional<Account> foundAccount = repository.findById(accountId);

        if (foundAccount.isPresent()) {
            Account account = foundAccount.get();

            if (account.getBalance().compareTo(amount) >= 0) {
                account.setBalance(account.getBalance().add(amount, new MathContext(5)));

                Account updatedAccount = repository.save(account);

                return updatedAccount;
            }

            throw new BadRequestException("You're trying to withdraw an amount greater than your account's balance.");
        }

        throw new ResourceNotFoundException("Account", "id", accountId);
    }

    @PostMapping("/{id}/transfer")
    public Account transfer(@PathVariable(value = "id") Long id,
                            @RequestParam("toAccountId") Long toAccountId, @RequestParam("amount") BigDecimal amount) {
        Optional<Account> foundSourceAccount = repository.findById(id);
        Optional<Account> foundTargetAccount = repository.findById(toAccountId);

        if (foundSourceAccount.isPresent()) {
            if (foundTargetAccount.isPresent()) {
                Account sourceAccount = foundSourceAccount.get();

                if (sourceAccount.getBalance().compareTo(amount) >= 0) {
                    Account targetAccount = foundTargetAccount.get();

                    targetAccount.setBalance(targetAccount.getBalance().subtract(amount, new MathContext(5)));

                    Account updatedAccount = repository.save(sourceAccount);

                    if (updatedAccount != null) {
                        sourceAccount.setBalance(sourceAccount.getBalance().add(amount, new MathContext(5)));
                        updatedAccount = repository.save(sourceAccount);

                        return repository.findById(id).orElseThrow(()
                                -> new ResourceNotFoundException("Account", "id", id));
                    }

                    throw new ConflictException("Unable to update the source account, terminating request.");
                }

                throw new BadRequestException("You're trying to transfer an amount greater than your account's balance.");
            }

            throw new ResourceNotFoundException("Account", "id", toAccountId);
        }

        throw new ResourceNotFoundException("Account", "id", id);
    }

    @PutMapping("/{id}")
    public Account updateBalance(@PathVariable(value = "id") Long accountId, @RequestParam("balance") BigDecimal balance) {

        Account account = repository.findById(accountId).orElseThrow(()
                -> new ResourceNotFoundException("Account", "id", accountId));

        account.setBalance(balance);
        Account updatedAccount = repository.save(account);

        return updatedAccount;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long accountId) {
        Account account = repository.findById(accountId).orElseThrow(()
                -> new ResourceNotFoundException("Account", "id", accountId));

        repository.delete(account);

        return ResponseEntity.ok().build();
    }
}
