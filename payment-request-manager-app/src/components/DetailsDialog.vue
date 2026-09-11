<script setup lang="ts">
import { computed } from 'vue';
import { Dialog } from 'primevue';
import type { CampoDetalhe, SolicitacaoCompleta } from '../types/solicitacao';

const props = defineProps<{
  modelValue: boolean;
  campos?: CampoDetalhe[];
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
    <div v-if="props.campos?.length" class="details-dialog">
      <p v-for="campo in props.campos" :key="campo.titulo">
        <strong>{{ campo.titulo }}:</strong> {{ campo.valor ?? '' }}
      </p>
    </div>
  </Dialog>
</template>

<style scoped>
.details-dialog {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
}

p {
  margin: 0;
}
</style>
