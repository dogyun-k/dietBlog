package com.knu.dibly.repository;

import com.knu.dibly.domain.diet.Diet;
import com.knu.dibly.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DietRepository extends JpaRepository<Diet, Long> {

    List<Diet> findAllByUser(User user);

}
