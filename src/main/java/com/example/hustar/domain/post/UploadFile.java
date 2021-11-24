package com.example.hustar.domain.post;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

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

    protected UploadFile() {
    }

    public UploadFile(String uploadFileName, String storeFileName, String storedPath) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
        this.storedPath = storedPath;
    }

}
