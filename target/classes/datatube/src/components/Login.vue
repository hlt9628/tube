<script>

import {root} from '../../.eslintrc'

export default {
  name: "Login",
  data(){
    return{
      loginForm:{
        userAccount:"",
        userPassword:""
      },
      loginRules: {
        userAccount: [
          {required: true, message: '请输入账户', trigger: 'blur'},
          {min: 1, max: 11, message: '长度在 1 到 11 个字符', trigger: 'blur'}
        ],
        userPassword: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 1, max: 11, message: '长度在 1到 11 个字符', trigger: 'blur'}
        ]
      }
    }
  },
  methods:{
    login() {
      this.$refs.my_form.validate((valid) => {
        console.log(valid)
        if (valid) {
          this.$http.post("/user/login",this.loginForm).then(res=>{
            console.log(res)
            if (res.data.code==200){
              //登录成功
              this.$message({
                showClose:true,
                message:'登录成功',
                type:'success'
              });
              this.$refs.my_form.resetFields();
              window.sessionStorage.setItem("token",res.data.data.token)
              localStorage.setItem('username', res.data.data.username);
              this.$router.push("/home")
            }else{
              //登录失败
              this.$message.error( {showClose:true,
                message:'登录失败',
              type:'error'});
              this.$refs.my_form.resetFields();
              window.sessionStorage.clear();
            }
          });
          // 这里可以添加登录逻辑，比如发送请求到后端
        } else {
          alert('登录失败！');
        }
      });
    },
    register() {
      this.$router.push('/register'); // 假设你有一个注册页面的路由
    },
    forget(){
      this.$router.push('/forget');
    }
  }

}
</script>

<template>
  <div class="bodyDiv">
    <div class="login_container">
      <h1 class="title"> 登录</h1>
      <div class="head">以有账号？立即<el-button type="text" @click="register">注册</el-button></div>


      <el-divider></el-divider>
      <el-form :model="loginForm" :rules="loginRules" ref="my_form" class="login_form">
        <el-form-item prop="userAccount" >
          <el-input v-model="loginForm.userAccount" prefix-icon="el-icon-user-solid"></el-input>
        </el-form-item>

        <el-form-item  prop="userPassword">
          <el-input  v-model="loginForm.userPassword" prefix-icon="el-icon-lock"></el-input>
        </el-form-item>
        <el-divider style="border-top: 1px dashed #e8eaec;"></el-divider>


        <el-form-item class="btns">
          <el-button type="success" @click="forget">忘记密码</el-button>
          <el-button type="success" @click="login" style="width: 270px;background-color: #545c64">登录</el-button>

        </el-form-item>

      </el-form>
    </div>
    <img class="img" src="./../assets/house.png">
  </div>

</template>

<style scoped>
/* 基础容器样式 */
.bodyDiv {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
  padding: 2rem;
}

/* 登录容器 */
.login_container {
  background: rgba(255, 255, 255, 0.95);
  border-radius: 20px;
  padding: 2.5rem;
  width: 420px;
  box-shadow: 0 8px 32px rgba(31, 38, 135, 0.15);
  backdrop-filter: blur(10px);
  position: relative;
  z-index: 2;
  transition: transform 0.3s ease;
  margin-left: 750px;
  margin-top: -60px;
}

.login_container:hover {
  transform: translateY(-5px);
}

.title {
  font-size: 2rem;
  color: #2c3e50;
  font-weight: 600;
  text-align: center;
  margin-bottom: 1.5rem;
  letter-spacing: 1px;
}

.head {
  text-align: center;
  color: #7f8c8d;
  margin-bottom: 1.2rem;
}

/* 图片样式优化 */
.img {

  position: absolute;
  right: 43%;
  top: 50%;
  transform: translateY(-50%);
  width: 50%;
  max-width: 720px;
  border-radius: 20px;
  box-shadow: 0 15px 30px rgba(0, 0, 0, 0.12);
  z-index: 1;
}

/* 表单元素优化 */
.el-form {
  --el-color-primary: #3a5fcd;
  --el-border-radius-base: 8px;
}

.el-input__inner {
  padding: 12px 15px !important;
  font-size: 0.95rem;
  transition: all 0.3s ease;
}

.el-input__prefix {
  left: 12px;
}

.el-input:hover .el-input__inner {
  border-color: #3a5fcd80;
}

.el-input.is-focused .el-input__inner {
  border-color: #3a5fcd;
  box-shadow: 0 0 0 2px #3a5fcd40;
}

/* 按钮样式增强 */
.btns {
  margin-top: 1.5rem;
}

.el-button--success {
  background: linear-gradient(45deg, #3a5fcd, #5b82eb);
  border: none;
  padding: 12px 28px;
  border-radius: 8px;
  transition: all 0.3s ease;
}

.el-button--success:hover {
  transform: translateY(-2px);
  box-shadow: 0 5px 15px rgba(58, 95, 205, 0.3);
}

.el-button--text {
  color: #7f8c8d;
  transition: color 0.2s ease;
}

.el-button--text:hover {
  color: #3a5fcd;
}

/* 分割线优化 */
.el-divider {
  margin: 1.5rem 0;
  background-color: #e8eaec;
}

/* 响应式设计 */
@media (max-width: 992px) {
  .bodyDiv {
    flex-direction: column;
    padding: 1rem;
  }

  .img {
    position: static;
    width: 80%;
    margin: 2rem auto;
    transform: none;
  }

  .login_container {
    width: 90%;
    margin: 2rem auto;
  }
}

@media (max-width: 576px) {
  .login_container {
    padding: 1.5rem;
  }

  .title {
    font-size: 1.75rem;
  }

  .el-input__inner {
    font-size: 0.9rem;
  }
}
</style>
