CREATE DATABASE projetjava;
USE projetjava;

CREATE TABLE  clients (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255),
    prenom VARCHAR(255),
    specialite VARCHAR(255),
    club VARCHAR(255)
);