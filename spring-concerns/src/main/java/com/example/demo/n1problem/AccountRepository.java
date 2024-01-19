package com.example.demo.n1problem;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

  @Query("SELECT acc FROM Account acc JOIN FETCH acc.owner")
//  N + 1
//  @Query(value = "select a.*, u.* from Account a LEFT JOIN t_mk_user u ON a.t_mk_user_mku_id=u.mku_id", nativeQuery = true)
//  Solved
//  @Query(value = "select a.* from Account a where a.t_mk_user_mku_id in (select mku_id from t_mk_user)", nativeQuery = true)
  List<Account> findAllAccounts();

//  @EntityGraph(
//      type = EntityGraph.EntityGraphType.FETCH)
//  List<Account> findAll();
}
