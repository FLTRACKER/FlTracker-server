package ru.ds.fltracker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ds.fltracker.entity.SessionEntity;
import ru.ds.fltracker.entity.UserEntity;
import ru.ds.fltracker.payload.request.GenerateWorkReportRequest;
import ru.ds.fltracker.payload.request.find.SessionFindParam;
import ru.ds.fltracker.repo.UserRepo;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.util.List;

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

    public UserEntity getUserById(String id) {
        return userRepo.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
