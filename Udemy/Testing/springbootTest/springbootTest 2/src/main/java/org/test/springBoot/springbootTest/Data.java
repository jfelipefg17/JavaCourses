package org.test.springBoot.springbootTest;

import org.test.springBoot.springbootTest.Entities.Account;
import org.test.springBoot.springbootTest.Entities.Bank;

import java.math.BigDecimal;
import java.util.Optional;

public class Data {

  //public static final Account ACCOUNT_001 = new Account(1L, "Andres", new BigDecimal("1000"));
  //public static final Account ACCOUNT_002 = new Account(2L, "John", new BigDecimal("2000"));

  //public static final Bank BANK = new Bank(1L, "El banco financiero", 0 );


  public static Optional<Account> createAccount001 () {
    return Optional.of(new Account(1L, "Andres", new BigDecimal("1000")));
  }

  public static Optional<Account> createAccount002 () {
    return Optional.of(new Account(2L, "John", new BigDecimal("2000")));
  }

  public static Optional<Bank> createBank () {
    return Optional.of(new Bank(1L, "El banco financiero", 0 ));
  }


}
