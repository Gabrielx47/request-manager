describe('Testando as Funcionalidades de Solicitação de Pagamento', () => {
  beforeEach(() => {
    cy.intercept('GET', 'http://localhost:5000/solicitacoes*', {
      body: { content: [], page: { totalElements: 0 } },
    });
    cy.intercept('GET', 'http://localhost:5000/categorias', {
      body: [{ id: 1, nome: 'Operacional' }],
    });
    cy.intercept('GET', 'http://localhost:5000/solicitantes', {
      body: [{ id: 1, cpfCnpj: '12345678909', nome: 'Pessoa de Teste' }],
    });
    cy.intercept('POST', 'http://localhost:5000/solicitacoes', {
      statusCode: 201,
      body: 'Solicitação criada com sucesso',
    }).as('criarSolicitacao');

    cy.visit('/');
  });

  it('Deve criar uma nova solicitação', () => {
    cy.contains('button', 'Criar').click();

    cy.get('#cpfCnpj').click();
    cy.contains('[role="option"]', '123.456.789-09').click();

    cy.get('#dataDaSolicitacao input').type('2026-11-03').blur();

    cy.get('#status').click();
    cy.contains('[role="option"]', 'SOLICITADO').click();

    cy.get('#categoria').click();
    cy.contains('[role="option"]', 'Operacional').click();

    cy.get('#descricao').type('Descrição da nova solicitação');
    cy.get('#valor input').type('123.45');
    cy.contains('button', 'Salvar').click();

    cy.wait('@criarSolicitacao').its('request.body').should('deep.equal', {
      descricao: 'Descrição da nova solicitação',
      valor: 123.45,
      dataSolicitacao: '2026-11-03',
      status: 'SOLICITADO',
      categoria_id: 1,
      solicitante_id: 1,
    });
    cy.contains('Solicitação criada com sucesso').should('be.visible');
  });
});