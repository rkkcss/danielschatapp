package com.chat.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Username can not be null.")
    @Length(min = 6, message = "Username must at least 6 characters.")
    private String username;

    @NotEmpty(message = "E-mail can not be null.")
    @Email(message = "Need valid e-mail address.")
    private String email;
    
    @NotEmpty(message = "Password can not be null.")
    @Length(min = 6, message = "Password must at least 6 characters.")
    private String password;
    
    
    private String passwordAgain;
    
    @Lob
    @Column(nullable = true, columnDefinition = "MEDIUMBLOB")
    private String image;
    
    @OneToMany(mappedBy = "user")
    private List<ChatMessage> content;

    public User() {
    }

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPasswordAgain() {
		return passwordAgain;
	}



	public void setPasswordAgain(String passwordAgain) {
		this.passwordAgain = passwordAgain;
	}



	public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ChatMessage> getContent() {
        return content;
    }

    public void setContent(List<ChatMessage> content) {
        this.content = content;
    }

    public User(String username) {
        this.username = username;
    }

    

    @Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", passwordAgain=" + passwordAgain + ", photos=" + image + ", content=" + content + "]";
	}



	public User(long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;

    }

}
