package com.zweirm.wordscounter.repository;

import com.zweirm.wordscounter.domain.Words;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WordsRepository extends JpaRepository<Words, Integer> {
    @Query(value = "SELECT * FROM WORDS;", nativeQuery = true)
    List<Words> countAll();
}
