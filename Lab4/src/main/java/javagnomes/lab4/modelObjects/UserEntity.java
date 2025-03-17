package javagnomes.lab4.modelObjects;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "users")
public class UserEntity implements Serializable {
    @Id
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "hashedPassword")
    @JsonIgnore
    private String hashedPassword;

    @Column(name = "salt")
    @JsonIgnore
    private String salt;

    public UserEntity(String username, String hashedPassword) {
        this.username = username;
        this.hashedPassword = hashedPassword;
    }

    public UserEntity(String username, String hashedPassword, String salt) {
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.salt = salt;
    }
}
