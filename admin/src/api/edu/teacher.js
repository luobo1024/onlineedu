import request from '@/utils/request'

export default{
  // 1.讲师列表(条件查询分页)
  getTeacherListPage(current, limit, teacherQuery) {
    return request({
      url: `/eduservice/edu-teacher/pageTeacherCondition/${current}/${limit}`,
      method: 'post',
      data: teacherQuery// requestbody必须写data
    })
  },
  // 删除讲师
  removeTeacher(id) {
    return request({
      url: `/eduservice/edu-teacher/${id}`,
      method: 'delete'
    })
  },
  addtTeacher(eduTeacher) {
    return request({
      url: `/eduservice/edu-teacher/addTeacher`,
      method: 'post',
      data: eduTeacher
    })
  },
  updateTeacher(eduTeacher) {
    return request({
      url: `/eduservice/edu-teacher/updateTeacher`,
      method: 'post',
      data: eduTeacher
    })
  },
  getTeacher(id) {
    return request({
      url: `/eduservice/edu-teacher/getTeacher/${id}`,
      method: 'get'
    })
  }

}

