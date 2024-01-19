package com.example.demo.n1problem;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  private final AccountRepository accountRepository;

  public List<User> getUsers() {
    return userRepository.findAll();
  }

  public List<Account> getAccounts() {
    return accountRepository.findAll();
  }

  public List<Account> getAccounts1() {
    return accountRepository.findAllAccounts();
  }

//  public List<Account> getAccounts2() {
//    return accountRepository.findAllAccountGraph();
//  }
}
