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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="EMPLOYEE_PRIVILEGE")
@Audited
public class EmployeePrivilege {
	
	private static final long serialVersionUID = 453693552059515163L;
	private static final Logger log = LoggerFactory.getLogger(EmployeePrivilege.class);
	
	@Id
	@Getter @Setter  @JsonIgnore @Column(name="ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPLOYEE_PRIVILEGE_SEQ")
	@SequenceGenerator(name = "EMPLOYEE_PRIVILEGE_SEQ", sequenceName = "EMPLOYEE_PRIVILEGE_SEQ", allocationSize = 1, initialValue = 1)	
	private Long id;
	
	@Getter @Setter  @JsonProperty(access = Access.WRITE_ONLY) @Column(name="EMPLOYEE_ID")
	private Long employeeId;

	@Getter @Setter  @JsonProperty @Column (name="EMPLOYEE_PRIVILEGE_ID")
	private Long privilegeId;
	
	@Getter @Setter @JsonIgnore @Column (name="DELETEFLAG")
	private Boolean deleteFlag;
	
	@Getter @Setter @JsonIgnore @Column (name="LASTMODIFIEDBY")
	private String lastModifiedBy;
	
	@Temporal(TemporalType.DATE)
	@Getter @Setter @JsonIgnore @Column (name="LASTMODIFIED")
	private Date lastModified;

	@Override
	public String toString(){
		try { 
			StringBuffer sb = new StringBuffer();
			//sb.append("id:"+id+";employeeId:"+employeeId+";privilegeId:"+privilegeId);
			sb.append(privilegeId);
			log.debug(sb.toString());
			return sb.toString();
		} catch(Throwable t) {
			t.printStackTrace();
			return "";
		}
	}
}
