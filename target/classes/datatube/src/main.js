// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import  'element-ui/lib/theme-chalk/index.css'
import "./assets/all.css"
import axios from "axios";




// 创建 Axios 实例
const http = axios.create({
  baseURL: 'http://localhost:8888',// 设置基础 URL
  timeout: 5000, // 设置超时时间
});

// 将 Axios 实例挂载到 Vue 实例上
Vue.prototype.$http = http;

axios.interceptors.request.use(function (config) {
  // 在发送请求之前做些什么
  // 判断是否存在token,如果存在将每个页面header添加token
  if (window.sessionStorage.getItem("token")) {
    config.headers.token = window.sessionStorage.getItem("token");
  }
  return config
}, function (error) {
  router.push('/')
  return Promise.reject(error)
})

Vue.use(ElementUI)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
