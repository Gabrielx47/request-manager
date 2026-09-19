import axios from "axios";

class SolicitacaoService {
  private readonly baseUrl = "http://localhost:5000";

  async buscarTodosOsDadosDaSolicitacao(id: number) {
    try {
      const response = await axios.get(`${this.baseUrl}/solicitacoes/${id}`);
      const dados = response.data;

      return {
        descricao: dados.descricao,
        valor: dados.valor,
        dataSolicitacao: new Date(dados.dataSolicitacao),
        status: dados.status,
        nomeDaCategoria: dados.nomeDaCategoria,
        nomeDoSolicitante: dados.nomeDoSolicitante,
        cpfCnpj: dados.cpfCnpj,
      };
    } catch (error) {
      console.error("Erro ao buscar dados da solicitação:", error);
      return null;
    }
  }

  async atualizarStatusDaSolicitacao(id: number, novoStatus: string) {
    try {
      const response = await axios.patch(`${this.baseUrl}/solicitacoes/${id}`, {
        status: novoStatus,
      });

      return {
        sucesso: true,
        mensagem: response.data,
      };
    } catch (error: any) {
      const mensagem = error.response?.data?.detail || "Erro ao atualizar status";
      console.error("Erro ao atualizar status da solicitação:", error);

      return {
        sucesso: false,
        mensagem,
      };
    }
  }
}

const solicitacaoService = new SolicitacaoService();

export { SolicitacaoService };
export default solicitacaoService;