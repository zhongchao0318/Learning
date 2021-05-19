import Vue from 'vue'
import Router from 'vue-router'

import Content from "../components/Content";
import Main from "../views/Main";
import Login from "../views/Login";
//安装路由
Vue.use(Router);

export default new Router({
  routes: [
    {
      //路由路径
      path: '/content',
      name: 'content',
      //路由组件
      component: Content
    },
    {
      path: '/main',
      name: 'main',
      component: Main
    },
    {
      path: '/login',
      name: 'login',
      component: Login

    }
  ]
});
