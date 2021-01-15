package tech.arao.minibank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.arao.minibank.data.domain.Account;
import tech.arao.minibank.data.domain.Transaction;
import tech.arao.minibank.data.domain.User;
import tech.arao.minibank.data.repository.UserRepository;
import tech.arao.minibank.exception.ResourceNotFoundException;
import tech.arao.minibank.exception.UserAccountNotFoundException;
import tech.arao.minibank.exception.UserTransactionNotFoundException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bank/users")
public class UserRESTController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/all")
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable(value = "id") Long userId) {
        return userRepository.findById(userId).orElseThrow(()
                -> new ResourceNotFoundException("User", "id", userId));
    }

    @PostMapping("/create")
    public User create(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable(value = "id") Long userId, @RequestParam("name") String name) {

        User user = userRepository.findById(userId).orElseThrow(()
                -> new ResourceNotFoundException("User", "id", userId));

        user.setName(name);

        User updatedUser = userRepository.save(user);

        return updatedUser;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()
                -> new ResourceNotFoundException("User", "id", userId));

        userRepository.delete(user);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/accounts")
    public List<Account> getAllAccountsByUser(@PathVariable(value = "id") Long userId) {
        Optional<User> foundUser = userRepository.findById(userId);
        if (foundUser.isPresent()) {
            User user = foundUser.get();
            return user.getAccounts();
        }
        throw new UserAccountNotFoundException("User", "id", userId);
    }

    @GetMapping("/{id}/transactions")
    public List<Transaction> getAllTransactionsByUser(@PathVariable(value = "id") Long userId) {
        Optional<User> foundUser = userRepository.findById(userId);
        if (foundUser.isPresent()) {
            User user = foundUser.get();
            return user.getTransactions();
        }
        throw new UserTransactionNotFoundException("User", "id", userId);
    }
}
