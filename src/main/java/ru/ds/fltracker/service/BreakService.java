package ru.ds.fltracker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ds.fltracker.entity.ActivityEntity;
import ru.ds.fltracker.entity.BreakEntity;
import ru.ds.fltracker.repo.BreakRepo;

import javax.persistence.EntityManager;

@Service
@Transactional
@RequiredArgsConstructor
public class BreakService {
    private final BreakRepo breakRepo;
    private final EntityManager em;

    public BreakEntity save(BreakEntity breakEntity) {
        BreakEntity persisted = breakRepo.saveAndFlush(breakEntity);
        em.refresh(persisted);
        return persisted;
    }
}
