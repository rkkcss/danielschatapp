package com.chat.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^[a-zA-Z0-9.\\-\\/+=@_ ]*$", message = "Illegal characters in username.")
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
    
    @Pattern(regexp = "^[a-zA-Z0-9.\\-\\/+=@_ ]*$", message = "Illegal characters.")
    @Column(nullable = true, name = "sir_name", length = 50)
    private String sirName;
    
    @Pattern(regexp = "^[a-zA-Z0-9.\\-\\/+=@_ ]*$", message = "Illegal characters.")
    @Column(nullable = true, name = "first_name", length = 50)
    private String firstName;
    
    @Column(nullable = true, name = "about_me", length = 100)
    private String aboutMe;
    
    @Column(nullable = true)
    private Integer age;
    
    @OneToMany(mappedBy = "user")
    private List<ChatMessage> content;

    public User() {
    }

    
    
	public Integer getAge() {
		return age;
	}



	public void setAge(Integer age) {
		this.age = age;
	}



	public String getAboutMe() {
		return aboutMe;
	}


	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe.trim().replaceAll("\\s+", " ");
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email.trim().replaceAll("\\s+", "");
	}

	public String getPasswordAgain() {
		return passwordAgain;
	}

	public void setPasswordAgain(String passwordAgain) {
		this.passwordAgain = passwordAgain.trim().replaceAll("\\s+", "");
	}

	public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username.trim().replaceAll("\\s+", "");
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password.trim().replaceAll("\\s+", "");
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
				+ ", passwordAgain=" + passwordAgain + ", sirName=" + sirName + ", firstName=" + firstName
				+ ", aboutMe=" + aboutMe + ", age=" + age + ", content=" + content + "]";
	}



	public String getSirName() {
		return sirName;
	}


	public void setSirName(String sirName) {
		this.sirName = sirName.trim();
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName.trim();
	}


	public User(long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;

    }

}
