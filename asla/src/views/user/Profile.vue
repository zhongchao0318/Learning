<template>
  <div>
    <h1>个人信息页</h1>
    {{userInfo}}
    <!--    <p>{{$route.params.id}}</p>-->
  </div>

</template>

<script>
    export default {
        props: ['id'],
        name: "Profile",
        data() {
            return {
                userInfo: null
            }
        },
        //过滤器
        //to:路由将要跳转的路径信息
        //form:路径跳转前的路径信息
        //next:路由的控制参数
        //next()跳入下一个页面
        //next('path')改变路由的跳转方向，使其跳转到另一个路由
        //next(false)返回原来的页面
        //next((vm)=>{})仅在beforeRouteEnter中可用，vm是组件实例
        beforeRouteEnter: (to, form, next) => {
            console.log('1111');
            next(vm => {
                vm.getData();
            });
        },
        beforeRouteLeave: (to, form, next) => {
            console.log('222');
            next();
        },
        methods: {
            getData: function () {
                this.axios({
                    method: 'get',
                    url: 'http://localhost:8081/static/mock/data.json'
                }).then(response => {
                    console.log(response);
                    this.userInfo = response.data
                });
            }
        }

    }
</script>

<style scoped>

</style>
