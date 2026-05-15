export interface Solicitacao {
    id: number;
    nomeDoSolicitante: string;
    cpfCnpj: string;
    nomeDaCategoria: string;
    status: string;
    valor: number;
}

export interface SolicitacaoCompleta {
    descricao: string;
    valor: number;
    dataSolicitacao: Date;
    status: string;
    nomeDaCategoria: string;
    nomeDoSolicitante: string;
    cpfCnpj: string;
}

export interface Filtro {
  status?: string;
  dataInicial?: Date;
  dataFinal?: Date;
  categoria?: string;
}