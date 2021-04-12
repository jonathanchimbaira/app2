package com.student.sec;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class roles {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column
    private String role;

    @Column
    private String userid;

    @Column
    private String u_id;

   /* @Fetch(FetchMode.JOIN)
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="userid", insertable=false, updatable=false)

    */
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "userid", nullable = false, insertable=false, updatable=false)

    private DAOUser user;


    public roles() {
    }

    public roles(String role) {
        this.role = role;
    }


    public String getuserid() {
        return userid;
    }

    public String getId() {
        return id;
    }
    public String getu_Id() {
        return u_id;
    }
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public void setid(String u_id) {
        this.u_id = u_id;
    }
    public void setu_id(String id) {
        this.id = id;
    }

    public void setuserid(String userid) {
        this.userid = userid;
    }

    public DAOUser getUser() {
        return user;
    }

    public void setUser(DAOUser user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", user=" + userid +
                '}';
    }
}