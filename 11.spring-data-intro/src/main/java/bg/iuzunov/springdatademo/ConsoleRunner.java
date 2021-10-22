package bg.iuzunov.springdatademo;

import bg.iuzunov.springdatademo.services.AccountService;
import bg.iuzunov.springdatademo.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ConsoleRunner implements CommandLineRunner {

    private final UserService userService;
    private final AccountService accountService;

    public ConsoleRunner(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void run(String... args) throws Exception {

        /*this.userService.register(
                "Gosho",
                25,
                new BigDecimal(1000)
        );*/

        //this.userService.addAccount(new BigDecimal(500), 1L);

        //this.accountService.withdrawMoney(BigDecimal.valueOf(100), 5L);

        this.accountService.transferMoney(BigDecimal.valueOf(0), 6L);


    }
}
