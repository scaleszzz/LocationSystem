package com.example.locationsystem.repository;

import com.example.locationsystem.model.UserLocationAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserLocationAccessRepository extends JpaRepository<UserLocationAccess, Long> {
    List<UserLocationAccess> findByLocationId(Long locationId);
    List<UserLocationAccess> findByUserId(Long userId);
}
