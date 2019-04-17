package com.lightning.school.mvc.repository.mysql;

import com.lightning.school.mvc.model.constante.Constante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(collectionResourceRel = "constante", path = "constante")
public interface ConstanteRepository extends JpaRepository<Constante, Integer> {
}
