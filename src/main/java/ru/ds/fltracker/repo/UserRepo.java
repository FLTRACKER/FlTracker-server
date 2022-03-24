package ru.ds.fltracker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ds.fltracker.entity.UserEntity;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, String> {
}
