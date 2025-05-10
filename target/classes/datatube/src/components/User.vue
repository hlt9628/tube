<script>

export default {
  name: "User",
  data() {
    return {
      tableData: [{}],
      dialogVisible: false,
      imageUrl: '',
      dialogTitle:"新增",
      total:0,
      pageList:{
        page:1,
        size:5,
        userName: "",
        userAccount: "",
        userEmail: ""
      },
//表单数据
      addForm: {
        userAccount: "",
        userPassword: "",
        userPhone: "",
        userEmail: "",
        userImg: "",
        userName:"",
        userId:""
      },

    }
  },

  created() {
    this.listUser();
  },
  methods: {
    listUser() {
// 获取token
      console.log("1")
      const token = window.sessionStorage.getItem("token");
      this.$http.get("/user/selectPage", {
// 将token添加到请求头中
        headers:{
          token: token
        },params:this.pageList
      }).then(res => {
        this.tableData = res.data.data.list
        this.total=res.data.data.total
        this.pageList.userName=""
        this.pageList.userAccount=""
        this.pageList.userEmail=""
      })
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => {
        });
    },
    shows(row= null) {
        if (row){
            this.dialogTitle = "修改"
            this.addForm.userImg = row.userImg;
            this.addForm.userAccount =row.userAccount;
            this.addForm.userName = row.userName;
            this.addForm.userEmail = row.userEmail;
            this.addForm.userPhone =row.userPhone;
                this.addForm.userPassword =row.userPassword
            this.addForm.userId = row.userId;
//图片显示
            this.imageUrl = row.userImg;
        }else {
          this.dialogTitle = "新增";
          this.addForm = {
            userAccount: null,
            userPassword: null,
            userPhone: null,
            userEmail: null,
            userImg: null,
            userName: null,
            userId: null
          };
          this.imageUrl = '';
        }

      this.dialogVisible = true;
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pageList.size=val
      this.listUser();
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.pageList.page = val
      this.listUser();
    },
    handleAvatarSuccess(res, file) {
      //this.imageUrl = URL.createObjectURL(file.raw);
      console.log(res.data)
      this.imageUrl = "http://localhost:8888" + res.data;
      this.addForm.userImg = this.imageUrl;
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return isJPG && isLt2M;
    },
    add() {
        if (this.dialogTitle=="新增"){
      this.$http.post("/user/add", this.addForm).then(res => {
        // console.log(res);
        if (res.data.code == 200) {
//新增成功
          this.imageUrl = null;//图片设置为null
          this.$refs.addRef.resetFields();//表单重置的方法
//关闭窗口
          this.dialogVisible = false;
//表格刷新
          this.listUser();
        }
      }
      )
    }else {
            this.$http.post("/user/update", this.addForm).then(res => {

                    // console.log(res);
                    if (res.data.code == 200) {
//新增成功
                        this.imageUrl = null;//图片设置为null
                        this.$refs.addRef.resetFields();//表单重置的方法
//关闭窗口
                        this.dialogVisible = false;
//表格刷新
                        this.listUser();
                    }
                }
            )
        }
    },
    loginout(){
      this.$router.push("home")
    },
      deleteUser(userId){
      var result = confirm("确定删除吗")
      if(result){
        const username = localStorage.getItem("username")
        console.log(username)
        this.addForm.userName=username
        this.$http.post("user/selectUser", this.addForm).then(res=>{
          localStorage.setItem('user_id', res.data.data);
          this.$http.get("/user/delete",
            {headers:{
                "user_id": localStorage.getItem("user_id")
              },params:{
                "id":userId
              }}).then(res1 => {
              console.log(res1);
              if(res1.data.code=403){
                alert(res1.data.message)
              }else{
                this.listUser();
              }
            }
          )
        })
      }
    }
  }
}
</script>

<template>
 <div>

<el-row>
  <el-col :span="4">
    <el-input placeholder="请输入用户名" v-model="pageList.userName" class="input-with-select">

    </el-input>
  </el-col>
  <el-col :span="4" style="margin-left: 20px">
    <el-input placeholder="请输入账号" v-model="pageList.userAccount" class="input-with-select">

    </el-input>
  </el-col>
    <el-col :span="4" style="margin-left: 20px">
      <el-input placeholder="请输入邮箱" v-model="pageList.userEmail" class="input-with-select">
        <el-button slot="append" icon="el-icon-search" @click="listUser"></el-button>
      </el-input>
    </el-col>
  <el-col :span="4" style="margin-left: 20px">
    <el-button  type="primary" @click="shows()">新增用户</el-button>
  </el-col>
  <el-col :span="4" style="margin-left: 20px">
    <el-button  type="primary" @click="loginout()">退出登录</el-button>
  </el-col>
</el-row>
   <el-table
     :data="tableData"
     style="width: 100%">
       <el-table-column
               prop="userId"
               label="用户Id"
       >
       </el-table-column>
     <el-table-column
       prop="userName"
       label="用户姓名"
      >
     </el-table-column>
     <el-table-column
       prop="userAccount"
       label="用户账户"
      >
     </el-table-column>
     <el-table-column
       prop="userPhone"
       label="用户手机">
     </el-table-column>
     <el-table-column
       prop="userEmail"
       label="用户邮箱">
     </el-table-column>
     <el-table-column
       prop="userImg"
       label="用户照片">
       <template slot-scope="scope"><img :src="scope.row.userImg" style="width:50px" height="50px"/>
       </template>

     </el-table-column>
       <el-table-column
               label="操作">
           <template slot-scope="scope">
               <el-button
               size="mini" @click="shows(scope.row)">编辑</el-button>
               <el-button size="mini " type="danger"
                          @click="deleteUser(scope.row.userId)">删除</el-button>
           </template>
       </el-table-column>
   </el-table>
   <div class="block">
     <el-pagination
       @size-change="handleSizeChange"
       @current-change="handleCurrentChange"
       :current-page="pageList.page"
       :page-sizes="[1,2,3,4,5,6 ]"
       :page-size="pageList.size"
       layout="total, sizes, prev, pager, next, jumper"
       :total="total">
     </el-pagination>
   </div>

   <el-dialog
    :title="dialogTitle"
     :visible.sync="dialogVisible"
     width="30%"
     :before-close="handleClose">
      <el-form :model="addForm" ref="addRef">
        <el-form-item label="头像" style="text-align: center">
          <el-upload
            class="avatar-uploader"
            action="http://localhost:8888/file/upload"
            name="file"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload">
            <img v-if="imageUrl" :src="imageUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item >
        <el-form-item label="姓名">
          <el-input v-model="addForm.userName"></el-input>
        </el-form-item>
        <el-form-item label="账号">
          <el-input v-model="addForm.userAccount"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="addForm.userPassword"></el-input>
        </el-form-item>
        <el-form-item label="手机号">
          <el-input v-model="addForm.userPhone"></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="addForm.userEmail"></el-input>
        </el-form-item>

      </el-form>
     <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="add">确 定</el-button>
  </span>
   </el-dialog>
 </div>


</template>

<style scoped>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
