<template>
  <div class="app-container">
    <el-form label-width="120px" :rules="rules">
      <el-form-item label="讲师名称" prop="teacherName">
        <el-input v-model="teacher.name" />
      </el-form-item>
      <el-form-item label="讲师排序">
        <el-input-number
          v-model="teacher.sort"
          controls-position="right"
        />
      </el-form-item>
      <el-form-item label="讲师头衔" prop="teacherLevel">
        <el-select v-model="teacher.level" placeholder="选择讲师头衔">
          <!--
数据类型一定要和取出的json中的一致，否则没法回填
因此，这里value使用动态绑定的值，保证其数据类型是number
-->
          <el-option :value="1" label="高级讲师" />
          <el-option :value="2" label="首席讲师" />
        </el-select>
      </el-form-item>
      <el-form-item label="讲师资历" prop="teacherCareer">
        <el-input v-model="teacher.career" />
      </el-form-item>
      <el-form-item label="讲师简介">
        <el-input v-model="teacher.intro" :rows="10" type="textarea" />
      </el-form-item>
      <!-- 讲师头像：TODO -->
      <!-- 讲师头像：TODO -->
      <!-- 讲师头像 -->
      <el-form-item label="讲师头像">
        <!-- 头衔缩略图 -->
        <pan-thumb :image="teacher.avatar"  />
        <!-- 文件上传按钮 -->
        <el-button
          type="primary"
          icon="el-icon-upload"
          @click="imagecropperShow = true"
          >更换头像
        </el-button>
        <!--
v-show：是否显示上传组件
:key：类似于id，如果一个页面多个图片上传控件，可以做区分
:url：后台上传的url地址
@close：关闭上传组件
@crop-upload-success：上传成功后的回调 -->
        <image-cropper
          v-show="imagecropperShow"
          :width="300"
          :height="300"
          :key="imagecropperKey"
          :url="BASE_API + '/eduoss/fileoss'"
          field="file"
          @close="close"
          @crop-upload-success="cropSuccess"
        />
      </el-form-item>
      <el-form-item>
        <el-button
          :disabled="saveBtnDisabled"
          type="primary"
          @click="saveOrUpdate()"
          >保存</el-button
        >
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import teacher from "@/api/edu/teacher";
import ImageCropper from "@/components/ImageCropper";
import PanThumb from "@/components/PanThumb"
export default {
  components: { ImageCropper, PanThumb},
  data() {
    var checkName = (rule, value, callback) => {
      if (this.teacher.name == "") {
        callback(new Error("讲师名称不能为空"));
      } else {
        callback();
      }
    };
    var checkCareer = (rule, value, callback) => {
      if (this.teacher.career == "") {
        callback(new Error("讲师资历不能为空"));
      } else {
        callback();
      }
    };
    return {
      teacher: {
        name: "",
        sort: 0,
        level: 1,
        career: "",
        intro: "",
        avatar: "https://onlineedu1010.oss-cn-beijing.aliyuncs.com/2021/06/24/55c1a1c9d6734f679582793bad73c92bQQ%E5%9B%BE%E7%89%8720210624230453.jpg",
      },
      BASE_API: process.env.VUE_APP_BASE_API,
      imagecropperKey: 0,
      imagecropperShow: false,
      saveBtnDisabled: false,
      rules: {
        teacherName: [{ validator: checkName, trigger: "blur" }],
        teacherCareer: [{ validator: checkCareer, trigger: "blur" }],
      },
    };
  },
  created() {
    this.init();
  },
  watch: {
    //路由监听
    $route(to, from) {
      //路由变化方式
      this.init();
    },
  },
  methods: {
    close(){
      this.imagecropperShow = false;
      this.imagecropperKey = this.imagecropperKey + 1;
    },
    cropSuccess(data){
      this.teacher.avatar = data.url;
      this.imagecropperShow = false;
      this.imagecropperKey = this.imagecropperKey + 1;
    },
    init() {
      if (this.$route.params && this.$route.params.id) {
        const id = this.$route.params.id;//获取id
        this.getInfo(id);
      } else {
        this.teacher = {};
      }
    },
    saveOrUpdate() {
      if (this.$route.params && this.$route.params.id) {
        this.updateTeacher();
      } else {
        this.saveTeacher();
      }
    },
    getInfo(id) {
      teacher.getTeacher(id).then((response) => {
        this.teacher = response.data.teacher;
      });
    },
    updateTeacher() {
      if (this.teacher.name == "" || this.teacher.career == "") {
        return false;
      }
      this.$confirm("是否更新讲师数据?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        teacher.updateTeacher(this.teacher);
        this.$message({
          type: "success",
          message: "修改成功!",
        });
        this.teacher = {};
        this.$router.push({ path: "/teacher/table" }); //路由跳转
      });
    },
    saveTeacher() {
      if (this.teacher.name == "" || this.teacher.career == "") {
        return false;
      }
      this.$confirm("是否添加讲师数据?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        teacher.addtTeacher(this.teacher);
        this.$message({
          type: "success",
          message: "保存成功!",
        });
        this.teacher = {};
        this.$router.push({ path: "/teacher/table" }); //路由跳转
      });
    },
  },
};
</script>
