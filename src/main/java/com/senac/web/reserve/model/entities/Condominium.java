package com.senac.web.reserve.model.entities;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Table(name = "condominium")
@Entity(name = "condominium")
public class Condominium implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 75, nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String cpf;
    @Column(nullable = false)
    private LocalDate birth;
    private UserRole role;

    public Condominium(Long id, String name, String password, String cpf, LocalDate birth, UserRole role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.cpf = cpf;
        this.birth = birth;
        this.role = role;
    }

    public Condominium(String name, String password, String cpf, LocalDate birth, UserRole role) {
        this.name = name;
        this.password = password;
        this.cpf = cpf;
        this.birth = birth;
        this.role = role;
    }

    public Condominium(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Condominium() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.getRole() == UserRole.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        }else {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "Id:" + this.getId() +
                "\nName:" + this.getName() +
                "\nCPF:" + this.getCpf() +
                "\nPassword:" + this.getPassword() +
                "\nBirth:" + this.getBirth() +
                "\nRole:" + this.getRole();


    }
}
