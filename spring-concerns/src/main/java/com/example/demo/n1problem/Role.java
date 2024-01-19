package com.example.demo.n1problem;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "role")
public class Role {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "role_id")
  private Integer id;

  @Column(name = "name")
  private String name;

  private String description;

  public String getAuthority() {
    return name;
  }
}
