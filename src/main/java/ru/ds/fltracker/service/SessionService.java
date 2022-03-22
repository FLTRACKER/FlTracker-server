package ru.ds.fltracker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.ds.fltracker.dto.UserDto;
import ru.ds.fltracker.entity.SessionEntity;
import ru.ds.fltracker.payload.request.find.SessionFindParam;
import ru.ds.fltracker.repo.SessionRepo;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static ru.ds.fltracker.specifications.Specifications.*;

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

    public boolean finishSession(SessionEntity sessionEntity) {
        try {
            sessionEntity.setFinishTime(LocalDateTime.now());
            save(sessionEntity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public SessionEntity findSessionById(Long id) {
        return sessionRepo.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Page<SessionEntity> find(SessionFindParam findParam) {
        List<Specification<SessionEntity>> specifications = Arrays.asList(
                equalOrReturnNull("user.id", findParam.getUserId(), SessionEntity.class),
                lessThanEqualToOrNull("startTime", findParam.getToDate(), SessionEntity.class),
                greaterThanEqualToOrNull("startTime", findParam.getFromDate(), SessionEntity.class)
                /*lessThanEqualToOrNull("finishTime", findParam.getToDate(), SessionEntity.class),
                greaterThanEqualToOrNull("finishTime", findParam.getFromDate(), SessionEntity.class)*/
        );

        String sortProperty = findParam.getSortProperty();
        Sort.Direction sortDirection = findParam.getSortDirection();
        int pageNumber = findParam.getPageNumber();
        int pageSize = findParam.getPageSize();
        return sessionRepo.findAll(
                And.<SessionEntity>builder()
                        .specifications(specifications)
                        .build(),
                sortDirection == null || StringUtils.isEmpty(sortProperty)
                        ? PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, "id")
                        : PageRequest.of(pageNumber, pageSize, sortDirection, sortProperty)
        );
    }

}
