package ru.ds.fltracker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ds.fltracker.repo.RoleRepo;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepo roleRepo;
}
