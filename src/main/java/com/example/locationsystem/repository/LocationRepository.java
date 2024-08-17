package com.example.locationsystem.repository;

import com.example.locationsystem.model.Location;
import com.example.locationsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface LocationRepository  extends JpaRepository<Location, Long> {
    List<Location> findByOwner(User owner);
}
