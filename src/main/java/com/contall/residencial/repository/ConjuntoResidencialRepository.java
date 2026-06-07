package com.contall.residencial.repository;

import com.contall.residencial.model.ConjuntoResidencial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConjuntoResidencialRepository extends JpaRepository<ConjuntoResidencial, Long> {
}
