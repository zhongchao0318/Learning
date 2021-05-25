import Vue from 'vue'
import Router from 'vue-router'

import Content from "../components/Content";
import Main from "../views/Main";
import Login from "../views/Login";
import UserList from "../views/user/List";
import UserProfile from "../views/user/Profile";
import NotFound from "../views/NotFound";
import Main2 from "../views/Main2";
//安装路由
Vue.use(Router);

export default new Router({
  mode: 'history',//去掉#号，默认是hash模式，路由中会有#
  routes: [
    {
      //路由路径
      path: '/content',
      name: 'content',
      //路由组件
      component: Content
    },
    {
      path: '/main2',
      component: Main2
    },
    {
      //路由路径
      path: '/',
      //路由组件
      component: Main
    },
    {
      path: '/main/:name',
      component: Main,
      props: true,
      children: [
        {
          path: '/user/profile/:id',
          name: "UserProfile",
          component: UserProfile,
          props: true
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
    },
    //重定向
    {
      path: '/goHome',
      redirect: '/main'
    }
    ,
    {
      path: "*",
      component: NotFound
    }
  ]
});
