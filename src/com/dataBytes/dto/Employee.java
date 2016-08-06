package com.dataBytes.dto;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.envers.Audited;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dataBytes.controller.EmployeeController;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="EMPLOYEE")
@Audited
@JsonIgnoreProperties(ignoreUnknown=true)
public class Employee implements Serializable{

	private static final long serialVersionUID = 453693552059515150L;
	private static final Logger log = LoggerFactory.getLogger(Employee.class);
	
	@Id
	@Getter @Setter @JsonProperty @Column(name="EMPLOYEE_ID")
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPLOYEE_SEQ")
	//@SequenceGenerator(name = "EMPLOYEE_SEQ", sequenceName = "EMPLOYEE_SEQ", allocationSize = 1, initialValue = 1)	
	private Long id;
	
	@Getter @Setter @JsonProperty @Column (name="NAME", nullable = false, length = 100)
	private String name;
	
	@Getter @Setter @JsonProperty @Column (name="APPAREA")
	private String appArea;	
	
	@Getter @Setter @JsonProperty @Column (name="DSID")
	private String dsId;
	
	@Temporal(TemporalType.DATE)
	@Getter @Setter @Column (name="BADGEENDDATE")
	private Date badgeEndDate;
		
	@Getter @Setter @JsonProperty @Column (name="EMAIL")
	private String email;
	
	@Getter @Setter @JsonProperty @Column (name="CUBICLEID")
	private String cubicleId;	
	
	@Getter @Setter @JsonProperty @Column (name="MANAGERID")
	private String managerId;
	
	@Getter @Setter @JsonProperty @Column (name="REQUESTTYPE")
	private String requestType;
	
	@Getter @Setter @JsonProperty 
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "EMPLOYEE_PRIVILEGE", joinColumns = { @JoinColumn(name = "EMPLOYEE_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "EMPLOYEE_PRIVILEGE_ID") })
	private Set<Privilege> privileges = new HashSet<Privilege>();
	
	@Getter @Setter @Column (name="DELETEFLAG")
	private Boolean deleteFlag;
	
	@Getter @Setter @Column (name="LASTMODIFIEDBY")
	private String lastModifiedBy;
	
	@Temporal(TemporalType.DATE)
	@Getter @Setter @Column (name="LASTMODIFIED")
	private Date lastModified;
	
	@Getter @Setter @JsonProperty @Transient
	private Set<String> files;
	
	@Override
	public boolean equals(Object employee){
		try { 
			if (null == employee) return false;
			if (this == employee) return true;
			if (this.id == ((Employee)employee).id){
				return true;
			} else return false;
			
		} catch(Throwable t) {
			t.printStackTrace();
			return false;
		}
	}
	
	@Override
	public String toString(){
		try { 
			StringBuffer sb = new StringBuffer();
			sb.append("  id:"+id+"; Employee Name:"+name+";  App Area:"+appArea+";  DSID:"+dsId);
			sb.append("; PO / Badge End Date:"+badgeEndDate+";  Email:"+email+";  Cubicle Id:"+cubicleId);
			sb.append(";  Infy Manager Id:"+managerId+";  Type Of Request:"+requestType);
			log.debug(sb.toString());
			return sb.toString();
		} catch(Throwable t) {
			t.printStackTrace();
			return "";
		}
	}
			 
}
