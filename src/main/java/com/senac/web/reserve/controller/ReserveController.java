package com.senac.web.reserve.controller;

import com.senac.web.reserve.model.dto.ReserveDTO;
import com.senac.web.reserve.model.entities.CommonArea;
import com.senac.web.reserve.model.entities.Condominium;
import com.senac.web.reserve.model.entities.Reserve;
import com.senac.web.reserve.model.exception.EntityException;
import com.senac.web.reserve.model.service.CommonAreaService;
import com.senac.web.reserve.model.service.CondominiumService;
import com.senac.web.reserve.model.service.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reserve")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.OPTIONS,RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.HEAD, RequestMethod.TRACE, RequestMethod.PATCH})
public class ReserveController {

    @Autowired
    private ReserveService reserveService;

    @Autowired
    private CondominiumService condominiumService;

    @Autowired
    private CommonAreaService commonAreaService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ReserveDTO reserveDTO) {
        Condominium condominium = condominiumService.findCondominium(reserveDTO.idCondominium());
        CommonArea commonArea = commonAreaService.findCommonArea(reserveDTO.idCommonArea());
        Reserve reserve = new Reserve(reserveDTO.dtReserve(), condominium, commonArea);
        try {
            reserve = reserveService.save(reserve);
            return ResponseEntity.ok().body(reserve);
        } catch (EntityException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Reserve reserve) {
        try {
            reserve = reserveService.save(reserve);
            return ResponseEntity.ok().body(reserve);
        } catch (EntityException e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @GetMapping("/listall")
    public ResponseEntity<?> getAllReserves() {
        List<Reserve> reserveList = reserveService.getAllReserves();
        return ResponseEntity.ok().body(reserveList);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<?> remove(Long id) {
        reserveService.remove(id);
        return ResponseEntity.ok().body("Removido");
    }
}
