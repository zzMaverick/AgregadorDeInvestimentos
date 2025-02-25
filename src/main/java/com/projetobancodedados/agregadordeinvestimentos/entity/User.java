package com.projetobancodedados.agregadordeinvestimentos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userid;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @CreationTimestamp
    private Instant creationTimesTamp;

    @UpdateTimestamp
    private Instant UpdateTimestamp;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Account> accountList;

    public UUID getUserid() {
        return userid;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Instant getCreationTimesTamp() {
        return creationTimesTamp;
    }

    public Instant getUpdateTimestamp() {
        return UpdateTimestamp;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setUserid(UUID userid) {
        this.userid = userid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreationTimesTamp(Instant creationTimesTamp) {
        this.creationTimesTamp = creationTimesTamp;
    }

    public void setUpdateTimestamp(Instant updateTimestamp) {
        UpdateTimestamp = updateTimestamp;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    public User(UUID userid, String username, String email, String password, Instant creationTimesTamp, Instant updateTimestamp) {
        this.userid = userid;
        this.username = username;
        this.email = email;
        this.password = password;
        this.creationTimesTamp = creationTimesTamp;
        UpdateTimestamp = updateTimestamp;
    }

    public User() {
    }
}
