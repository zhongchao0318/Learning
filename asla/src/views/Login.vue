<template>
  <div>
    <section>
      <div class="color"></div>
      <div class="color"></div>
      <div class="color"></div>
      <div class="box">
        <div class="square" style="--i:0;"></div>
        <div class="square" style="--i:1;">
          <!--          <img src="../../../images/elm.gif"-->
          <!--               style="height: 115px;width: 115px;margin: 2px;border-radius: 8px;">-->
        </div>
        <div class="square" style="--i:2;"></div>
        <div class="square" style="--i:3;"></div>
        <div class="square" style="--i:4;"></div>

        <div class="container">
          <div class="form">
            <h2>Login Form</h2>
            <el-form ref="loginForm" :model="form" :rules="rules">
              <el-form-item prop="username" class="inputBox">
                <el-input type="text" placeholder="Username" v-model="form.username"/>
              </el-form-item>
              <el-form-item prop="password" class="inputBox">
                <el-input type="password" placeholder="Password" v-model="form.password"/>
              </el-form-item>
              <el-form-item>
                <!--                <el-button class="inputBox"  v-on:click="onSubmit('loginForm')">登录</el-button>-->
                <div class="inputBox">
                  <input type="submit" value="Login" v-on:click="onSubmit('loginForm')">
                </div>
              </el-form-item>

              <p class="forget">Forgot Password ? <a href="#">Click Here</a></p>
              <p class="forget">Don't have an account ? <a href="#">Sign up</a></p>
            </el-form>
          </div>
        </div>
      </div>
    </section>
    <el-dialog
      title="温馨提示"
      :visible.sync="dialogVisible"
      width="30%"
      :modal='false'
    >
      <span>请输入账号和密码</span>
      <span slot="footer" class="dialog-footer">
                <el-button class="fix-button" type="primary" @click="dialogVisible=false">确认</el-button>
              </span>
    </el-dialog>
  </div>
</template>

<script>
    export default {
        name: "Login",
        data() {
            return {
                form: {
                    username: '',
                    password: ''
                },
                //表单验证，需要在el-form-item元素中加prop属性
                rules: {
                    username: [
                        {required: true, message: '账号不能为空', trigger: 'blur'}
                    ],
                    password: [
                        {required: true, message: '密码不能为空', trigger: 'blur'}
                    ]
                },
                //对话框提示和隐藏
                dialogVisible: false
            }
        },
        methods: {
            onSubmit(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.$router.push("/main/" + this.form.username);
                    } else {
                        this.dialogVisible = true;
                        return false;
                    }
                });
            },
        }
    }
</script>

<style scoped>
  .fix-button {
    padding: 12px 20px;
  }

  * {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: 'Poppins', sans-serif;
  }

  body {
    overflow: hidden;
  }

  section {
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
    background: linear-gradient(to bottom, #f1f4f9, #dff1ff);
  }

  section .color {
    position: absolute;
    filter: blur(150px);
  }

  section .color:nth-child(1) {
    top: -350px;
    width: 600px;
    height: 600px;
    background: #ff359b;
  }

  section .color:nth-child(2) {
    bottom: -150px;
    left: 100px;
    width: 500px;
    height: 500px;
    background: #fffd87;
  }

  section .color:nth-child(3) {
    bottom: 50px;
    right: 100px;
    width: 300px;
    height: 300px;
    background: #00d2ff;
  }

  .box {
    position: relative;
  }

  .box .square {
    position: absolute;
    backdrop-filter: blur(5px);
    box-shadow: 0 25px 45px rgba(0, 0, 0, 0.1);
    border: 1px solid rgba(255, 255, 255, 0.5);
    border-right: 1px solid rgba(255, 255, 255, 0.2);
    border-bottom: 1px solid rgba(255, 255, 255, 0.2);
    background: rgba(255, 255, 255, 0.1);
    border-radius: 10px;
    animation: animate 10s linear infinite;
    animation-delay: calc(-1s * var(--i));
  }

  @keyframes animate {
    0%, 100% {
      transform: translateY(-40px);
    }
    50% {
      transform: translateY(40px);
    }
  }

  .box .square:nth-child(1) {
    top: -50px;
    right: -60px;
    width: 100px;
    height: 100px;
  }

  .box .square:nth-child(2) {
    top: 150px;
    left: -100px;
    width: 120px;
    height: 120px;
    z-index: 2;
  }

  .box .square:nth-child(3) {
    bottom: 50px;
    right: -60px;
    width: 80px;
    height: 80px;
    z-index: 2;
  }

  .box .square:nth-child(4) {
    bottom: -80px;
    left: 100px;
    width: 50px;
    height: 50px;
  }

  .box .square:nth-child(5) {
    top: -80px;
    left: 140px;
    width: 60px;
    height: 60px;
  }

  .container {
    position: relative;
    width: 400px;
    min-height: 400px;
    background: rgba(255, 255, 255, 0.1);
    border-radius: 10px;
    display: flex;
    justify-content: center;
    align-items: center;
    backdrop-filter: blur(5px);
    box-shadow: 0 25px 45px rgba(0, 0, 0, 0.1);
    border: 1px solid rgba(255, 255, 255, 0.5);
    border-right: 1px solid rgba(255, 255, 255, 0.2);
    border-bottom: 1px solid rgba(255, 255, 255, 0.2);
  }

  .form {
    position: relative;
    width: 100%;
    height: 100%;
    padding: 40px;
  }

  .form h2 {
    position: relative;
    color: #fff;
    font-size: 24px;
    font-weight: 600;
    letter-spacing: 1px;
    margin-bottom: 40px;
  }

  .form h2::before {
    content: '';
    position: absolute;
    left: 0;
    bottom: -10px;
    width: 80px;
    height: 4px;
    background: #fff;
  }

  .form .inputBox {
    width: 100%;
    margin-top: 20px;
  }

  .form .inputBox input {
    width: 100%;
    background: rgba(255, 255, 255, 0.2);
    border: none;
    outline: none;
    padding: 10px 20px;
    border-radius: 35px;
    border: 1px solid rgba(255, 255, 255, 0.5);
    border-right: 1px solid rgba(255, 255, 255, 0.2);
    border-bottom: 1px solid rgba(255, 255, 255, 0.2);
    font-size: 16px;
    letter-spacing: 1px;
    color: #fff;
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.05);
  }

  .form .inputBox input::placeholder {
    color: #fff;
  }

  .form .inputBox input[type="submit"] {
    background: #fff;
    color: #666;
    max-width: 100px;
    cursor: pointer;
    margin-bottom: 20px;
    font-weight: 600;
  }

  .forget {
    margin-top: 5px;
    color: #fff;
  }

  .forget a {
    color: #fff;
    font-weight: 600;
  }
</style>
