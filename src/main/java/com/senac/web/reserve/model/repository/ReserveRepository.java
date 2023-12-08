package com.senac.web.reserve.model.repository;

import com.senac.web.reserve.model.entities.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReserveRepository extends JpaRepository<Reserve, Long> {

    List<Reserve> findAllByCondominiumId(Long id);

    @Query("""
    SELECT r FROM reserve r where r.dtReserve = :date
    """)
    Reserve findReserveByDtReserve(@Param("date") LocalDate date);
}
