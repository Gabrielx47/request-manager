<script setup lang="ts">
import { reactive, ref, onMounted } from 'vue';
import { DataTable, Column, Select, Dialog, Message} from 'primevue';
import 'primeicons/primeicons.css';
import axios from 'axios';
import type { Solicitacao, SolicitacaoCompleta } from '../types/solicitacao';

const loading = ref(false);
const message = ref('');
const messageSeverity = ref('');
const isMessageVisible = ref(false);
const numeroDapagina = ref(0);
const numeroDeRegistros = ref(2);
const baseUrl = "http://localhost:5000" 

let solicitacoes = reactive<Solicitacao[]>([]);
const totalRecords = ref(0);

const solicitacaoCompleta = ref<SolicitacaoCompleta>();
const isVisibleDialog = ref(false);

async function buscarTodosOsDadosDaSolicitacao(id: number) {
  axios.get(`${baseUrl}/solicitacoes/${id}`)
    .then(response => {
      const dados = response.data;
      solicitacaoCompleta.value = {
        descricao: dados.descricao,
        valor: dados.valor,
        dataSolicitacao: new Date(dados.dataSolicitacao),
        status: dados.status,
        nomeDaCategoria: dados.nomeDaCategoria,
        nomeDoSolicitante: dados.nomeDoSolicitante,
        cpfCnpj: dados.cpfCnpj
      };
      isVisibleDialog.value = true;
    })
    .catch(error => {
      console.error('Erro ao buscar dados da solicitação:', error);
    });
}


function onPage(event: any) {
  const { page, rows } = event;
  console.log(`Page: ${page}, Rows: ${rows}`);
  numeroDapagina.value = page;
  numeroDeRegistros.value = rows;
  listarDadosDasSolicitacoes(numeroDapagina.value, numeroDeRegistros.value);
}

async function  listarDadosDasSolicitacoes(numeroDapagina: number, numeroDeRegistros: number) {
  console.log(`Buscando dados para página ${numeroDapagina} com ${numeroDeRegistros} registros por página.`);
  loading.value = true;
  const url = `${baseUrl}/solicitacoes?numeroDaPagina=${numeroDapagina}&numeroDeElementosPorPagina=${numeroDeRegistros}`;

  try{
    const response = await fetch(url);
    const data = await response.json();
    console.log('Dados recebidos:', data);
    solicitacoes.splice(0, solicitacoes.length, ...data.content);
    totalRecords.value = data.page.totalElements; 
  } catch (error) {
    console.error('Erro ao buscar dados:', error);
    
  } finally {
    loading.value = false;
  }
}

async function carregarPrimeiraPagina() {
  loading.value = true;
  try {
    const response = await fetch(`${baseUrl}/solicitacoes?numeroDaPagina=0&numeroDeElementosPorPagina=2`);
    const data = await response.json();
    (data.content as Solicitacao[]).forEach((item: Solicitacao) => {
      console.log('Item recebido:', item);
    });
    solicitacoes.splice(0, solicitacoes.length, ...data.content);
    totalRecords.value = data.page.totalElements;
  } catch (error) {
    console.error('Error fetching data:', error);
  } finally {
    loading.value = false;
  }
}

async function atualizarStatusDaSolicitacao(id: number, novoStatus: string) {
    console.log(`Atualizando status da solicitação ${id} para ${novoStatus}`);
    console.log("isMessageVisible antes da atualização:", isMessageVisible.value);
    
    await axios.patch(`${baseUrl}/solicitacoes/${id}`, { status: novoStatus }).then(response => {
      console.log('Resposta da atualização de status:', response.data);
      message.value = response.data;
      messageSeverity.value = 'success';
      isMessageVisible.value = true;
    }).catch(error => {
      console.error('Erro ao atualizar status:', error);
      message.value = error.response?.data?.detail || 'Erro ao atualizar status';
      messageSeverity.value = 'error';
      isMessageVisible.value = true;
    });

    listarDadosDasSolicitacoes(numeroDapagina.value, numeroDeRegistros.value);
}

onMounted(() => {
    carregarPrimeiraPagina();
  });

</script>


<template>  
  <div id="message">
    <Message v-if="isMessageVisible" :severity="messageSeverity" closable @close="() => isMessageVisible = false">
      {{ message }}
    </Message>
  </div>

  <DataTable :value="solicitacoes"  paginator :rows="2" :totalRecords="totalRecords"  :lazy="true" @page="onPage($event)" >
    <Column field="nomeDoSolicitante" header="Nome"></Column>
    <Column field="cpfCnpj" header="CPF/CNPJ"></Column>
    <Column field="nomeDaCategoria" header="Categoria"></Column>  
    <Column field="status" header="Status">
      <template #body="{data}">
        <Select :options="['SOLICITADO', 'LIBERADO', 'APROVADO', 'REJEITADO', 'CANCELADO']" v-model="data.status" @change="atualizarStatusDaSolicitacao(data.id, data.status)" />
      </template>
    </Column>
    <Column field="valor" header="Valor">
      <template #body="slotProps">
        R$ {{slotProps.data.valor.toFixed(2)}}
      </template>
    </Column>
    <Column #body="{data}">
      <button  @click="buscarTodosOsDadosDaSolicitacao(data.id)">Ver Detalhes</button>
    </Column>  
  </DataTable>

  <Dialog v-model:visible="isVisibleDialog" header="Detalhes da Solicitação" modal>
    <div v-if="solicitacaoCompleta" modal >
        <p><strong>Solicitante:</strong> {{ solicitacaoCompleta.nomeDoSolicitante }}</p>
        <p><strong>CPF/CNPJ:</strong> {{ solicitacaoCompleta.cpfCnpj }}</p>
        <p><strong>Data da Solicitação:</strong> {{ solicitacaoCompleta.dataSolicitacao.toLocaleDateString() }}</p>
        <p><strong>Status:</strong> {{ solicitacaoCompleta.status }}</p>
        <p><strong>Categoria:</strong> {{ solicitacaoCompleta.nomeDaCategoria }}</p>
        <p><strong>Valor:</strong> R$ {{ solicitacaoCompleta.valor.toFixed(2) }}</p>
        <p><strong>Descrição:</strong> {{ solicitacaoCompleta.descricao }}</p>
    </div>
  </Dialog>
  
</template>


<style scoped>
#message {
  margin: 20px auto;
}
</style>