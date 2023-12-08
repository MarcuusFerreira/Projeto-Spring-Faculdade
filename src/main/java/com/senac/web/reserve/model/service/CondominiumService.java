package com.senac.web.reserve.model.service;

import com.senac.web.reserve.model.entities.Condominium;
import com.senac.web.reserve.model.exception.EntityException;
import com.senac.web.reserve.model.repository.CondominiumRepository;
import com.senac.web.reserve.model.utils.ValidateCpf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CondominiumService implements UserDetailsService {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private CondominiumRepository condominiumRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager manager;

    public Condominium save(Condominium condominium) throws EntityException {
        if (ValidateCpf.validarCpf(condominium.getCpf())) {
            throw new EntityException("Invalid CPF");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(condominium.getPassword());
        condominium.setPassword(encryptedPassword);
        return condominiumRepository.save(condominium);
    }

    public Map<String, Object> login(Condominium condominiumLogin) {
        UsernamePasswordAuthenticationToken condom = new UsernamePasswordAuthenticationToken(condominiumLogin.getName(), condominiumLogin.getPassword());
        var authenticate = manager.authenticate(condom);
        String token = tokenService.generateToken((Condominium) authenticate.getPrincipal());
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("condominium", authenticate);
        return response;
    }

    public List<Condominium> listAll() {
        return condominiumRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return condominiumRepository.findByName(username);
    }

    public Condominium findCondominium(Long id) {
        return condominiumRepository.findById(id).get();
    }

    public void delete(Long id) {
        condominiumRepository.deleteById(id);
    }
}
