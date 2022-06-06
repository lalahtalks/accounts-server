package io.lalahtalks.accounts.server.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "account")
public class AccountJpaDto {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "created_at")
    private Instant createdAt;

    public AccountJpaDto() {

    }

    public AccountJpaDto(String id, String email, String username, Instant createdAt) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.createdAt = createdAt;
    }

    public String id() {
        return id;
    }

    public String email() {
        return email;
    }

    public String username() {
        return username;
    }

    public Instant createdAt() {
        return createdAt;
    }

}
