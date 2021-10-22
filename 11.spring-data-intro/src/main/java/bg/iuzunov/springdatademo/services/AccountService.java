package bg.iuzunov.springdatademo.services;

import bg.iuzunov.springdatademo.exceptions.*;

import java.math.BigDecimal;

public interface AccountService {

    void withdrawMoney(BigDecimal amount, Long id) throws UserNotFoundException, AccountDoesNotExist, InsufficientAccountBalance, AccountIsNotRelatedToAnyUser;

    void transferMoney(BigDecimal amount, Long id) throws AccountIsNotRelatedToAnyUser, NegativeCreditCannotBeAdded;
}
