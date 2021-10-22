package bg.iuzunov.springdatademo.services;

import bg.iuzunov.springdatademo.exceptions.UserAlreadyExistsException;
import bg.iuzunov.springdatademo.exceptions.UserNotFoundException;
import bg.iuzunov.springdatademo.models.Account;
import bg.iuzunov.springdatademo.models.User;
import bg.iuzunov.springdatademo.repositories.AccountRepository;
import bg.iuzunov.springdatademo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    public UserServiceImpl(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public void register(String username, int age, BigDecimal initialAmount) throws UserAlreadyExistsException {

        if (userRepository.existsByUsername(username)) {

            throw new UserAlreadyExistsException();
        }

        var user = new User();
        user.setUsername(username);
        user.setAge(age);

        this.userRepository.save(user);

        var firstAccount = new Account();
        firstAccount.setBalance(initialAmount);

        firstAccount.setUser(user);

        this.accountRepository.save(firstAccount);
    }

    @Override
    public void addAccount(BigDecimal startingAmount, Long id) throws UserNotFoundException {

        User user = this.userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        var account = new Account();
        account.setBalance(startingAmount);
        account.setUser(user);

        this.accountRepository.save(account);

    }
}
