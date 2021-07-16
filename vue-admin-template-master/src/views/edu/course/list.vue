<template>
  <div class="app-container">
    课程列表
    <el-form
      :inline="true"
      class="demo-form-inline"
      style="margin-left: 20px; margin-top: 12px"
    >
      <el-form-item label="课程名称">
        <el-input
          v-model="courseQuery.title"
          placeholder="请输入名称"
        />
      </el-form-item>
      <el-form-item label="状态">
        <el-select v-model="courseQuery.status" placeholder="课程状态">
          <el-option :value="Normal" label="已发布"/>
          <el-option :value="Draft" label="未发布"/>
        </el-select>
      </el-form-item>
      <el-form-item label="添加时间">
        <el-time-picker
          v-model="courseQuery.gmtCreate"
          placeholder="选择开始时间"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00"
          type="datetime"
        />
      </el-form-item>
      <el-form-item>
        <el-time-picker
          v-model="courseQuery.gmtModified"
          placeholder="选择截止时间"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00"
          type="datetime"
        />
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          @click="getList()"
        >查询</el-button
        >
        <el-button type="default" @click="resetData()">清空</el-button>
      </el-form-item>
    </el-form>

    <el-table
      :data="list"
      style="width: 100%"
      border
      fit
      highlight-current-row
    >
      <el-table-column prop="date" label="序号" width="70" align="center">
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column prop="title" label="课程名称" width="80"/>
      <el-table-column label="课程状态" width="80">
        <template slot-scope="scope">
          {{ scope.row.status === "Normal" ? "已发布" : "未发布" }}
        </template>
      </el-table-column>
      <el-table-column prop="lessonNum" label="课时数" />
      <el-table-column prop="gmtCreate" label="添加时间" width="160" />
      <el-table-column prop="viewCount" label="浏览数量" width="60" />
      <el-table-column label="操作" width="150" align="center">
        <template slot-scope="scope">
          <router-link :to="'/course/info/' + scope.row.id">
            <el-button
              type="text"
              size="mini"
              icon="el-icon-edit"
            >编辑课程基本信息</el-button
            >
          </router-link>
          <router-link :to="'/course/chapter/' + scope.row.id">
            <el-button
              type="text"
              size="mini"
              icon="el-icon-edit"
            >编辑课程大纲</el-button
            >
          </router-link>
          <el-button
            type="text"
            size="mini"
            icon="el-icon-delete"
            @click="removeDataById(scope.row.id)"
          >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      :current-page="page"
      :page-size="limit"
      :total="total"
      style="padding: 30px 0; text-align: center"
      layout="total, prev, pager, next, jumper"
      @current-change="getList"
    />
  </div>
</template>
<script>
import course from '@/api/edu/course'

export default {
  data() {
    return {
      list: null, // 查询之后接口返回集合
      page: 1, // 当前页
      limit: 5, // 每页记录数
      total: 0,
      Normal: '已发布',
      Draft: '未发布',
      courseQuery: {
        // 条件封装对象
        status: ''
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    // 获取讲师列表的方法
    getList(page = 1) {
      this.page = page
      course
        .getCourseListPage(this.page, this.limit, this.courseQuery)
        .then((response) => {
          this.list = response.data.rows
          this.total = response.data.total
        }) // 请求成功
        .catch((error) => console.log(error)) // 请求失败
    },
    resetData() {
      this.courseQuery = {}
      this.getList()
    },
    removeDataById(id) {
      this.$confirm('此操作将永久删除课程记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          course.deleteCourse(id)
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
          this.getList()
          this.$router.go(0)
        })
    }
  }
}
</script>
