package com.senac.web.reserve.controller;

import com.senac.web.reserve.model.dto.CondominiumDTO;
import com.senac.web.reserve.model.dto.LoginDTO;
import com.senac.web.reserve.model.entities.Condominium;
import com.senac.web.reserve.model.exception.EntityException;
import com.senac.web.reserve.model.service.CondominiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/condominium")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.OPTIONS,RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.HEAD, RequestMethod.TRACE, RequestMethod.PATCH})
public class CondominiumController {

    @Autowired
    private CondominiumService condominiumService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody CondominiumDTO condominiumDTO) {
        try{
            if (condominiumDTO.name() == null || condominiumDTO.password() == null ||
                    condominiumDTO.cpf() == null || condominiumDTO.birth() == null || condominiumDTO.role() == null) {
                return ResponseEntity.badRequest().body("Todos os campos são obrigatórios");
            }
            Condominium condominium = new Condominium(condominiumDTO.name(), condominiumDTO.password(), condominiumDTO.cpf(), condominiumDTO.birth(), condominiumDTO.role());
            condominium = condominiumService.save(condominium);
            return ResponseEntity.ok().body(condominium);
        } catch(EntityException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        try {
            Condominium condominium = new Condominium(loginDTO.name(), loginDTO.password());
            Map<String, Object> responseMap = condominiumService.login(condominium);
            return ResponseEntity.ok().body(responseMap);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid creadentials" + e.getMessage());
        }
    }

    @GetMapping("/listall")
    public ResponseEntity<?> listAll() {
        List<Condominium> condons = condominiumService.listAll();
        return ResponseEntity.ok().body(condons);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCondom(@RequestBody Condominium condominium) {
        try {
            System.out.println(condominium);
            if (condominium.getId() == null || condominium.getName() == null || condominium.getPassword() == null ||
                condominium.getCpf() == null || condominium.getBirth() == null) {
                return ResponseEntity.badRequest().body("Todos os campos são obrigatórios");
            }
            condominium = condominiumService.save(condominium);
            return ResponseEntity.ok().body(condominium);
        } catch (EntityException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        condominiumService.delete(id);
        return ResponseEntity.ok().body("Condomino removido com sucesso!");
    }
}
