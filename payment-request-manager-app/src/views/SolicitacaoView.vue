<script setup lang="ts">
import { ref, onMounted, reactive, inject } from 'vue';
import { RouterLink, useRouter } from 'vue-router';
import { Button, Select, DatePicker, InputNumber, InputText, Message } from 'primevue';
import type { NovaSolicitacao } from '@/types/solicitacao';
import type { Categoria } from '@/types/categoria';
import type { Solicitante } from '@/types/solicitante';
import type { CamposErros } from '@/types/solicitacao';
import solicitacaoServiceDefault, { solicitacaoServiceKey } from '../services/solicitacao.service';
import categoriaServiceDefault, { categoriaServiceKey } from '../services/categoria.service';

const router = useRouter();
const solicitacaoService = inject(solicitacaoServiceKey, solicitacaoServiceDefault);
const categoriaService = inject(categoriaServiceKey, categoriaServiceDefault);

function navegarParaHome() {
  router.push("/");
}

const novaSolicitacao = reactive<NovaSolicitacao>({
    descricao: undefined,
    valor: undefined,
    dataDaSolicitacao: undefined,
    status: undefined,
    categoria_id: undefined,
    solicitante_id: undefined
});

const erros = reactive<CamposErros>({
    descricao: undefined,
    valor: undefined,
    dataSolicitacao: undefined,
    status: undefined,
    categoria_id: undefined,
    solicitante_id: undefined
});

const categorias = ref<Categoria[]>([]);
const solicitantes = ref<Solicitante[]>([]);

async function obterCategorias() {
  categorias.value = await categoriaService.buscarTodasAsCategorias();
}

function obterSolicitantes() {
  fetch("http://localhost:5000/solicitantes")
    .then((response) => response.json())
    .then((data) => {
      solicitantes.value = data;
    })
    .catch((error) => {
      console.error("Erro ao buscar solicitantes:", error);
    });
}

async function criarNovaSolicitacao() {
  const resultado = await solicitacaoService.criarNovaSolicitacao({
    descricao: novaSolicitacao.descricao,
    valor: novaSolicitacao.valor,
    dataSolicitacao: novaSolicitacao.dataDaSolicitacao,
    status: novaSolicitacao.status,
    categoria_id: novaSolicitacao.categoria_id,
    solicitante_id: novaSolicitacao.solicitante_id,
  });

  if (resultado.sucesso) {
    console.log("Solicitação criada:" + resultado.mensagem);
    router.push({name: "Home", state: {mensagemDeCriacaoDaSolicitacao: resultado.mensagem}} );
    return;
  }

  if (!resultado.sucesso) {
    const errosApi = resultado.erros ?? {};
    erros.dataSolicitacao = errosApi.dataSolicitacao;
    erros.descricao = errosApi.descricao;
    erros.categoria_id = errosApi.categoria_id;
    erros.solicitante_id = errosApi.solicitante_id;
    erros.status = errosApi.status;
    erros.valor = errosApi.valor;
  }

  setTimeout(() => {
    erros.dataSolicitacao = '';
    erros.descricao = '';
    erros.categoria_id = '';
    erros.solicitante_id = '';
    erros.status = '';
    erros.valor = '';
  }, 10000);
}

onMounted(() => {
  obterCategorias();
  obterSolicitantes();
});
</script>

<template>
  <h1>Criar Solicitação</h1>
  
  <div id="formNewRequest">
    <div class="fields">
      <div style="width: 100%;" >
        <span><strong>CPF/CNPJ</strong></span>
        <Select style="width: 100%;" placeholder="CPF/CNPJ" :options="solicitantes" option-label="cpfCnpj" option-value="id" v-model="novaSolicitacao.solicitante_id" @change="erros.solicitante_id = ''">
        <template #option="slotProps">
          <div>
            {{ slotProps.option.cpfCnpj.replace(/^(\d{3})(\d{3})(\d{3})(\d{2})$/, '$1.$2.$3-$4').replace(/^(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})$/, '$1.$2.$3/$4-$5') }}
          </div>
        </template>
      </Select>
        <Message v-if="erros.solicitante_id" severity="error" size="small" variant="simple" :life="10000">{{erros.solicitante_id}}</Message>
      </div>
      
      <div style="width: 100%;">
        <span><strong>Data</strong></span>
        <DatePicker style="width: 100%;" v-model="novaSolicitacao.dataDaSolicitacao" placeholder="Data" showIcon dateFormat="yy-mm-dd"/>
        <Message v-if="erros.dataSolicitacao" severity="error" size="small" variant="simple" :life="10000" >{{erros.dataSolicitacao}}</Message>
      </div>
    </div>

    <div class="fields">
      <div style="width: 100%;">
        <span><strong>Status</strong></span>
        <Select style="width: 100%;" placeholder="Status" :options="['SOLICITADO']" v-model="novaSolicitacao.status" />
        <Message v-if="erros.status" severity="error" size="small" variant="simple" :life="10000" >{{erros.status}}</Message>
      </div>
      
      <div style="width: 100%;">
        <span><strong>Categoria</strong></span>
        <Select style="width: 100%;" placeholder="Categoria" :options="categorias" option-label="nome" option-value="id" v-model="novaSolicitacao.categoria_id" />
        <Message v-if="erros.categoria_id" severity="error" size="small" variant="simple" :life="10000" >{{erros.categoria_id}}</Message>
      </div>
    </div>

    <div class="fields">
      <div style="width: 100%;">
        <span><strong>Descrição</strong></span>
        <InputText style="width: 100%;" placeholder="Descrição" v-model="novaSolicitacao.descricao" />
        <Message v-if="erros.descricao" severity="error" size="small" variant="simple" :life="10000">{{erros.descricao}}</Message>
      </div>

      <div style="width: 100%;">
        <span><strong>Valor</strong></span>
        <InputNumber style="width: 100%;" placeholder="Valor R$" v-model="novaSolicitacao.valor" />
        <Message v-if="erros.valor" severity="error" size="small" variant="simple" :life="10000">{{erros.valor}}</Message>
      </div>
    </div>
  </div>

  
  <div style="display: flex; margin-top: 1rem; gap: 1rem; justify-content: end; margin-right: 5rem;">
    <Button label="Voltar" class="p-button-danger" @click="navegarParaHome" />
    <Button label="Salvar" @click="criarNovaSolicitacao" />
  </div>
</template>

<style scoped>
#formNewRequest{
  display: flex;
  flex-direction: column;
  row-gap: 1rem;
}

.fields{
  display: flex;
  gap: 1rem;
}

</style>