<script setup lang="ts">
import { ref, onMounted } from 'vue';
import axios from 'axios';

const props = defineProps(['idDaSolicitacao']);

interface Solicitacao {
   descricao: string;
    valor: number;
    dataSolicitacao: Date;
    status: string;
    nomeDaCategoria: string;
    nomeDoSolicitante: string;
    cpfCnpj: string;
}

const solicitacao = ref<Solicitacao>();
const baseUrl = "http://localhost:5000";

async function buscarTodosOsDadosDaSolicitacao() {
  // Simulação de busca de dados da solicitação
  axios.get(`${baseUrl}/solicitacoes/${props.idDaSolicitacao}`)
    .then(response => {
      const dados = response.data;
      solicitacao.value = {
        descricao: dados.descricao,
        valor: dados.valor,
        dataSolicitacao: new Date(dados.dataSolicitacao),
        status: dados.status,
        nomeDaCategoria: dados.nomeDaCategoria,
        nomeDoSolicitante: dados.nomeDoSolicitante,
        cpfCnpj: dados.cpfCnpj
      };
    })
    .catch(error => {
      console.error('Erro ao buscar dados da solicitação:', error);
    });
}

onMounted(() => {
    console.log('ID da solicitação:', props.idDaSolicitacao);
    buscarTodosOsDadosDaSolicitacao();
});

</script>

<template>
  <h1>Solicitação de Pagamento</h1>
  <p>Esta é a página de solicitação de pagamento.</p>
    <div v-if="solicitacao">
        <p><strong>Descrição:</strong> {{ solicitacao.descricao }}</p>
        <p><strong>Valor:</strong> R$ {{ solicitacao.valor }}</p>
        <p><strong>Data da Solicitação:</strong> {{ solicitacao.dataSolicitacao.toLocaleDateString() }}</p>
        <p><strong>Status:</strong> {{ solicitacao.status }}</p>
        <p><strong>Categoria:</strong> {{ solicitacao.nomeDaCategoria }}</p>
        <p><strong>Solicitante:</strong> {{ solicitacao.nomeDoSolicitante }}</p>
        <p><strong>CPF/CNPJ:</strong> {{ solicitacao.cpfCnpj }}</p>
    </div>
</template>

<style scoped>

</style>