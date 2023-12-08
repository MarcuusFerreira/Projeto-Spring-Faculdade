package com.senac.web.reserve.model.repository;

import com.senac.web.reserve.model.entities.Condominium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface CondominiumRepository extends JpaRepository<Condominium, Long> {
    UserDetails findByName(String name);

    Condominium findByNameAndPassword(String name, String password);
}
