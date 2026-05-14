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
