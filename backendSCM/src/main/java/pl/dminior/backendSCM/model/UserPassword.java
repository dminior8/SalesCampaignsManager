package pl.dminior.backendSCM.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "User_Password")
@Data
public class UserPassword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "salt")
    private String salt;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

}

