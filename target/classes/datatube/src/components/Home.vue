<script>
export default {
  name: "Home",
  data(){
    return{
      search_input:"",
    }
  },
  methods:{
    head_show (){
      alert("nihao1")
    },
    handlecommend(command){
      if (command == ""){
        this.end()
      }
      this.$router.push("/"+command)
    },

    end(){
      window.sessionStorage.removeItem("token");
      localStorage.removeItem("username")
      localStorage.removeItem("user_account")
      localStorage.removeItem("user_id")
      localStorage.removeItem("user_name")
      //返回首界面
      this.$router.push("/");
    }
  }

}
</script>
<template>

  <!--布局-->
  <el-container >
    <el-container>
      <div style="box-shadow: 0 0 2px 1px #999">
        <el-aside width="260px" style="border: 1px solid whitesmoke;">

          <button style="font-size: 21px; margin-left: 50px;margin-top: 17px;border: none;background-color: white" @click="$router.push('/home')">
            数据分析系统
          </button>
          <!--所有菜单-->

            <el-menu
              background-color="white"
              text-color="black"
              active-text-color="green"
              unique-opened
              router
            style="height: 500px;">
              <!--el-submenu一级菜单-->
              <el-submenu index="1">
                <!--菜单属性-->
                <template slot="title">
                  <!--图标-->
                  <i class="el-icon-house"></i>
                  <span>主页</span>
                </template>
                <el-menu-item index="/home">
                  <i class="el-icon-house"></i>
                  <span slot="title">主页</span>
                </el-menu-item>
                <!--二级菜单-->

              </el-submenu>
              <!--el-submenu一级菜单-->
              <el-submenu index="2">
                <!--菜单属性-->
                <template slot="title">
                  <i class="el-icon-s-goods"></i>
                  <span>公厕管理</span>
                </template>
                <!--二级菜单-->
                <el-menu-item index="/goods">
                  <i class="el-icon-goods"></i>
                  <span slot="title">公厕列表</span>
                </el-menu-item>
              </el-submenu>
              <!--el-submenu一级菜单-->
              <el-submenu index="3">
                <!--菜单属性-->
                <template slot="title">
                  <i class="el-icon-s-data"></i>
                  <span>数据分析</span>
                </template>
                <!--二级菜单-->
                <el-menu-item index="/goodsAssay">
                  <i class="el-icon-s-data"></i>
                  <span slot="title">用户分析</span>
                </el-menu-item>
                <el-menu-item index="/goodsAssay">
                  <i class="el-icon-s-data"></i>
                  <span slot="title">公厕分析</span>
                </el-menu-item>
              </el-submenu>
              <el-submenu>
              <template slot="title">
                <i class="el-icon-s-goods"></i>
                <span>用户</span>
              </template>
                <el-menu-item index="/user">
                  <i class="el-icon-s-data"></i>
                  <span slot="title">消息通知</span>
                </el-menu-item>
                <el-menu-item index="/user">
                  <i class="el-icon-s-data"></i>
                  <span slot="title">个人中心</span>
                </el-menu-item>
                <el-menu-item  @click='end()'>
                  <i class="el-icon-s-data"></i>
                  <span slot="title" >退出登录</span>
                </el-menu-item>
              </el-submenu>
            </el-menu>
          </el-aside>
        <div style="height: 212px;background-color: white">
          <el-button icon="el-icon-setting" circle  ></el-button>
        </div>

      </div>
      <div style="height: 65px;width: 100%;background-color: white">
        <div style="border: 1px solid whitesmoke;height: 65px;width: 100%;margin-left: 1px;box-shadow: 0 0 1px 1px #999  ">
          <el-input v-model="search_input" placeholder="请输入内容" prefix-icon="el-icon-search" style="margin-top: 15px;margin-left: 40px;width: 600px"></el-input>
          <el-button icon="el-icon-search" circle></el-button>
          <el-button icon="el-icon-bell" circle style="margin-left: 340px"></el-button>
          <el-button icon="el-icon-setting" circle @click="$router.push('/setting')"></el-button>
          <el-image :src="require('@/assets/head.jpg')" fit="contain" style="height: 40px;border-radius: 20px;left: 10px;top: 15px"></el-image>
            <el-dropdown trigger="click" @command="handlecommend">
              <span class="el-dropdown-link">
                <i class="el-icon-caret-bottom" style="margin-left: 5px"></i>
              </span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item command="setting" icon="el-icon-setting">设置</el-dropdown-item>
                  <el-dropdown-item command="user" icon="el-icon-user">个人中心</el-dropdown-item>
                  <el-dropdown-item command="" icon="el-icon-delete">退出登录</el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
        </div>
        <div style="height: 690px">
          <el-main>
            <router-view></router-view>
          </el-main>
        </div>
      </div>
      <!--数据操作-->
    </el-container>

  </el-container>



</template>



<style scoped>
.message-title{
  font-size: 30px;
  text-align: center;
  margin-top: 10px;
}
.el-container{
  height: 100%;
  background-color: #f5f6fe;
}
.el-header{
  background-color: black;
  display: flex;
  justify-content: space-between;
  padding-left: 0px;
  align-items: center;
  font-size: 20px;
  color: white;
}
.el-aside{
  background-color: white;
}
.el-main{
  background-color: antiquewhite;
  padding: 0px;
}
.img{
  width: 15%;
  height: 15%;
  border-radius:50%;
}

.el-menu{
  border-right: none;
  color: #d9d9d9;
}
.div{
  display: flex;
  align-items: center
}
</style>
