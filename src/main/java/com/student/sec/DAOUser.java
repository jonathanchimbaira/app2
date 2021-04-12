package com.student.sec;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user")
public class DAOUser {

    @Id
    private String userid;
    @Column
    private Long id;
    @Column
    private String role;
    @Column
    @JsonIgnore
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    //@OneToMany( fetch = FetchType.LAZY, mappedBy = "id")
   // @JoinColumn(name = "ro", nullable = false)
    private Set<roles> role1;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<roles> getRole1() {
        return role1;
    }

    public void setRole1(Set<roles> role) {
        this.role1 = role;
    }
}