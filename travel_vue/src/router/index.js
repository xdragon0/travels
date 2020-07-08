import Vue from 'vue'
import VueRouter from 'vue-router'
//导入组件
import Register from  '../components/Register'
import Login from  '../components/Login'
import Main from '../components/Main'
import Add_province from '../components/province/Add_province'
import Update_province from '../components/province/Update_province'
import PlaceList from '../components/place/PlaceList'
import Update_place from '../components/place/Update_place'


//安装路由
Vue.use(VueRouter);

//配置导出路由
export default new VueRouter({
  routes:[
    {
      path: '/register',
      name: 'Register',
      component: Register
    },
    {
      path: '/',
      name: Login,
      component: Login
    },
    {
      path: '/login',
      name: Login,
      component: Login
    },
    {
      path: '/main',
      name: Main,
      component: Main
    },
    {
      path: '/add_province',
      name: Add_province,
      component: Add_province
    },
    {
      path: '/update_province',
      name: Update_province,
      component: Update_province
    },
    {
      path: '/placelist',
      name: PlaceList,
      component: PlaceList
    },
    {
      path: '/update_place',
      name: Update_place,
      component: Update_place
    },
  ]
})
