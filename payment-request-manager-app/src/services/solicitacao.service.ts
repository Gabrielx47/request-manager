import axios from "axios";

const baseUrl = "http://localhost:5000"

async function buscarTodosOsDadosDaSolicitacao(id: number) {
  try {
    const response = await axios.get(`${baseUrl}/solicitacoes/${id}`);
    const dados = response.data;
    
    return {
      descricao: dados.descricao,
      valor: dados.valor,
      dataSolicitacao: new Date(dados.dataSolicitacao),
      status: dados.status,
      nomeDaCategoria: dados.nomeDaCategoria,
      nomeDoSolicitante: dados.nomeDoSolicitante,
      cpfCnpj: dados.cpfCnpj
    };
  } catch (error) {
    console.error('Erro ao buscar dados da solicitação:', error);
  }
}

export { buscarTodosOsDadosDaSolicitacao };