package bg.iuzunov.springdatademo.services;

import bg.iuzunov.springdatademo.exceptions.UserAlreadyExistsException;
import bg.iuzunov.springdatademo.exceptions.UserNotFoundException;

import java.math.BigDecimal;

public interface UserService {

    void register(
            String username,
            int age,
            BigDecimal initialAmount
    ) throws UserAlreadyExistsException;

    void addAccount(
            BigDecimal startingAmount,
            Long id
    ) throws UserNotFoundException;

}
