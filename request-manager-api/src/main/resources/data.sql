INSERT INTO tb_solicitante (id, nome, cpf_cnpj) 
VALUES (1, "João da Silva", "12345678900");

INSERT INTO tb_solicitante (id, nome, cpf_cnpj) 
VALUES (2, "Maria Oliveira", "98765432100");

INSERT INTO tb_solicitante (id, nome, cpf_cnpj)
VALUES (3, "Empresa XYZ Ltda", "12345678000199");

INSERT INTO tb_solicitante (id, nome, cpf_cnpj)
VALUES (4, "Empresa ABC Ltda", "98765432000188");

INSERT INTO tb_solicitante (id, nome, cpf_cnpj)
VALUES (5, "Carlos Pereira", "11122233344");

INSERT INTO tb_solicitante (id, nome, cpf_cnpj)
VALUES (6, "Fernanda Santos", "22233344455");

INSERT INTO tb_solicitante (id, nome, cpf_cnpj)
VALUES (7, "Eduardo Lima", "33344455566");

INSERT INTO tb_solicitante (id, nome, cpf_cnpj)
VALUES (8, "Construtora Delta Ltda", "44556677000188");


INSERT INTO tb_categoria (id, nome)
VALUES (1, "Eletrônicos");

INSERT INTO tb_categoria (id, nome)
VALUES (2, "Móveis");

INSERT INTO tb_categoria (id, nome)
VALUES (3, "Serviços");

INSERT INTO tb_categoria (id, nome)
VALUES (4, "Material de Escritório");

INSERT INTO tb_categoria (id, nome)
VALUES (5, "Outros");

INSERT INTO tb_categoria (id, nome)
VALUES (6, "Marketing");

INSERT INTO tb_categoria (id, nome)
VALUES (7, "Viagens");

INSERT INTO tb_categoria (id, nome)
VALUES (8, "Saúde");


INSERT INTO tb_solicitacao (id, descricao, valor, data_solicitacao, status, solicitante_id, categoria_id)
VALUES (1, "Compra de um novo laptop", 3500.00, "2024-06-01", "SOLICITADO", 1, 1);

INSERT INTO tb_solicitacao (id, descricao, valor, data_solicitacao, status, solicitante_id, categoria_id)
VALUES (2, "Compra de uma nova mesa de escritório", 800.00, "2024-06-02", "SOLICITADO", 2, 2);

INSERT INTO tb_solicitacao (id, descricao, valor, data_solicitacao, status, solicitante_id, categoria_id)
VALUES (3, "Contratação de um serviço de manutenção", 1500.00, "2024-06-03", "SOLICITADO", 3, 3);

INSERT INTO tb_solicitacao (id, descricao, valor, data_solicitacao, status, solicitante_id, categoria_id)
VALUES (4, "Compra de material de escritório", 500.00, "2024-06-04", "LIBERADO", 4, 4);

INSERT INTO tb_solicitacao (id, descricao, valor, data_solicitacao, status, solicitante_id, categoria_id)
VALUES (5, "Solicitação de um serviço de limpeza", 300.00, "2024-06-05", "REJEITADO", 5, 3);

INSERT INTO tb_solicitacao (id, descricao, valor, data_solicitacao, status, solicitante_id, categoria_id)
VALUES (6, "Solicitação de novo equipamento de rede", 1200.00, "2024-06-06", "SOLICITADO", 6, 1);

INSERT INTO tb_solicitacao (id, descricao, valor, data_solicitacao, status, solicitante_id, categoria_id)
VALUES (7, "Renovação de assinatura de software", 450.00, "2024-06-07", "LIBERADO", 7, 5);

INSERT INTO tb_solicitacao (id, descricao, valor, data_solicitacao, status, solicitante_id, categoria_id)
VALUES (8, "Campanha de marketing digital", 1800.00, "2024-06-08", "APROVADO", 8, 6);

INSERT INTO tb_solicitacao (id, descricao, valor, data_solicitacao, status, solicitante_id, categoria_id)
VALUES (9, "Passagem aérea para treinamento", 2300.00, "2024-06-09", "REJEITADO", 1, 7);

INSERT INTO tb_solicitacao (id, descricao, valor, data_solicitacao, status, solicitante_id, categoria_id)
VALUES (10, "Compra de material de proteção", 650.00, "2024-06-10", "CANCELADO", 2, 4);

INSERT INTO tb_solicitacao (id, descricao, valor, data_solicitacao, status, solicitante_id, categoria_id)
VALUES (11, "Serviço de consultoria financeira", 2900.00, "2024-06-11", "SOLICITADO", 3, 3);

INSERT INTO tb_solicitacao (id, descricao, valor, data_solicitacao, status, solicitante_id, categoria_id)
VALUES (12, "Compra de cadeiras ergonômicas", 980.00, "2024-06-12", "LIBERADO", 4, 2);

INSERT INTO tb_solicitacao (id, descricao, valor, data_solicitacao, status, solicitante_id, categoria_id)
VALUES (13, "Assinatura de plataforma de saúde", 760.00, "2024-06-13", "APROVADO", 5, 8);

INSERT INTO tb_solicitacao (id, descricao, valor, data_solicitacao, status, solicitante_id, categoria_id)
VALUES (14, "Reparo de impressora", 220.00, "2024-06-14", "REJEITADO", 6, 4);

INSERT INTO tb_solicitacao (id, descricao, valor, data_solicitacao, status, solicitante_id, categoria_id)
VALUES (15, "Evento corporativo de integração", 3400.00, "2024-06-15", "CANCELADO", 7, 7);
