<template>
  <div class="app-container">
    <el-form :model="articleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="标题" prop="title">
        <el-input v-model="articleForm.title"></el-input>
      </el-form-item>
      <el-form-item label="内容" prop="content" >
          <mavon-editor :toolbars="markdownOption" v-model="articleForm.content"/>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="articleForm.status" placeholder="请选择状态">
          <el-option label="编辑中" :value="0"></el-option>
          <el-option label="发布" :value="1"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="排序" prop="sort">
        <el-input-number v-model="articleForm.sort" :min="1" :max="10" label="描述文字" @change="sortChange"></el-input-number>
      </el-form-item>
      <el-form-item label="封面">
        <div class="demo-image__preview">
          <el-image
            style="width: 300px; height: 220px"
            :src="url"
            :preview-src-list="srcList">
          </el-image>
        </div>
        <el-upload type="file" :action="BASE_API+'/article/edu-article/saveImg'" :on-success="onSuccess">
          <el-button size="small" type="primary" >点击上传</el-button>
        </el-upload>

      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">发布</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { mavonEditor} from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import * as articleApi from '@/api/article/edit'
export default {
  name: "Markdown",
  components: {
    mavonEditor
  },
  data() {
    return {
      value:0,
      url: '',
      srcList: [
        'https://fuss10.elemecdn.com/8/27/f01c15bb73e1ef3793e64e6b7bbccjpeg.jpeg',
      ],
      markdownOption:{
        bold: true, // 粗体
        italic: true, // 斜体
        header: true, // 标题
        underline: true, // 下划线
        strikethrough: true, // 中划线
        mark: true, // 标记
        superscript: true, // 上角标
        subscript: true, // 下角标
        quote: true, // 引用
        ol: true, // 有序列表
        ul: true, // 无序列表
        link: true, // 链接
        imagelink: true, // 图片链接
        code: true, // code
        table: true, // 表格
        fullscreen: true, // 全屏编辑
        readmodel: true, // 沉浸式阅读
        htmlcode: true, // 展示html源码
        help: true, // 帮助
        /* 1.3.5 */
        undo: true, // 上一步
        redo: true, // 下一步
        trash: true, // 清空
        save: true, // 保存（触发events中的save事件）
        /* 1.4.2 */
        navigation: true, // 导航目录
        /* 2.1.8 */
        alignleft: true, // 左对齐
        aligncenter: true, // 居中
        alignright: true, // 右对齐
        /* 2.2.1 */
        subfield: true, // 单双栏模式
        preview: true, // 预览
      },
      articleForm: {
        title:'',
        content: '',
        status: '',
        img: '',
        sort: 1

      },
      rules: {
        title: [
          { required: true, message: '请输入标题', trigger: 'blur' },
          { min: 1, max: 25, message: '长度在 1 到 25 个字符', trigger: 'blur' }
        ],
        status: [
          { required: true, message: '请选择状态', trigger: 'change' }
        ],
      },
      BASE_API:process.env.VUE_APP_BASE_API,
      fileName:'default',
    };
  },
  created() {
    this.init()
  },
  methods: {
    init(){
      if (this.$route.params && this.$route.params.id) {
        const id = this.$route.params.id;//获取id
        articleApi.getArticleById(id)
         .then(response => {
           this.articleForm = response.data.article
           this.url = this.articleForm.cover
           this.srcList[0] = this.url
         })
      } else {
        this.articleForm = {};
      }
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (this.$route.params && this.$route.params.id) {
            articleApi.updateArticleById(this.$route.params.id, this.articleForm)
              .then(response => {
                this.$message({
                  message: '成功修改文章',
                  type: 'success'
                });
                this.$router.push({ path: "/article/list" }); //路由跳转
              }).catch(response => {
              this.$message({
                message: '重复提交或上传图片超过1MB',
                type: 'warning'
              });
            })
          }else{
            articleApi.addArticle(this.articleForm)
              .then(response => {
                this.$message({
                  message: '成功提交文章',
                  type: 'success'
                });
                this.$router.push({ path: "/article/list" }); //路由跳转
              }).catch(response => {
              this.$message({
                message: '重复提交或上传图片超过1MB',
                type: 'warning'
              });
            })
          }

        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    /*/getImg/{imgPath}/{fileName}*/
    //上传文件成功,返回结果集
    onSuccess(file){
      this.url = "data:image/jpeg;base64," + encodeURI(file.data.imgSteam)
          this.articleForm.img = this.url
          this.srcList[0] = this.url
    },
    sortChange(){
      if(this.articleForm.sort == undefined){
        this.articleForm.sort = 0
      }
    }

  }
}
</script>

