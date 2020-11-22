package com.app.AppJava.repository;

import com.app.AppJava.models.ERole;
import com.app.AppJava.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
