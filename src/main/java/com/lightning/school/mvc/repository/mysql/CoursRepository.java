package com.lightning.school.mvc.repository.mysql;

import com.lightning.school.mvc.model.Cours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursRepository extends JpaRepository<Cours, Integer> {
}
