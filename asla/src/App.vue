<template>
  <div id="app">
    <router-link to="/main">首页</router-link>
    <router-link to="/content">内容页</router-link>
    <router-view></router-view>
  </div>
</template>

<script>
    import Main from "./views/Main";

    export default {
        name: 'App',
        components: {Main}
    }
</script>


