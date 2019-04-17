package com.lightning.school.mvc.repository.neo4j;

import com.lightning.school.mvc.graph.OrOperator;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface OrOperatorRepository extends Neo4jRepository<OrOperator, Long> {
}
