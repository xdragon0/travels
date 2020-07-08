import Vue from 'vue'
import App from './App'

import router from './router' //自动扫描里面的路由配置

import axios from 'axios';
// import VueAxios from 'vue-axios'

import './css/style.css'

Vue.prototype.axios = axios;

axios.defaults.baseURL = "/api"
axios.defaults.withCredentials = true
Vue.use(axios);
// Vue.use(VueAxios,axios);

Vue.config.productionTip = false


new Vue({
  el: '#app',
   //配置路由
  router,
  components: { App },
  template: '<App/>'
})
