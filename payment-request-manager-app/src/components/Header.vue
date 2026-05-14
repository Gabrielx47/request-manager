<script setup lang="ts">
import { Menubar } from 'primevue';
import { RouterLink } from 'vue-router';

const menuItems = [
  { label: 'Home', icon: 'pi pi-home', to: '/' },
];
</script>

<template>
  <Menubar :model="menuItems">
    <template #item="{item, props}">
      <RouterLink :to="item.to" v-slot="{href, navigate}" custom >
        <a v-ripple :href="href" v-bind="props.action" @click="navigate">
          <span :class="item.icon"></span>
          <span>{{ item.label }}</span>
        </a>
      </RouterLink>
    </template>
  </Menubar>
</template>

