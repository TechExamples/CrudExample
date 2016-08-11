	
	drop table APP_USER_USER_PROFILE_AUD; 
    drop table APP_USER_USER_PROFILE;     
    drop table USER_PROFILE_AUD;  
    drop table USER_PROFILE;     
    drop table APP_USER_AUD; 	
    drop table APP_USER;
    
    drop table EMPLOYEE_PRIVILEGE_AUD;
    drop table EMPLOYEE_PRIVILEGE;
    drop table EMPLOYEE_AUD; 
    drop table EMPLOYEE;
    drop table PRIVILEGE_AUD;
    drop table PRIVILEGE;    
    drop table REVINFO; 
    drop table persistent_logins;
    
    create table persistent_logins (
	    username varchar(64) not null, 
	    series varchar(64) primary key, 
	    token varchar(64) not null, 
	    last_used timestamp not null
    );

    create table REVINFO (
        REV integer generated by default as identity (start with 1),
        REVTSTMP bigint,
        primary key (REV)
    );
    
    create table PRIVILEGE (
        PRIVILEGE_ID bigint not null,
        NAME varchar(255),
        DELETEFLAG boolean,
        LASTMODIFIED date,
        LASTMODIFIEDBY varchar(255),
        primary key (PRIVILEGE_ID)
    );

    
	    create table PRIVILEGE_AUD (
	        PRIVILEGE_ID bigint not null,
	        NAME varchar(255),
			DELETEFLAG boolean,
	        LASTMODIFIED date,
	        LASTMODIFIEDBY varchar(255),
	        REV integer not null,
	        REVTYPE tinyint,
	        primary key (PRIVILEGE_ID, REV)
	    );
	    
    create table EMPLOYEE (
        EMPLOYEE_ID bigint not null,
        NAME varchar(100) not null,
        MANAGERID varchar(255),
        DSID varchar(255),
        APPAREA varchar(255),
        BADGEENDDATE date,
        CUBICLEID varchar(255),
        EMAIL varchar(255),
        REQUESTTYPE varchar(255),
        STATUS INTEGER  not null),
        DELETEFLAG boolean,
        LASTMODIFIED date,
        LASTMODIFIEDBY varchar(255),
        primary key (EMPLOYEE_ID)
    );
	
    
    create table EMPLOYEE_AUD (
        EMPLOYEE_ID bigint not null,
        NAME varchar(100) not null,
        MANAGERID varchar(255),
        DSID varchar(255),
        APPAREA varchar(255),
        BADGEENDDATE date,
        CUBICLEID varchar(255),
        EMAIL varchar(255),
        REQUESTTYPE varchar(255),
        STATUS INTEGER  not null),
        DELETEFLAG boolean,
        LASTMODIFIED date,
        LASTMODIFIEDBY varchar(255),
		REV integer not null,
        REVTYPE tinyint,
        primary key (EMPLOYEE_ID, REV)
    );
	
    create table EMPLOYEE_PRIVILEGE (
        EMPLOYEE_ID bigint not null,
        EMPLOYEE_PRIVILEGE_ID bigint not null,
        primary key (EMPLOYEE_ID, EMPLOYEE_PRIVILEGE_ID)
    );

    
    create table EMPLOYEE_PRIVILEGE_AUD (
        EMPLOYEE_ID bigint not null,
        EMPLOYEE_PRIVILEGE_ID bigint not null,
        REVTYPE tinyint,
		REV integer not null,      
        primary key (REV, EMPLOYEE_ID, EMPLOYEE_PRIVILEGE_ID)
    );

    create table APP_USER (
        id integer generated by default as identity (start with 1),
        FIRST_NAME varchar(255) not null,
        LAST_NAME varchar(255) not null,
        SSO_ID varchar(255) not null,        
		PASSWORD varchar(255) not null,
        STATE varchar(255) not null,
		EMAIL varchar(255) not null,        
        primary key (id)
    );
    
      
    create table APP_USER_AUD (
        id integer not null,
        FIRST_NAME varchar(255) not null,
        LAST_NAME varchar(255) not null,
        SSO_ID varchar(255) not null,        
		PASSWORD varchar(255) not null,
        STATE varchar(255) not null,
		EMAIL varchar(255) not null,   
    	REV integer not null,
        REVTYPE tinyint,
        primary key (id, REV)
    );
    
           
    create table USER_PROFILE (
        id integer generated by default as identity (start with 1),
        TYPE varchar(15) not null,
        primary key (id)
    );
    
     
    create table USER_PROFILE_AUD (
        id integer not null,
        TYPE varchar(15),
        REV integer not null,
        REVTYPE tinyint,        
        primary key (id, REV)
    );
    
   
	create table APP_USER_USER_PROFILE (
        USER_ID integer not null,
        USER_PROFILE_ID integer not null,
        primary key (USER_ID, USER_PROFILE_ID)
    );	
    
   
    create table APP_USER_USER_PROFILE_AUD (
        USER_ID integer not null,
        USER_PROFILE_ID integer not null,
        REV integer not null,
        REVTYPE tinyint,
        primary key (REV, USER_ID, USER_PROFILE_ID)
    );    
        
    
    alter table APP_USER 
        add constraint UK_hqk6uc88j3imq8u9jhro36vt3  unique (SSO_ID);

    alter table APP_USER_AUD 
        add constraint FK_nnsq3oacl7l4b0789i3kntavq 
        foreign key (REV) 
        references REVINFO;
        
    alter table USER_PROFILE 
        add constraint UK_cva7m2blp7ekclxworqxau1l7  unique (TYPE);

    alter table USER_PROFILE_AUD 
        add constraint FK_dcb320l5x3tgdfc1w2j6plm35 
        foreign key (REV) 
        references REVINFO;
        
    alter table APP_USER_USER_PROFILE 
        add constraint FK_gs2lq4vmukguubh36utd4r2cl 
        foreign key (USER_PROFILE_ID) 
        references USER_PROFILE;

    alter table APP_USER_USER_PROFILE 
        add constraint FK_brmce0t584euix4wb4rursf1q 
        foreign key (USER_ID) 
        references APP_USER;

     alter table APP_USER_USER_PROFILE_AUD 
        add constraint FK_aeyg3nwiq9alm518h9wryr6c3 
        foreign key (REV) 
        references REVINFO;
          
    alter table EMPLOYEE_AUD 
        add constraint FK_t6ybx1sbutm2dmawkw79u05mu 
        foreign key (REV) 
        references REVINFO;
		
    alter table EMPLOYEE_PRIVILEGE 
        add constraint FK_an1yeebh2v877qby743vgovgb 
        foreign key (EMPLOYEE_PRIVILEGE_ID) 
        references PRIVILEGE;

    alter table EMPLOYEE_PRIVILEGE 
        add constraint FK_3i4hkpki66e8ftm3ouqg9g141 
        foreign key (EMPLOYEE_ID) 
        references EMPLOYEE;

    alter table EMPLOYEE_PRIVILEGE_AUD 
        add constraint FK_an2kb7nxr45x1mlfji6qena1w 
        foreign key (REV) 
        references REVINFO;

    alter table PRIVILEGE_AUD 
        add constraint FK_20w4q54dnhl9ybkvwog138moh 
        foreign key (REV) 
        references REVINFO;
	
    drop sequence PRIVILEGE_SEQ;
	create sequence PRIVILEGE_SEQ start with 1;
	