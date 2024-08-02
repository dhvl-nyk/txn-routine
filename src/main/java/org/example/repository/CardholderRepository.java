package org.example.repository;

import org.example.entity.Cardholder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardholderRepository extends JpaRepository<Cardholder, Long> {
}
