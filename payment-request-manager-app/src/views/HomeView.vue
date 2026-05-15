<script setup lang="ts">
import { reactive, ref, onMounted } from 'vue';
import { DataTable, Column, Select, Dialog, Message, Button, DatePicker} from 'primevue';
import 'primeicons/primeicons.css';
import axios from 'axios';
import type { Solicitacao, SolicitacaoCompleta, Filtro } from '../types/solicitacao';

const loading = ref(false);
const message = ref('');
const messageSeverity = ref('');
const isMessageVisible = ref(false);
const numeroDaPagina = ref(0);
const numeroDeRegistros = ref(2);
const baseUrl = "http://localhost:5000" 

let solicitacoes = reactive<Solicitacao[]>([]);
const totalRecords = ref(0);

const solicitacaoCompleta = ref<SolicitacaoCompleta>();
const isVisibleDetailDialog = ref(false);
const isVisibleFilterDialog = ref(false);
const categorias = ref<string[]>([]);
const filtro = reactive<Filtro>({
  status: undefined,
  dataInicial: undefined,
  dataFinal: undefined,
  categoria: undefined
});

async function aplicarFiltros() {
  listarDadosDasSolicitacoes();
  isVisibleFilterDialog.value = false;
}

function limparFiltros() {
   filtro.status = undefined; 
   filtro.dataInicial = undefined; 
   filtro.dataFinal = undefined; 
   filtro.categoria = undefined; 
}


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
      isVisibleDetailDialog.value = true;
    })
    .catch(error => {
      console.error('Erro ao buscar dados da solicitação:', error);
    });
}


function onPage(event: any) {
  const { page, rows } = event;
  console.log(`Page: ${page}, Rows: ${rows}`);
  numeroDaPagina.value = page;
  numeroDeRegistros.value = rows;
  loading.value = true;
  listarDadosDasSolicitacoes();
  loading.value = false;
}

async function  listarDadosDasSolicitacoes() {
  console.log(`Buscando dados para página ${numeroDaPagina.value} com ${numeroDeRegistros.value} registros por página.`);
  const params: any = {};
  
  if (filtro.status) params.status = filtro.status;
  if (filtro.dataInicial) params.dataInicial = filtro.dataInicial.toISOString().split('T')[0]; 
  if (filtro.dataFinal) params.dataFinal = filtro.dataFinal.toISOString().split('T')[0]; 
  if (filtro.categoria) params.categoria = filtro.categoria;
  params.numeroDaPagina = numeroDaPagina.value;
  params.numeroDeElementosPorPagina = numeroDeRegistros.value;

  try {
    const response = await axios.get(`${baseUrl}/solicitacoes`, { params });
    solicitacoes.splice(0, solicitacoes.length, ...response.data.content);
    totalRecords.value = response.data.page.totalElements;
  } catch (error) {
    console.error('Erro ao aplicar filtros:', error);
    message.value = 'Erro ao aplicar filtros';
    messageSeverity.value = 'error';
  }
}


async function encontrarTodasCategorias() {
  try {
    const response = await fetch(`${baseUrl}/categorias`);
    const data = await response.json();
    categorias.value = data;
    console.log('Categorias recebidas:', data);
  } catch (error) {
    console.error('Erro ao buscar categorias:', error);
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

    listarDadosDasSolicitacoes();
}

onMounted(() => {
    listarDadosDasSolicitacoes();
    encontrarTodasCategorias();
  });

</script>


<template>  
  <div id="message">
    <Message v-if="isMessageVisible" :severity="messageSeverity" closable @close="() => isMessageVisible = false">
      {{ message }}
    </Message>
  </div>

  <DataTable  :value="solicitacoes" :loading="loading"  paginator :rows="numeroDeRegistros" :totalRecords="totalRecords"  :lazy="true" @page="onPage($event)" >
    <template #header>
      <div class="table-header">
        <h2>Solicitações de Pagamento</h2>
        <Button label="Filtrar"  @click="isVisibleFilterDialog = true" icon="pi pi-filter" iconPos="right"/>
      </div>
    </template>

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

  <Dialog v-model:visible="isVisibleDetailDialog" header="Detalhes da Solicitação" modal>
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

  <Dialog v-model:visible="isVisibleFilterDialog" header="Filtrar Solicitações" modal>
    <div style="display: flex; flex-direction: column; gap: 1rem;">
      <Select v-model="filtro.status" placeholder="Status" :options="['SOLICITADO', 'LIBERADO', 'APROVADO', 'REJEITADO', 'CANCELADO']" />
      <div style="display: flex; gap: 1rem;">
        <DatePicker v-model="filtro.dataInicial" placeholder="Data de Inicial" showIcon dateFormat="yy-mm-dd"/>
        <DatePicker v-model="filtro.dataFinal" placeholder="Data de Final" showIcon />
      </div>
      <Select v-model="filtro.categoria" placeholder="Categoria" :options="categorias" />
      <div style="display: flex; gap: 1rem; align-self: center;" >
        <Button label="Limpar" icon="pi pi-filter-slash" iconPos="right" @click="limparFiltros" class="p-button-danger" />
        <Button label="Aplicar" icon="pi pi-filter-fill" iconPos="right" @click="aplicarFiltros" />
      </div>
    </div>
  </Dialog>
  
</template>


<style scoped>
#message {
  margin: 20px auto;
}

.table-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
}
</style>