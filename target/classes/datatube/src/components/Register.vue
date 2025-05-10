<script>

export default {
  name: "Register",
  data(){
    return{
      loginForm:{
        userName: "",
        userAccount:"",
        userPassword:"",
        userPhone:""
      },
      loginRules: {
        userName: [
          {required: true, message: '为自己创建一个名字', trigger: 'blur'},
          {min: 1, max: 11, message: '长度在 1 到 11 个字符', trigger: 'blur'}
        ],
        userAccount: [
          {required: true, message: '请输入账户', trigger: 'blur'},
          {min: 1, max: 11, message: '长度在 1 到 11 个字符', trigger: 'blur'}
        ],
        userPassword: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 1, max: 11, message: '长度在 1 到 11 个字符', trigger: 'blur'}
        ],
        userPhone: [
          {required: true, message: '请输入手机号', trigger: 'blur'},
          {min: 11, max: 11, message: '长度为11个数字', trigger: 'blur'}
        ]
      }
    }
  },
  methods:{
    register() {
      this.$refs.my_form.validate((valid) => {
        if (valid) {
          this.$http.post("/user/register",this.loginForm).then(res=>{
            if (res.data.code==200){
              //登录成功
              this.$message({
                showClose:true,
                message:'注册成功',
                type:'success'
              });
              this.$refs.my_form.resetFields();

              this.$router.push("/")
            }else{
              //登录失败
              this.$message.error( {showClose:true,
                message:'注册失败',
                type:'error'});
              this.$refs.my_form.resetFields();
            }
          });
          // 这里可以添加登录逻辑，比如发送请求到后端
        } else {
          alert('注册失败，请仔细检查！');
        }
      });
    },
    loginRef(){
      this.$refs.my_form.resetFields();
    },
    backlogin(){
      this.$router.push("/login")
    }
  }

}
</script>

<template>
  <div class="bodyDiv">
    <div class="login_container">
      <div class="title" >注册</div>
      <div class="head">以有账号？立即<el-button type="text" @click="backlogin()">登录</el-button></div>


      <el-divider></el-divider>
      <el-form :model="loginForm" :rules="loginRules" ref="my_form" class="login_form">
        <el-form-item prop="userName" >
          <el-input v-model="loginForm.userName" prefix-icon="el-icon-user-solid"></el-input>
        </el-form-item>
        <el-form-item prop="userAccount" >
          <el-input v-model="loginForm.userAccount" prefix-icon="el-icon-user-solid"></el-input>
        </el-form-item>

        <el-form-item  prop="userPassword">
          <el-input  v-model="loginForm.userPassword" prefix-icon="el-icon-lock"></el-input>
        </el-form-item>
        <el-divider style="border-top: 1px dashed #e8eaec;"></el-divider>
        <el-form-item  prop="userPhone">
          <el-input  v-model="loginForm.userPhone" prefix-icon="el-icon-phone"></el-input>
        </el-form-item>

        <el-form-item class="btns">


        </el-form-item>
        <el-button type="success" @click="loginRef()">重置</el-button>
        <el-button type="success" @click="register()" style="width: 300px" >注册</el-button>
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
