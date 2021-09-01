<template>
  <div class="app-container">

<!--条件搜索-->
    <el-form :inline="true" :model="searchCondition" class="demo-form-inline">
      <el-form-item label="标题">
        <el-input v-model="searchCondition.title" placeholder="标题"></el-input>
      </el-form-item>
      <el-form-item label="状态">
        <el-select v-model="searchCondition.status" placeholder="编辑中/已发布">
          <el-option label="编辑中" value="0"></el-option>
          <el-option label="已发布" value="1"></el-option>
          <el-option label="无" value=""></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSearch">查询</el-button>
      </el-form-item>
    </el-form>


    <el-table
      :data="articles"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
    >
      <el-table-column align="center"  prop="id" label="ID" width="95">
        <template slot-scope="scope">
          {{ (pages.current - 1) * pages.size + scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column align="center" prop="title" label="标题" width="150">
      </el-table-column>
      <el-table-column label="封面" prop="cover" width="120" align="center">
        <template slot-scope="scope" >
          <div class="demo-image">
            <div class="block" v-for="fit in fits" :key="fit">
              <span class="demonstration"></span>
              <el-image
                style="width: 100px; height: 100px"
                :src="scope.row.cover"
                :fit="fit"></el-image>
            </div>
          </div>
        </template>

      </el-table-column>
      <el-table-column label="浏览数" prop="view" width="120" align="center">
      </el-table-column>
      <el-table-column label="评论数" prop="comment" width="120" align="center">
      </el-table-column>
      <el-table-column label="排序" prop="sort" width="120" align="center">
      </el-table-column>
      <el-table-column label="状态" prop="status" width="120" align="center">
        <template slot-scope="scope" >
          <el-tag type="info" v-if="scope.row.status == 0">编辑中</el-tag>
          <el-tag type="success" v-if="scope.row.status == 1">已发布</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="gmtCreate" label="创建时间" width="200">
        <template slot-scope="scope">
          <i class="el-icon-time" />
          <span>{{scope.row.gmtCreate}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="gmtModified" label="修改时间" width="200">
        <template slot-scope="scope">
          <i class="el-icon-time" />
          <span>{{scope.row.gmtModified}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="选项" width="228">
        <template slot-scope="scope" >
          <router-link   :to="'/article/edit/' + scope.row.id">
            <el-link type="primary" size="mini" icon="el-icon-edit"
            >编辑文章</el-link
            >
          </router-link>
          <br>
          <el-link type="danger" icon="el-icon-delete" @click="removeArticleById(scope.row.id)">删除文章</el-link>
          <br>
          <el-link type="success" icon="el-icon-view">查看文章</el-link>
        </template>
      </el-table-column>
    </el-table>

<!--分页-->
    <el-pagination
      :current-page="pages.current"
      :page-size="pages.size"
      :total="pages.total"
      style="padding: 30px 0; text-align: center"
      layout="total, prev, pager, next, jumper"
      @current-change="getAllArticle"
    />
  </div>
</template>

<script>
import * as articleApi from '@/api/article/edit'
export default {
  data() {
    return {
      fits: ['scale-down'],
      url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',//封面地址
      articles:[],//文章结果集
      searchCondition: {
        title: '',
        status: ''
      },
      pages: {
        current:null,
        hasNext:null,
        hasPrevious: null,
        size: null,
        total: null,
      }
    }
  },
  created() {
    this.getAllArticle(1)
  },
  methods: {
    getAllArticle(page) {
      articleApi.getPageArticle(this.searchCondition,page,5)
        .then(response => {
          this.articles = response.data.records
          this.pages = response.data
        })
    },

    onSearch(){
      articleApi.getPageArticle(this.searchCondition,1,5)
        .then(response => {
          this.articles = response.data.records
          this.pages =response.data

        })
    },
    removeArticleById(id){

      this.$confirm("此操作将永久删除讲师记录, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          articleApi.deleteArticleById(id)
            .then(response => {
              this.$message({
                type: "success",
                message: "删除成功!",
              });
            })
          this.getAllArticle(1)
          location. reload()
        })
    },

  }
}
</script>
