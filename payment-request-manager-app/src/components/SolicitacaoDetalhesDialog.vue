<script setup lang="ts">
import { computed } from 'vue';
import { Dialog } from 'primevue';
import type { SolicitacaoCompleta } from '../types/solicitacao';

const props = defineProps<{
  modelValue: boolean;
  solicitacao: SolicitacaoCompleta | null | undefined;
}>();

const emit = defineEmits<{
  'update:modelValue': [value: boolean];
}>();

const visible = computed({
  get: () => props.modelValue,
  set: (value: boolean) => emit('update:modelValue', value),
});

function formatarCpfCnpj(valor?: string) {
  if (!valor) return '';

  return valor
    .replace(/^(\d{3})(\d{3})(\d{3})(\d{2})$/, '$1.$2.$3-$4')
    .replace(/^(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})$/, '$1.$2.$3/$4-$5');
}

function formatarData(data?: Date | string) {
  if (!data) return '';

  return new Date(data).toLocaleDateString('pt-BR');
}
</script>

<template>
  <Dialog
    v-model:visible="visible"
    header="Detalhes da Solicitação"
    modal
    :dismissable-mask="true"
    :draggable="false"
  >
    <div v-if="solicitacao" class="solicitacao-detalhes">
      <p><strong>Solicitante:</strong> {{ solicitacao.nomeDoSolicitante }}</p>
      <p><strong>CPF/CNPJ:</strong> {{ formatarCpfCnpj(solicitacao.cpfCnpj) }}</p>
      <p><strong>Data da Solicitação:</strong> {{ formatarData(solicitacao.dataSolicitacao) }}</p>
      <p><strong>Status:</strong> {{ solicitacao.status }}</p>
      <p><strong>Categoria:</strong> {{ solicitacao.nomeDaCategoria }}</p>
      <p><strong>Valor:</strong> R$ {{ solicitacao.valor.toFixed(2) }}</p>
      <p><strong>Descrição:</strong> {{ solicitacao.descricao }}</p>
    </div>
  </Dialog>
</template>

<style scoped>
.solicitacao-detalhes {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
}

p {
  margin: 0;
}
</style>
