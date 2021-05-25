<template>
  <div>
    <el-aside width="200px">
      <el-menu>
        <div v-for="item in menuList" :key="item.title">
          <!--没有子菜单-->
          <template v-if="item.children && item.children.length===0">
            <el-menu-item :index="item.title">
              <i class="el-icon-menu"></i>
              {{item.title}}
            </el-menu-item>
          </template>

          <!--有子菜单-->
          <el-submenu v-else :index="item.title">
            <template slot="title">
              <i class="el-icon-menu"></i>
              {{item.title}}
            </template>

            <template v-for="child in item.children">
              <sidebar-item v-if="child.children && child.children.length===0"
                            :item="child"
                            :key="child.title"
              >

              </sidebar-item>

              <el-menu-item v-else :key="child.title" :index="child.title">
                <i class="el-icon-location"></i>
                {{child.title}}
              </el-menu-item>
            </template>
          </el-submenu>
        </div>
      </el-menu>
    </el-aside>
  </div>
</template>

<script>
    export default {
        name: "Main2",
        data() {
            return {
                menuList: null
            }
        },
        methods: {
            getMenuData: function () {
                this.axios({
                    method: 'get',
                    url: "http://localhost:8081/static/mock/menu/mainMenu.json",
                }).then(response => {
                    console.log(response);
                    this.menuList = response.data;
                });
            }
        },
        beforeRouteEnter: (to, from, next) => {
            next(vm => {
                vm.getMenuData();
            });
        }
    }
</script>

<style scoped>

</style>
