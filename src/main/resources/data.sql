INSERT INTO USER (email, password, login, active) VALUES ( 'thomas@gmail.com',
                                                           '$2a$10$kc7UIBquKJXstuXEthHRYefeOPzyHU2w0ZAK0EnFUsvRhh1lgYSKq', 'thomas',1);
INSERT INTO USER (email, password, login, active) VALUES ( 'anja@gmail.com',
                                                           '$2a$10$kc7UIBquKJXstuXEthHRYefeOPzyHU2w0ZAK0EnFUsvRhh1lgYSKq', 'anja', 1);
INSERT INTO AUTHORITY (description) VALUES ( 'ADMIN');
INSERT INTO AUTHORITY (description) VALUES ( 'STUDENT');
INSERT INTO USERAUTHORITY (IDUSER, IDAUTHORITY) VALUES ( 1, 1);
INSERT INTO USERAUTHORITY (IDUSER, IDAUTHORITY) VALUES ( 2, 2);
