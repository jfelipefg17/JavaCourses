package org.example;

import org.example.exception.NoMoney;

import java.math.BigDecimal;

public class Account {

  private String person;
  private BigDecimal balance;


  public Account() {
  }

  public Account(String person, BigDecimal balance) {
    this.person = person;
    this.balance = balance;
  }

  public String getPerson() {
    return person;
  }

  public void setPerson(String person) {
    this.person = person;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }


  public void debit(BigDecimal amount) {
    BigDecimal newBalance = this.balance.subtract(amount);
    if (newBalance.compareTo(BigDecimal.ZERO) < 0){
      throw new NoMoney("No Balance");
    }
    this.balance = newBalance;
  }

  public void credit(BigDecimal amount) {
    this.balance = this.balance.add(amount);
  }


  @Override
  public boolean equals(Object obj) {

    if (!(obj instanceof Account)) {
      return false;
    }

    Account c = (Account) obj;

    if (this.person == null || this.balance == null) {
      return false;
    }
    return this.person.equals(c.getPerson()) && this.balance.equals(c.getBalance());
  }
}