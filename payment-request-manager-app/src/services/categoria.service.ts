import axios from "axios";
import type { InjectionKey } from "vue";
import type { Categoria } from "../types/categoria";

class CategoriaService {
  private readonly baseUrl = "http://localhost:5000";

  async buscarTodasAsCategorias(): Promise<Categoria[]> {
    try {
      const response = await axios.get(`${this.baseUrl}/categorias`);
      return response.data ?? [];
    } catch (error) {
      console.error("Erro ao buscar categorias:", error);
      return [];
    }
  }
}

const categoriaService = new CategoriaService();

export const categoriaServiceKey: InjectionKey<CategoriaService> = Symbol("categoriaService");

export { CategoriaService };
export default categoriaService;
