import axios from "axios";
import type { InjectionKey } from "vue";
import type { ResultadoCriacaoSolicitacao } from "../types/solicitacao";

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

  async criarNovaSolicitacao(payload: {
    descricao?: string;
    valor?: number;
    dataSolicitacao?: Date | null;
    status?: string;
    categoria_id?: number;
    solicitante_id?: number;
  }): Promise<ResultadoCriacaoSolicitacao> {
    try {
      const response = await axios.post(`${this.baseUrl}/solicitacoes`, {
        descricao: payload.descricao,
        valor: payload.valor,
        dataSolicitacao: payload.dataSolicitacao?.toISOString().split("T")[0],
        status: payload.status,
        categoria_id: payload.categoria_id,
        solicitante_id: payload.solicitante_id,
      });

      return {
        sucesso: true,
        mensagem: response.data,
      };
    } catch (error: any) {
      const errors = error.response?.data?.errors ?? {};
      console.error("Erro ao criar solicitação:", error);

      return {
        sucesso: false,
        mensagem: error.response?.data?.detail ?? "Erro ao criar solicitação",
        erros: {
          dataSolicitacao: errors.dataSolicitacao,
          descricao: errors.descricao,
          categoria_id: errors.categoria_id,
          solicitante_id: errors.solicitante_id,
          status: errors.status,
          valor: errors.valor,
        },
      };
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

export const solicitacaoServiceKey: InjectionKey<SolicitacaoService> = Symbol("solicitacaoService");

export { SolicitacaoService };
export default solicitacaoService;