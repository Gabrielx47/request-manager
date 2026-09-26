import axios from "axios";
import type { InjectionKey } from "vue";
import type { Solicitante } from "../types/solicitante";

class SolicitanteService {
  private readonly baseUrl = "http://localhost:5000";

  async buscarTodosOsSolicitantes(): Promise<Solicitante[]> {
    try {
      const response = await axios.get(`${this.baseUrl}/solicitantes`);
      return response.data ?? [];
    } catch (error) {
      console.error("Erro ao buscar solicitantes:", error);
      return [];
    }
  }
}

const solicitanteService = new SolicitanteService();

export const solicitanteServiceKey: InjectionKey<SolicitanteService> = Symbol("solicitanteService");

export { SolicitanteService };
export default solicitanteService;
