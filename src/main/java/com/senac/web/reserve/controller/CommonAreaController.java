package com.senac.web.reserve.controller;

import com.senac.web.reserve.model.dto.CommonAreaDTO;
import com.senac.web.reserve.model.entities.CommonArea;
import com.senac.web.reserve.model.service.CommonAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commonarea")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.OPTIONS,RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.HEAD, RequestMethod.TRACE, RequestMethod.PATCH})
public class CommonAreaController {

    @Autowired
    private CommonAreaService commonAreaService;

    @PostMapping("/insert")
    public ResponseEntity<?> create(@RequestBody CommonAreaDTO commonAreaDTO) {
        CommonArea commonArea = new CommonArea(commonAreaDTO.name(), commonAreaDTO.description());
        commonArea = commonAreaService.save(commonArea);
        return ResponseEntity.ok().body(commonArea);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody CommonArea commonArea) {
        commonArea = commonAreaService.save(commonArea);
        return ResponseEntity.ok().body(commonArea);
    }

    @GetMapping("/listall")
    public ResponseEntity<?> listall() {
        List<CommonArea> commonAreas = commonAreaService.listAll();
        return ResponseEntity.ok().body(commonAreas);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        commonAreaService.delete(id);
        return ResponseEntity.ok().body("Item removido com sucesso!");
    }
}
