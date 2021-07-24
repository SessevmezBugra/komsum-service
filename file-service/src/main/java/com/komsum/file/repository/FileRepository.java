package com.komsum.file.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.komsum.file.entity.FileEntity;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, String>{

}
