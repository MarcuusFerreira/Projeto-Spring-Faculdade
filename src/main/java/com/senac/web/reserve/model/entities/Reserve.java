package com.senac.web.reserve.model.entities;

import jakarta.persistence.*;
import jakarta.websocket.OnError;

import java.time.LocalDate;

@Table(name = "reserve")
@Entity(name = "reserve")
public class Reserve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "dt_reserve")
    private LocalDate dtReserve;
    @ManyToOne
    @JoinColumn(name = "id_condominium")
    private Condominium condominium;
    @ManyToOne
    @JoinColumn(name = "id_common_area")
    private CommonArea commonArea;

    public Reserve(LocalDate dtReserve, Condominium condominium, CommonArea commonArea) {
        this.dtReserve = dtReserve;
        this.condominium = condominium;
        this.commonArea = commonArea;
    }

    public Reserve() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDtReserve() {
        return dtReserve;
    }

    public void setDt_reserve(LocalDate dtReserve) {
        this.dtReserve = dtReserve;
    }

    public Condominium getCondominium() {
        return condominium;
    }

    public void setCondominium(Condominium condominium) {
        this.condominium = condominium;
    }

    public CommonArea getCommonArea() {
        return commonArea;
    }

    public void setCommonArea(CommonArea commonArea) {
        this.commonArea = commonArea;
    }

    @Override
    public String toString() {
        return "Id: " + this.getId() +
                "\nDate reserve:" + this.getDtReserve() +
                "\nCondominium: " + this.getCondominium() +
                "\nCommon Area: " + this.getCommonArea();
    }
}
