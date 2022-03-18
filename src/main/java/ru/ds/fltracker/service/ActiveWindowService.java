package ru.ds.fltracker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ds.fltracker.entity.ActiveWindowEntity;
import ru.ds.fltracker.entity.ActivityEntity;
import ru.ds.fltracker.repo.ActiveWindowRepo;

import javax.persistence.EntityManager;

@Service
@Transactional
@RequiredArgsConstructor
public class ActiveWindowService {
    private final ActiveWindowRepo activeWindowRepo;
    private final EntityManager em;

    public ActiveWindowEntity save(ActiveWindowEntity activeWindowEntity) {
        ActiveWindowEntity persisted = activeWindowRepo.saveAndFlush(activeWindowEntity);
        em.refresh(persisted);
        return persisted;
    }
}
