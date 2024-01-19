package com.example.demo.n1problem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Administrator of Master Key
 *
 * @author Z.DRISSI
 */
@Getter
@Setter
@Entity
@Table(name = "t_mk_user")
public class User {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  /**
   * Master Key User ID
   */
  @Id
  @Column(name = "mku_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Master Key Login
   */
  @Column(name = "mku_username")
  private String username;

  /**
   * First Name
   */
  @Column(name = "mku_first_name")
  private String firstName;

  /**
   * Last Name
   */
  @Column(name = "mku_last_name")
  private String lastName;

  /**
   * Email address
   */
  @Column(name = "mku_email")
  private String email;

  /**
   * Password Hash
   */
  @Column(name = "mku_password_hash")
  private String password;

  /**
   * Is the Master Key User Active
   */
  @Column(name = "mku_is_enabled")
  private boolean enabled;

//  @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
//  @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {
//      @JoinColumn(name = "role_id")})
//  private Set<Role> authorities = new HashSet<>();

  @OneToMany(mappedBy = "owner")
  private List<Account> accounts = new ArrayList<>();

  /**
   * Add account to the user for bi-directional relationship
   */
  public void add(Account account) {
    if (accounts == null) {
      accounts = new ArrayList<>();
    }
    accounts.add(account);
    account.setOwner(this);
  }

  @Override
  public String toString() {
    return "User [id=" + id + ", username=" + username + ", firstName=" + firstName + ", lastName=" + lastName
        + ", email=" + email + ", password=" + password +  "]";
  }
}
