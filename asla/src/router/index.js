import Vue from 'vue'
import Router from 'vue-router'

import Content from "../components/Content";
import Main from "../views/Main";
import Login from "../views/Login";
import UserList from "../views/user/List";
import UserProfile from "../views/user/Profile";
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
      component: Main,
      children: [
        {
          path: '/user/profile',
          component: UserProfile
        },
        {
          path: '/user/list',
          component: UserList
        }
      ]
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    }

  ]
});
