<template>
  <div class="app-container">
    讲师列表
    <el-form
      :inline="true"
      class="demo-form-inline"
      style="margin-left: 20px; margin-top: 12px"
    >
      <el-form-item label="名称">
        <el-input
          v-model="teacherQuery.name"
          placeholder="请输入名称"
        ></el-input>
      </el-form-item>
      <el-form-item label="级别">
        <el-select v-model="teacherQuery.level" placeholder="讲师头衔">
          <el-option label="高级讲师" :value="1"></el-option>
          <el-option label="特级讲师" :value="2"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="getList()"
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
      <el-table-column prop="name" label="名称" width="80"> </el-table-column>
      <el-table-column label="头衔" width="80">
        <template slot-scope="scope">
          {{ scope.row.level === 1 ? "普通讲师" : "特级讲师" }}
        </template>
      </el-table-column>
      <el-table-column prop="intro" label="资历" />
      <el-table-column prop="gmtCreate" label="添加时间" width="160" />
      <el-table-column prop="sort" label="排序" width="60" />
      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <router-link :to="'/teacher/edit/' + scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit"
              >修改</el-button
            >
          </router-link>
          <el-button
            type="danger"
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
import teacher from "@/api/edu/teacher";

export default {
  data() {
    return {
      list: [], //查询之后接口返回集合
      page: 1, //当前页
      limit: 5, //每页记录数
      total: 0,
      teacherQuery: {
        //条件封装对象
      },
    };
  },
  created() {
    this.getList(1);
  },
  methods: {
    //获取讲师列表的方法
    getList(page) {
      teacher.getTeacherListPage(page, this.limit, this.teacherQuery)
        .then(response => {
          this.list = response.data.rows;
          this.total = response.data.total;
        }) //请求成功
        .catch((error) => console.log(error)); //请求失败
    },
    resetData() {
      this.teacherQuery = {};
      this.getList();
    },
    //删除讲师的方法
    removeDataById(id) {
      this.$confirm("此操作将永久删除讲师记录, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          teacher.removeTeacher(id);
          this.$message({
            type: "success",
            message: "删除成功!",
          });
          this.list = []
          this.page = 1
          this.getList(1)
          location. reload()

        })
    },
  },
};
</script>
