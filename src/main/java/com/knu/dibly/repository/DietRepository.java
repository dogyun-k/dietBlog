package com.knu.dibly.repository;

import com.knu.dibly.domain.diet.Diet;
import com.knu.dibly.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface DietRepository extends JpaRepository<Diet, Long> {

    List<Diet> findAllByUserOrderByIdDesc(User user);

    List<Diet> findAllByUserAndCreateDate(User user, Date createDate);

}
