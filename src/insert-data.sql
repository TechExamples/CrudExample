/* Populate USER_PROFILE Table */
INSERT INTO USER_PROFILE(type)
VALUES ('USER');
 
INSERT INTO USER_PROFILE(type)
VALUES ('ADMIN');
 
/* Populate APP_USER Table abc123 */
INSERT INTO APP_USER(sso_id, password, first_name, last_name, email, state)
VALUES ('anusha','$2a$10$pnyOvW2S8WzgXt4lKbV/2uY1yOWAn8hWCM8UEAgDz.hZPoNS8L13C', 'Anusha','Nelavalli','anusha.nelavalli@infosys.com', 'Active');
 
INSERT INTO APP_USER(sso_id, password, first_name, last_name, email, state)
VALUES ('srikanth','$2a$10$pnyOvW2S8WzgXt4lKbV/2uY1yOWAn8hWCM8UEAgDz.hZPoNS8L13C', 'Srikanth','Infosys','srikanth@infosys.com', 'Active');
 
/* Populate JOIN Table */
INSERT INTO APP_USER_USER_PROFILE (user_id, user_profile_id)
  SELECT user.id, profile.id FROM app_user user, user_profile profile  
  where user.sso_id='anusha' and profile.type='ADMIN';
 
INSERT INTO APP_USER_USER_PROFILE (user_id, user_profile_id)
  SELECT user.id, profile.id FROM app_user user, user_profile profile
  where user.sso_id='srikanth' and profile.type='ADMIN';
 
/* Populate EMPLOYEE Table */
INSERT INTO EMPLOYEE(EMPLOYEE_ID, NAME,MANAGERID,DSID,APPAREA, BADGEENDDATE, CUBICLEID, EMAIL, REQUESTTYPE,DELETEFLAG,LASTMODIFIED,LASTMODIFIEDBY)
VALUES (1001,'abc', 'sri123','dsid1','apparea1', '2016-08-05','11','abc@infosys.com','requesttype1',false,'2016-08-02','system');
    
INSERT INTO EMPLOYEE(EMPLOYEE_ID, NAME,MANAGERID,DSID,APPAREA, BADGEENDDATE, CUBICLEID, EMAIL, REQUESTTYPE,DELETEFLAG,LASTMODIFIED,LASTMODIFIEDBY)
VALUES (1002,'123', 'anu123','dsi21','apparea2', '2016-08-04','12','123@infosys.com','requesttype2',false,'2016-08-01','system');


commit;


