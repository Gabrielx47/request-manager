DROP DATABASE if exists mydatabase;
CREATE DATABASE mydatabase;

USE mydatabase;

DROP TABLE IF EXISTS `tb_categoria`;

CREATE TABLE `tb_categoria` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ;

DROP TABLE IF EXISTS `tb_solicitante`;
CREATE TABLE `tb_solicitante` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cpf_cnpj` varchar(14) NOT NULL,
  `nome` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`cpf_cnpj`)
);

DROP TABLE IF EXISTS `tb_solicitacao`;
CREATE TABLE `tb_solicitacao` (
  `data_solicitacao` date NOT NULL,
  `valor` decimal(38,2) NOT NULL,
  `categoria_id` bigint DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `solicitante_id` bigint DEFAULT NULL,
  `descricao` varchar(200) NOT NULL,
  `status` enum('APROVADO','CANCELADO','LIBERADO','REJEITADO','SOLICITADO') NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT FOREIGN KEY (`categoria_id`) REFERENCES `tb_categoria` (`id`),
  CONSTRAINT FOREIGN KEY (`solicitante_id`) REFERENCES `tb_solicitante` (`id`)
);

