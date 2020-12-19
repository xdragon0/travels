<template>
    <div>
        <div id="wrap">
            <div id="header">
                <div style="float: right;padding-top: 24px"><span v-text="time"/> &emsp; </div>
                <h1>旅游信息管理系统</h1>
            </div>
            <div id="header-bar"></div>
            <div id="content" style="height: 360px">
                <img style="float: right;height: 320px">
                <h2>注册</h2>
                <form>
                    <label>
                        <div class="label-text">账&emsp;号：</div>
                        <input type="text" v-model="user.username" name="username">
                    </label>
                    <label>
                        <div class="label-text">密&emsp;码：</div>
                        <input type="password" v-model="user.password" name="password">
                    </label>
                    <label>
                        <div class="label-text">邮&emsp;箱：</div>
                        <input type="text" v-model="user.email" name="email">
                    </label>
                    <!--前后端分离的架构, 动态访问验证码-->
                    <img :src="src" id="img-vcode" @click="getImage" :key="key">
                    <label>
                        <div class="label-text">验证码：</div>
                        <input type="text" v-model="code" name="vcode" style="width: 100px">
                    </label>
                    <button type="button" @click="saveUserInfo()">提 交</button>&emsp;
                    <!-- <a href="/login">去登录</a> -->
                    <button type="button" @click="tologin">返回登录</button>
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
        name: "Register",
        data() {
            return {
                user: {},
                code: "",
                src: "",
                key: "",
                time: "",
            }
        },
        methods: {
            saveUserInfo() {
                if (!this.user.username) {
                    alert('用户名不能为空！');
                    return;
                }
                if (!this.user.password) {
                    alert('密码不能为空！');
                    return;
                }
                if (!this.user.email) {
                    alert('邮箱不能为空！');
                    return;
                }
                // 发送axios
                const _this = this
                this.axios.post("/user/register?code=" + this.code + "&key=" + this.key, this.user).then((res) => {
                    console.log(res);
                    if (res.data.state) {
                        alert(res.data.msg + ",点击确定跳转到登录页面!!!");
                        _this.$router.push("/login")
                        
                    } else {
                        alert(res.data.msg);
                    }
                });
            },
            getImage() {
                const _this = this;
                this.axios.get("/user/getImage").then((res) => {
                    console.log(res);
                    _this.src = "data:image/png;base64," + res.data.image;
                    _this.key = res.data.key;
                })
            },
            tologin(){
                this.$router.push('/login')
            }
        },
        created() {
            this.getImage(); // 获取验证码
            let now = new Date();
            this.time = `${now.getFullYear()}-${now.getMonth() + 1}-${now.getDate()}`;
        }
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
