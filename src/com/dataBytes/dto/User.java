package com.dataBytes.dto;

import java.util.HashSet;
import java.util.Set;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

import com.dataBytes.model.*;

@Entity
@Table(name = "APP_USER")
@Audited
public class User {

	@Id
	@Getter @Setter @JsonProperty @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Getter @Setter @JsonProperty @Column(name = "SSO_ID", unique = true, nullable = false)
	private String ssoId;

	@Getter @Setter @JsonProperty @Column(name = "PASSWORD", nullable = false)
	private String password;

	@Getter @Setter @JsonProperty @Column(name = "FIRST_NAME", nullable = false)
	private String firstName;

	@Getter @Setter @JsonProperty @Column(name = "LAST_NAME", nullable = false)
	private String lastName;

	@Getter @Setter @JsonProperty @Column(name = "EMAIL", nullable = false)
	private String email;

	@Getter @Setter @JsonProperty @Column(name = "STATE", nullable = false)
	private String state = State.ACTIVE.getState();

	@Getter @Setter @JsonProperty 
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "APP_USER_USER_PROFILE", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "USER_PROFILE_ID") })
	private Set<UserProfile> userProfiles = new HashSet<UserProfile>();
	
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((ssoId == null) ? 0 : ssoId.hashCode());
        return result;
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof User))
            return false;
        User other = (User) obj;
        if (id != other.id)
            return false;
        if (ssoId == null) {
            if (other.ssoId != null)
                return false;
        } else if (!ssoId.equals(other.ssoId))
            return false;
        return true;
    }
 
    @Override
    public String toString() {
        return "User [id=" + id + ", ssoId=" + ssoId + ", password=" + password
                + ", firstName=" + firstName + ", lastName=" + lastName
                + ", email=" + email + ", state=" + state + ", userProfiles=" + userProfiles +"]";
    }
}
