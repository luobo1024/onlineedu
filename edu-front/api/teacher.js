import request from '@/utils/request'

export default {
  // 登录
  getTeacherList(current, limit) {
    return request({
      url: `/eduservice/teacherfront/getTeacherFrontList/${current}/${limit}`,
      method: 'get'
    })
  },
  getTeacherInfo(id) {
    return request({
      url: `/eduservice/teacherfront/getTeacherFrontInfo/${id}`,
      method: 'get'
    })
  }
}

