package com.example.demo.n1problem;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "account")
@Getter
@Setter
public class Account {

  @Id
  @Column(name = "account_number")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long accountId;

  @ManyToOne
//       (fetch = FetchType.LAZY)
  @JoinColumn(name = "t_mk_user_mku_id")
  private User owner;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ref_account_types_account_type_code")
//    private AccountType accountType;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ref_account_status_account_status_code")
//    private AccountStatus accountStatus;

//  @Column(name = "account_current_balance")
//  private Integer balance;

  @Override
  public String toString() {
    return "Account [accountNumber=" + accountId + ", owner=" + owner.getUsername() + "]";
  }
}
