package com.microcommerce.userservice.repository;

import com.microcommerce.userservice.data.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}
