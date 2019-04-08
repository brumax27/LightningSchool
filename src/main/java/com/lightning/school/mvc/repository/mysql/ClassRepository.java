package com.lightning.school.mvc.repository.mysql;

import com.lightning.school.mvc.model.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ClassRepository extends JpaRepository<Class, Integer> {
}
