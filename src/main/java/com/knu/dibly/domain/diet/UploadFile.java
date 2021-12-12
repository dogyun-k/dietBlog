package com.knu.dibly.domain.diet;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@NoArgsConstructor
@ToString
@Getter
@Entity
public class UploadFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long id;

    private String uploadFileName;

    private String storeFileName;

    private String storedPath;

    public UploadFile(String uploadFileName, String storeFileName, String storedPath) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
        this.storedPath = storedPath;
    }

}
