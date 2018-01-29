  CREATE DATABASE cliente;
  use cliente;
   
  CREATE TABLE Cliente(
       id INT PRIMARY KEY auto_increment,
       nome VARCHAR(30),
       cpf VARCHAR(11) unique,
       endereco VARCHAR(50),
       email varchar(30) unique,
       data_cadastro TIMESTAMP
       
  );

