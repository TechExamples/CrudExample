package com.dataBytes.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="PRIVILEGE")
@Audited
public class Privilege {
	
	private static final long serialVersionUID = 453693552059515153L;

	@Id
	@Getter @Setter  @JsonProperty @Column(name="PRIVILEGE_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRIVILEGE_SEQ")
	@SequenceGenerator(name = "PRIVILEGE_SEQ", sequenceName = "PRIVILEGE_SEQ", allocationSize = 1, initialValue = 1)	
	private Long id;

	@Getter @Setter  @JsonProperty @Column (name="NAME")
	private String name;
	
	@Getter @Setter @Column (name="DELETEFLAG")
	private Boolean deleteFlag;
	
	@Getter @Setter @Column (name="LASTMODIFIEDBY")
	private String lastModifiedBy;
	
	@Temporal(TemporalType.DATE)
	@Getter @Setter @Column (name="LASTMODIFIED")
	private Date lastModified;

}
