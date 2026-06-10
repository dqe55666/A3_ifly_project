package cn.org.rsrs.project.project_a3_ifly.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String username;
    private String password;
    private boolean firstLogin = true;
    private boolean hasFilledInfo = false;
    
    // Basic Info
    private String fullName;
    private String gender;
    private String className;
    private Integer age;
    private String email;
    private String phoneNumber;
}
