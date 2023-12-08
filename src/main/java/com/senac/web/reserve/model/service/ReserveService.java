package com.senac.web.reserve.model.service;

import com.senac.web.reserve.model.entities.Reserve;
import com.senac.web.reserve.model.exception.EntityException;
import com.senac.web.reserve.model.repository.ReserveRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReserveService {

    @Autowired
    private ReserveRepository reserveRepository;

    @Autowired
    private EntityManager entityManager;

    public Reserve save(Reserve reserve) throws EntityException {
        if (LocalDate.now().isAfter(reserve.getDtReserve()) ||
                !this.validateReserveDate(reserve.getDtReserve())) {
            throw new EntityException("Invalid date");
        }
        System.out.println("Vou salvar");
        return reserveRepository.save(reserve);
    }

    public List<Reserve> getAllReserves() {
        return reserveRepository.findAll();
    }

    public void remove(Long id) {
        reserveRepository.deleteById(id);
    }

    private boolean validateReserveDate(LocalDate date){
        Reserve reserve = reserveRepository.findReserveByDtReserve(date);
        return reserve == null;
    }
}
