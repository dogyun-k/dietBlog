package com.knu.dibly.repository;

import com.knu.dibly.domain.diet.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadFileRepository extends JpaRepository<UploadFile, Long> {
}
