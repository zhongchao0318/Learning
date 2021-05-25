// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'//自动扫码里面的路由配置

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

import axios from "axios";
import VueAxios from 'vue-axios';

Vue.use(VueAxios, axios);
Vue.config.productionTip = false;
Vue.use(ElementUI);

new Vue({
  el: '#app',
  router,
  render: h => h(App)
});
