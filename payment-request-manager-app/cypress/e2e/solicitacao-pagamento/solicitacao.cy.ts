describe('Testando as Funcionalidades de Solicitação de Pagamento', () => {
  beforeEach(() => {
    cy.intercept('GET', 'http://localhost:5000/solicitacoes*', {
      fixture: 'solicitacao-pagamento/solicitacoes.json',
    }).as('listarSolicitacoes') ;
    cy.intercept('GET', 'http://localhost:5000/solicitacoes/*', {
      fixture: 'solicitacao-pagamento/solicitacao-1-detalhes.json',
    }).as('detalharSolicitacao');
    cy.intercept('GET', 'http://localhost:5000/categorias', {
      fixture: 'solicitacao-pagamento/categorias.json',
    }).as('listarCategorias');
    cy.intercept('GET', 'http://localhost:5000/solicitantes', {
      fixture: 'solicitacao-pagamento/solicitantes.json',
    });
    cy.intercept('POST', 'http://localhost:5000/solicitacoes', {
      statusCode: 201,
      fixture: 'solicitacao-pagamento/solicitacao-criada.json',
    }).as('criarSolicitacao');

    cy.visit('/');
  });

  it('Deve exibir as colunas da tabela de solicitações', () => {
    cy.get('thead th').should(($headers) => {
      expect($headers).to.have.length(6);
      expect(
        [...$headers]
          .map((header) => header.innerText.trim())
          .filter(Boolean),
      ).to.deep.equal(['Nome', 'CPF/CNPJ', 'Categoria', 'Status', 'Valor']);
    });
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

  it('Deve verificar os detalhes de uma solicitação', () => {
    cy.wait('@listarSolicitacoes');
    cy.wait('@listarCategorias');

    cy.contains('button', 'Detalhes').click({ force: true });
    cy.wait('@detalharSolicitacao');

    cy.fixture('solicitacao-pagamento/solicitacao-1-detalhes.json').then((solicitacao) => {
      const dataSolicitacao = new Date(solicitacao.dataSolicitacao).toLocaleDateString('pt-BR');

      cy.get('.details-dialog').should('be.visible').within(() => {
        cy.contains('p', `Solicitante: ${solicitacao.nomeDoSolicitante}`).should('be.visible');
        cy.contains('p', `CPF/CNPJ: ${solicitacao.cpfCnpj}`).should('be.visible');
        cy.contains('p', `Data da Solicitação: ${dataSolicitacao}`).should('be.visible');
        cy.contains('p', `Status: ${solicitacao.status}`).should('be.visible');
        cy.contains('p', `Categoria: ${solicitacao.nomeDaCategoria}`).should('be.visible');
        cy.contains('p', `Valor: R$ ${solicitacao.valor.toFixed(2)}`).should('be.visible');
        cy.contains('p', `Descrição: ${solicitacao.descricao}`).should('be.visible');
      });
    });
  });
});