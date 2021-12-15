package com.knu.dibly.service;

import com.knu.dibly.domain.diet.Diet;
import com.knu.dibly.domain.diet.dto.DietUpdateRequestDto;
import com.knu.dibly.domain.user.User;
import com.knu.dibly.repository.DietRepository;
import com.knu.dibly.repository.UploadFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DietService {

    private final UploadFileRepository fileRepository;
    private final DietRepository dietRepository;

    public void createDiet(Diet diet) {
        dietRepository.save(diet);
    }

    public Diet readPostById(Long id) {
        return dietRepository.getById(id);
    }

    public List<Diet> readAllDiet() {
        return dietRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Transactional
    public void updateDiet(Long id, DietUpdateRequestDto requestDto) {
        Diet diet = dietRepository.getById(id);
        diet.update(requestDto.getMealType(), requestDto.getContent());
    }

    public void deleteDietById(Long id) {
        Long fileId = dietRepository.getById(id).getUploadFile().getId();
        dietRepository.deleteById(id);
        fileRepository.deleteById(fileId);
    }

    public List<Diet> findAllByUser(User user) {
        return dietRepository.findAllByUserOrderByIdDesc(user);
    }

    public List<Diet> findAllByUserAndCreateDate(User user, Date createDate) {
        return dietRepository.findAllByUserAndCreateDate(user, createDate);
    }

    public Integer totalTodayCalorieByUser(User user, Date today) {
        List<Diet> dietList = this.findAllByUserAndCreateDate(user, today);
        Integer totalCalorie = 0;
        for (Diet diet : dietList) {
            totalCalorie += diet.getCalorieInfo();
        }
        return totalCalorie;
    }

}
