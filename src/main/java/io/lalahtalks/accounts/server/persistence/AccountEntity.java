package io.lalahtalks.accounts.server.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "account")
public class AccountEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "created_at")
    private Instant createdAt;

    public AccountEntity() {

    }

    public AccountEntity(String id, String email, String username, Instant createdAt) {
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

    public static final class Builder {

        private String id;
        private String email;
        private String username;
        private Instant createdAt;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public AccountEntity build() {
            return new AccountEntity(id, email, username, createdAt);
        }

    }

    public static Builder builder() {
        return new Builder();
    }

}
