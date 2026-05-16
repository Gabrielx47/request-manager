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

export interface CamposErros {
    descricao?: string;
    valor?: string;
    dataSolicitacao?: string;
    status?: string;
    categoria_id?: string;
    solicitante_id?: string;
}

export interface NovaSolicitacao {
    descricao?: string;
    valor?: number;
    dataDaSolicitacao?: Date;
    status?: string;
    categoria_id?: number;
    solicitante_id?: number;
}

export interface Filtro {
  status?: string;
  dataInicial?: Date;
  dataFinal?: Date;
  categoria?: string;
}