<template>
    <div>
        <div id="wrap">
            <div id="header">
                <div style="float: right;padding-top: 24px">{{time}}&emsp;</div>
                <h1>旅游信息管理系统</h1>
            </div>
            <div id="header-bar"></div>
            <div id="content" style="height: 480px">
                <img  style="float: right;height: 320px">
                <h2>修改景点</h2>
                <form>
                    <label>
                        <div class="label-text">景&emsp;&emsp;点：</div>
                        <input type="text" v-model="place.name">
                    </label>
                    <label>
                        <div class="label-text">印象图片：</div>
                        <div style="text-align: center;padding-left: 36%">
                            <img :src="src" alt="" id="img-show">
                            <input type="file" id="imgfile" ref="myFile" style="display: none" onchange="imgfileChange()">
                        </div>
                    </label>
                    <label>
                        <div class="label-text">旺季时间：</div>
                        <input type="text" v-model="place.hottime">
                    </label>
                    <label>
                        <div class="label-text">旺季门票：</div>
                        <input type="text" v-model="place.hotticket">
                    </label>
                    <label>
                        <div class="label-text">淡季门票：</div>
                        <input type="text" v-model="place.dimticket">
                    </label>
                    <label>
                        <div class="label-text">景点描述：</div>
                        <input type="text" v-model="place.placedes">
                    </label>
                    <label>
                        <div class="label-text">所属省份：</div>
                        <select v-model="place.provinceid">
                            <option v-for="(pro,index) in provinces" :value="pro.id" v-text="pro.name"></option>
                        </select>
                    </label>
                    <button type="button" @click="updatePlace">提 交</button>&emsp;
                    <a :href="'viewspotlist.html?id=' + place.provinceid">返回</a>
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
        data() {
            return {
                id: "",
                place: {},
                provinces: [],
                src: "",
                time: "",
            }
        },
        methods: {
            updatePlace() {
                console.log(this.place);
                let myFile = this.$refs.myFile;
                let files = myFile.files;
                let file = files[0];
                let formData = new FormData();
                formData.append("pic", file);
                formData.append("id", this.place.id);
                formData.append("name", this.place.name);
                formData.append("hottime", this.place.hottime);
                formData.append("hotticket", this.place.hotticket);
                formData.append("dimticket", this.place.dimticket);
                formData.append("placedes", this.place.placedes);
                formData.append("provinceid", this.place.provinceid);
                // axios
                this.axios({
                    method: 'post',
                    url: '/place/update',
                    data: formData,
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    }
                }).then((res) => {
                    console.log(res);
                    if (res.data.state) {
                        alert(res.data.msg + ",成功，点击确定回到景点列表");
                        location.href = "./viewspotlist.html?id=" + this.place.provinceid;
                    } else {
                        alert(res.data.msg + ",失败，点击确定回到景点列表");
                    }
                });
            },
            findOnePlace() {
                const _this = this;
                this.axios.get("/place/findOne?id=" + this.id).then((res) => {
                    _this.place = res.data;
                    _this.src = "data:image/png;base64," + _this.place.picpath;
                    // console.log(_this.place);
                });
            },
            findAllProvinces() {
                const _this = this;
                this.axios.get("/province/findByPage?rows=35").then((res) => {
                    // console.log(res.data.provinces);
                    _this.provinces = res.data.provinces;
                });
            }
        },
        created() {
            this.id = location.href.substr(location.href.indexOf("=") + 1);
            this.findOnePlace();
            this.findAllProvinces();
            // console.log(this.place);

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
            width: 64%;
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
            width: 36%;
            float: left;
        }

        #img-show {
            width: 135px;
            height: 135px;
            display: block;
            margin: 0 auto;
            object-fit: cover;
        }
</style>
