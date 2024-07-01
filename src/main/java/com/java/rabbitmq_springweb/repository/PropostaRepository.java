package com.java.rabbitmq_springweb.repository;

import com.java.rabbitmq_springweb.entity.Proposta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropostaRepository extends CrudRepository<Proposta, Long> {


}
