import Vue from 'vue'
import Router from 'vue-router'

import Login from "../components/Login.vue";
import Home from "../components/Home.vue";
import Register from "../components/Register.vue";
import User from "../components/User.vue";
import UserData from "../components/UserData.vue";
import Forget from "../components/Forget.vue"
import Setting from '../components/Setting.vue'

Vue.use(Router)

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login
  },
  {
    path: "/forget",
    name:"Forget",
    component: Forget
  },
  {
    path: '/home',
    name: 'Home',
    component: Home,
    children:[{
      path:"/user",
      name:"User",
      component: User,
    },{
      path: '/setting',
      name: 'Setting',
      component: Setting
    },
      {
        path:"/userData",
        name:"UserData",
        component: UserData,
      }
    ]
  },
  {
    path: '/register',
    name: 'Register',
    component: Register
  },
]
//路由守卫
const router = new Router({
  routes
})
router.beforeEach((to, from, next) => {
//如果去往登录页则放行
  if (to.path == '/'||to.path=='/register'||to.path=='/forget'||to.path == '/rootlogin') {
    next();
  } else {
    // 从本地存储里获取token
    let token = window.sessionStorage.getItem('token');
    // 判断token是否为空如果为空则跳转到登录页 如果有则放行
    if (token === null || token === '') {
      next({path:'/'});
    } else {
      next();
    }
  }
});

//发布
export default router
// export default new Router({
//   routes: [
//     {
//       path: '/',
//       name: 'Login',
//       component: Login
//     },
//     {
//       path: '/home',
//       name: 'Home',
//       component: Home
//     },
//     {
//       path: '/register',
//       name: 'Register',
//       component: Register
//     },
//   ]
// })
