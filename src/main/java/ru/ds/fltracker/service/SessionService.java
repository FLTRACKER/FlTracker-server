package ru.ds.fltracker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ds.fltracker.entity.SessionEntity;
import ru.ds.fltracker.repo.SessionRepo;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SessionService {
    private final SessionRepo sessionRepo;
    private final EntityManager em;

    public SessionEntity save(SessionEntity sessionEntity) {
        SessionEntity persisted = sessionRepo.saveAndFlush(sessionEntity);
        em.refresh(persisted);
        return persisted;
    }

    public List<SessionEntity> findAll() {
        return sessionRepo.findAll();
    }

    public SessionEntity findSessionById(Long id) {
        return sessionRepo.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
