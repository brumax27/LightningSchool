package com.lightning.school.mvc.repository.mysql;

import com.lightning.school.mvc.model.constante.Constante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConstanteRepository extends JpaRepository<Constante, Integer> {
}
