package com.codecool.queststore.repository;

import com.codecool.queststore.model.StudentItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentItemRepository extends JpaRepository<StudentItem, Long> {

    List<StudentItem> findByStudentId(Long id);

    List<StudentItem> findByStudentIdAndItemId(Long studentId, Long itemId);

}
