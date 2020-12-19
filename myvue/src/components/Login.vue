<template>
   <div>
        <div id="wrap">
            <div id="header">
                <div style="float: right;padding-top: 24px"><span v-text="time"/>&emsp;</div>
                <h1>旅游信息管理系统</h1>
            </div>
            <div id="header-bar"></div>
            <div id="content" style="height: 360px">
                <img style="float: right;height: 320px">
                <h2>登录</h2>
                <form action="province/provincelist.html" method="post">
                    <label>
                        <div class="label-text">账&emsp;号：</div>
                        <input type="text" v-model="user.username" name="username">
                    </label>
                    <label>
                        <div class="label-text">密&emsp;码：</div>
                        <input type="password" v-model="user.password" name="password">
                    </label>
                    <!--前后端分离的架构, 动态访问验证码-->
                    <img :src="src" :key="key" @click="getImage" id="img-vcode" style="width: 60px;margin-left: 10px;">
                    <label>
                        <div class="label-text">验证码：</div>
                        <input type="text" v-model="code" name="vcode" style="width: 100px">
                    </label>
                    <button type="button" @click="login">提 交</button>&emsp;
                    <!-- <a href="/register">去注册</a> -->
                    <button type="button" @click="toregister">去注册</button>
                </form>
            </div>
            <div id="footer">
                yusael~
            </div>
        </div>
   </div>
</template>

<script>
    export default {
        name: "Login",
        data() {
            return {
                user: {},
                code: "",
                time: "",
                src: "",
                key: "",
            }
        },
        methods: {
            login() { // 登录
                if (!this.user.username) {
                    alert('用户名不能为空！');
                    return;
                }
                if (!this.user.password) {
                    alert('密码不能为空！');
                    return;
                }
                const _this = this
                this.axios.post('/user/login?code=' + this.code + "&key=" + this.key, this.user).then((res) => {
                    console.log(res.data);
                    localStorage.setItem("userid", res.data.userId);
                    if (res.data.state) {
                        alert(res.data.msg + "点击确定进入主页！");
                        _this.$router.push('/main')
                    } else {
                        alert(res.data.msg);

                    }
                });
            },
            getImage() {
                const _this = this;
                this.axios.get("/user/getImage").then((res) => {
                    console.log(res.data);
                    _this.src = "data:image/png;base64," + res.data.image;
                    _this.key = res.data.key;
                })
            },
            toregister(){
                this.$router.push("/register")
            }
        },
        created() {
            this.getImage(); // 获取验证码
            let now = new Date();
            this.time = `${now.getFullYear()}-${now.getMonth() + 1}-${now.getDate()}`;
        },
    }
</script>

<style scoped>
       form {
            width: 270px;
        }

        input {
            width: 70%;
            background: #eee;
        }

        input:focus {
            background: #fff;
        }

        form {
            padding: 0 12px 12px;
        }

        label {
            display: block;
            padding-bottom: 12px;
        }

        #img-vcode {
            width: 56px;
            height: 21px;
            float: right;
            position: relative;
            top: 2px;
            left: -6px
        }

        .label-text {
            width: 30%;
            float: left;
        }
</style>
