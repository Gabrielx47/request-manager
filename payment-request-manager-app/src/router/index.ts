import { createRouter, createWebHistory } from "vue-router";

import HomeView from "../views/HomeView.vue";
import SolicitacaoView from "@/views/SolicitacaoView.vue";


const routes = [
    {path: '/', component: HomeView, name: "Home"},
    {path: '/solicitacoes', component: SolicitacaoView, props: true}
];

export const router = createRouter({
    history: createWebHistory(),
    routes
});