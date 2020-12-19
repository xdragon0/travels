<template>
    <div>
        <div id="wrap">
            <div id="header">
                <div style="float: right;padding-top: 24px">{{time}} &emsp;</div>
                <h1>旅游信息管理系统</h1>
            </div>
            <div id="header-bar"></div>
            <div id="content" style="height: 360px">
                <img  style="float: right;height: 320px">
                <h2>添加省份</h2>
                <form action="provincelist.html" method="post">
                    <label>
                        <div class="label-text">省&emsp;份：</div>
                        <input type="text" v-model="province.name">
                    </label>
                    <label>
                        <div class="label-text">标&emsp;签：</div>
                        <input type="text" v-model="province.tags">
                    </label>
                    <button type="button" @click="saveProvinceInfo">提 交</button>&emsp;
                    <a @click="back_main">返回</a>
                </form>
            </div>
            <div id="footer">
                yusael~
            </div>
        </div>
    </div>
</template>

<script>
    export default{
        data() {
            return {
                province: {},
                 time: "",
            }
        },
        methods: {
            saveProvinceInfo() {
                const _this = this
                this.axios.post("/province/save", this.province).then((res) => {
                    if (res.data.state) {
                        alert(res.data.msg + "点击确定跳转到省份列表页面!");
                        _this.$router.push('/main')
                    } else {
                        alert(res.data.msg);
                    }
                });
            },
            back_main(){
                this.$router.push("/main")
            }
        },
        created() {
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

        .label-text {
            width: 30%;
            float: left;
        }
</style>