<template>
  <div>
    <h1>首页</h1>
    <el-dialog
      title="温馨提示"
      :visible.sync="dialogVisible"
      width="30%"
      :modal='false'
    >
      <span>请输入账号和密码</span>
      <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="dialogVisible=false">确认</el-button>
              </span>
    </el-dialog>
  </div>
</template>

<script>
    export default {
        name: "Main",
        data(){
            return {
                dialogVisible: true
            }
        }
    }
</script>

<style scoped>

</style>
