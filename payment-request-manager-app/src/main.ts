import './assets/main.css'

import { createApp } from 'vue'
import PrimeVue from 'primevue/config'
import Aura from '@primeuix/themes/aura'
import Lara from '@primeuix/themes/lara'
import Material from '@primeuix/themes/material'
import Nora from '@primeuix/themes/nora'
import { router } from './router'
import App from './App.vue'
import solicitacaoService, { solicitacaoServiceKey } from './services/solicitacao.service'
import solicitanteService, { solicitanteServiceKey } from './services/solicitante.service'

const app = createApp(App)

app.provide(solicitacaoServiceKey, solicitacaoService)
app.provide(solicitanteServiceKey, solicitanteService)

app.use(PrimeVue, {
    theme : {
        preset: Aura,
        options: {
            darkModeSelector: "none",
        }
    }
});
app.use(router).mount('#app');
