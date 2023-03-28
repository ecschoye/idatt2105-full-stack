import { createApp } from "vue";
import { createPinia } from "pinia";
import piniaPluginPersistedState from "pinia-plugin-persistedstate";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import "./assets/main.css";
import "./assets/tailwind.css";

const pinia = createPinia();
pinia.use(piniaPluginPersistedState);

createApp(App).use(store).use(pinia).use(router).mount("#app");
