<template>
  <div id="app">
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


