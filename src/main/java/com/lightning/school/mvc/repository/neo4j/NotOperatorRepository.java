package com.lightning.school.mvc.repository.neo4j;

import com.lightning.school.mvc.graph.NotOperator;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface NotOperatorRepository extends Neo4jRepository<NotOperator, Long> {
}
