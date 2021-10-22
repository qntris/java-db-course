package bg.iuzunov.springdatademo.services;

import bg.iuzunov.springdatademo.exceptions.*;
import bg.iuzunov.springdatademo.models.Account;
import bg.iuzunov.springdatademo.repositories.AccountRepository;
import bg.iuzunov.springdatademo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService{

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    public AccountServiceImpl(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public void withdrawMoney(BigDecimal amount, Long id) throws UserNotFoundException, AccountDoesNotExist, InsufficientAccountBalance, AccountIsNotRelatedToAnyUser {



        Account account = this.accountRepository.findById(id).orElseThrow(AccountDoesNotExist::new);

        if (account.getUser() == null) {

            throw new AccountIsNotRelatedToAnyUser();
        }

        if (account.getBalance().compareTo(amount) < 0) {

            throw new InsufficientAccountBalance();

        }

        account.setBalance(account.getBalance().subtract(amount));

        this.accountRepository.save(account);
    }

    @Override
    public void transferMoney(BigDecimal amount, Long id) throws AccountIsNotRelatedToAnyUser, NegativeCreditCannotBeAdded {

        Account account = accountRepository.findById(id).orElseThrow();

        if (account.getUser() == null) {

            throw new AccountIsNotRelatedToAnyUser();
        }

        if (amount.compareTo(BigDecimal.ZERO) < 0) {

            throw new NegativeCreditCannotBeAdded();
        }

        account.setBalance(account.getBalance().add(amount));

        this.accountRepository.save(account);

    }
}
