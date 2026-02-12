 create database Finpay;
 CREATE TABLE prestataire(
 id INT auto_increment,
 nom varchar(50),
 adress varchar(77),
 FOREIGN KEY (prestataire_id) REFERENCES prestataires(id)
 )