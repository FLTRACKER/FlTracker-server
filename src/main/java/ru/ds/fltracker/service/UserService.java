package ru.ds.fltracker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ds.fltracker.entity.SessionEntity;
import ru.ds.fltracker.entity.UserEntity;
import ru.ds.fltracker.repo.UserRepo;

import javax.persistence.EntityManager;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final EntityManager em;

    public UserEntity save(UserEntity userEntity) {
        UserEntity persisted = userRepo.saveAndFlush(userEntity);
        em.refresh(persisted);
        return persisted;
    }
}
