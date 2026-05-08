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
