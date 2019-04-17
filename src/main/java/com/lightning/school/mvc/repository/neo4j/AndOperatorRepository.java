package com.lightning.school.mvc.repository.neo4j;

import com.lightning.school.mvc.graph.AndOperator;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AndOperatorRepository extends Neo4jRepository<AndOperator, Long> {
}
