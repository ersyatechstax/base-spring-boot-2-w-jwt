package com.main.persistence.repository;

import com.main.persistence.domain.Role;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * created by ersya 30/03/2019
 */
@Repository
public interface RoleRepository extends BaseRepository<Role> {
    Optional<Role> findByName(String roleName);
}
