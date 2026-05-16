# Payment Request Manager App

Aplicação web frontend para gerenciamento de solicitações de pagamento, desenvolvida com Vue 3, TypeScript e Vite.

## 🛠️ Tech Stack

- **Vue 3** - Framework JavaScript progressivo
- **TypeScript** - Tipagem estática para JavaScript
- **Vite** - Build tool rápido e moderno
- **PrimeVue** - Biblioteca de componentes UI
- **Axios** - Cliente HTTP
- **Vue Router** - Roteamento de página

## 📋 Requisitos

- **Node.js**: 20.19.0 ou superior, ou 22.12.0 ou superior
- **npm**: Incluído com Node.js
- **API REST**: Rodando em `http://localhost:5000` (ver instruções no README raiz)

## 🚀 Configuração e Execução

### 1. Instalar Dependências

```bash
npm install
```

### 2. Executar em Desenvolvimento

```bash
npm run dev
```

A aplicação estará disponível em `http://localhost:5173` (ou outra porta informada pelo Vite).

### 3. Build para Produção

```bash
npm run build
```

Os arquivos otimizados serão gerados na pasta `dist/`.

### 4. Preview da Build de Produção

```bash
npm run preview
```

## 📚 Scripts Disponíveis

| Script | Descrição |
|--------|-----------|
| `npm run dev` | Inicia o servidor de desenvolvimento com hot reload |
| `npm run build` | Compila e minifica para produção |
| `npm run preview` | Visualiza a build de produção localmente |
| `npm run type-check` | Verifica tipos TypeScript sem compilar |

## 🏗️ Estrutura do Projeto

```
src/
├── components/          # Componentes reutilizáveis
│   ├── Header.vue      # Cabeçalho da aplicação
│   └── icons/          # Componentes de ícones
├── views/              # Páginas da aplicação
│   ├── HomeView.vue    # Página inicial
│   └── SolicitacaoView.vue  # Página de solicitações
├── router/
│   └── index.ts        # Configuração de rotas
├── types/              # Tipos TypeScript
│   ├── categoria.ts
│   ├── solicitacao.ts
│   └── solicitante.ts
├── assets/
│   └── main.css        # Estilos globais
├── App.vue             # Componente raiz
└── main.ts             # Ponto de entrada
```

## 🎨 Estilização

A aplicação utiliza **PrimeVue** com temas customizáveis. O tema padrão pode ser alterado em `main.ts` ou `App.vue`.

## 🔌 Integração com a API

A aplicação se comunica com a API REST em `http://localhost:8080` via **Axios**. 

Para alterar a URL base da API, procure pelas configurações de `baseURL` nos arquivos que fazem requisições HTTP.

## 🛠️ Ferramentas de Desenvolvimento Recomendadas

### VS Code Extensions
- [Vue Official](https://marketplace.visualstudio.com/items?itemName=Vue.volar) - Suporte completo para Vue 3
- [TypeScript Vue Plugin](https://marketplace.visualstudio.com/items?itemName=Vue.vscode-typescript-vue-plugin) - Suporte TypeScript em arquivos .vue

### Browser DevTools
- [Vue.js DevTools](https://devtools.vuejs.org/)
- Chrome DevTools (integrado)

## 🐛 Troubleshooting

### Problema: "Porta 5173 já está em uso"
**Solução**: Vite usará automaticamente a próxima porta disponível, ou configure em `vite.config.ts`

### Problema: "Erro de CORS ao acessar a API"
**Solução**: Configure CORS na API Spring Boot para aceitar requisições de `http://localhost:5173`

### Problema: "Módulos não encontrados após npm install"
**Solução**: 
```bash
rm -rf node_modules package-lock.json
npm install
```

## 📖 Referências

- [Documentação Vue 3](https://vuejs.org/)
- [Documentação Vite](https://vitejs.dev/)
- [Documentação PrimeVue](https://primevue.org/)
- [Documentação TypeScript](https://www.typescriptlang.org/)

## 👨‍💻 Desenvolvimento

Para mais informações sobre execução local, veja o [README Principal](../README.md)

