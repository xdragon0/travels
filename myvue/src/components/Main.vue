<template>
   <div>
    <div id="wrap">
        <div id="header">
            <div style="float: right;padding-top: 24px">
                {{time}} &emsp;
                <a @click="back_login" style="color:#fff;float: right">安全退出</a>
            </div>
            <h1>旅游信息管理系统</h1>
        </div>
        <div id="header-bar"></div>
        <div id="content" style="height: 360px">
            <h2>省份列表</h2>
            <input v-model="pname" @keyup.enter="search_ByPname" type="text" placeholder="请输入查询条件">
            <table>
                <thead>
                <tr>
                    <th width="15%">ID</th>
                    <th width="20%">省份</th>
                    <th width="25%">标签</th>
                    <th width="15%">景点个数</th>
                    <th width="25%">操作</th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="province in provinces" :key="province.id">
                    <td v-text="province.id"></td>
                    <td v-text="province.name"></td>
                    <td v-text="province.tags"></td>
                    <td v-text="province.placecounts"></td>
                    <td>
                        <a href="javascript:;" @click="deleteProvince(province.id)">删除省份</a>
                        <a href="javascript:;" @click="showProvince(province.id)">景点列表</a>
                        <!-- <a :href="'../update_province?id=' + province.id">修改省份</a> -->
                        <a  href="javascript:;" @click="updateProvince(province.id)">修改省份</a>
                    </td>
                </tr>
                </tbody>
            </table>
            <a @click="to_addprovince">
                <button type="button">添加省份</button>
            </a>
            <div id="pages">
                <!--上一页, 只有当前所在页数>1才会显示-->
                <a href="javascript:;" class="page" v-if="page > 1" @click="findAll(page - 1)">&lt;上一页</a>
                <!--页面-->
                <a href="javascript:;" class="page" v-for="indexpage in totalPage" @click="findAll(indexpage)"
                   v-text="indexpage"></a>
                <!--下一页, 只有当前所在页数<总页数才会显示-->
                <a href="javascript:;" class="page" v-if="page < totalPage" @click="findAll(page + 1)">下一页&gt;</a>
            </div>
        </div>
        <div id="footer">
            yusael~
        </div>
    </div>
   </div>
</template>

<script>
    export default {
        name: "Main",
        data() {
            return {
                provinces: [],
                page: 1,
                rows: 4,
                totalPage: 0,
                totals: 0,
                time: "",
                pname:'广东',
            }
        },
        methods: {
            search_ByPname(){
               const _this = this
               this.axios.get("/province/findByName?name="+ this.pname ).then(res =>{
                    console.log(res)
                    _this.provinces = res.data;
               }) 
            },
            to_addprovince(){
                this.$router.push("/add_province")
            },
            findAll(indexpage) { // 查询某一页的数据
                if (indexpage) {
                    this.page = indexpage;
                }
                const _this = this; // 保存当前对象, 用于下面的作用域
                this.axios.get("/province/findByPage?page=" + this.page + "&rows=" + this.rows).then((res) => {
                    console.log(res)
                    _this.provinces = res.data.provinces;
                    _this.page = res.data.page;
                    _this.totalPage = res.data.totalPage;
                    _this.totals = res.data.totals;
                });
            },
            //删除省份
            deleteProvince(id) {
                if (confirm("确定要删除省份信息吗?")) {
                    this.axios.get("/province/delete?id=" + id).then((res) => {
                        if (res.data.state) {
                            alert(res.data.msg + "点击确定跳转到省份列表页面!");
                            location.reload(true); // 刷新当前页面
                        } else {
                            alert(res.data.msg);
                        }
                    });
                }
            },
            //修改省份
            updateProvince(id){
                this.$router.push("/update_province?id=" + id);
            },
            //展示省份
            showProvince(id){
                this.$router.push("/placelist?placeId=" + id)
            },
            //返回登录页
            back_login(){
                this.$router.push("/login")
            },
        },
        created() {
            this.findAll();
            let now = new Date();
            this.time = `${now.getFullYear()}-${now.getMonth() + 1}-${now.getDate()}`;
        }
    }
</script>

<style scoped>
  table {
            width: 100%;
            margin-bottom: 15px;
            border-collapse: collapse;
            table-layout: fixed;
        }

        th, td {
            border: 1px solid #CBD6DE;
            padding-left: 10px;
            line-height: 28px;
        }

        th {
            text-align: left;
            background: linear-gradient(#edf2f5, #dce9f2, #edf2f5);
            color: #467aa7;
        }

        tbody tr:nth-child(even) {
            background: #f2f2f2;
        }

        #pages {
            text-align: center;
            padding-top: 8px;
        }

        .page {
            min-width: 50px;
            display: inline-block;
        }
</style>
