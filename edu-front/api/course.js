import request from '@/utils/request'

export default {
  // 登录
  getCourseList(current, limit, searchObj) {
    return request({
      url: `/eduservice/coursefront/getFrontCourseList/${current}/${limit}`,
      method: 'post',
      data: searchObj
    })
  },
  getAllSubject() {
    return request({
      url: `/eduservice/edu-subject/getAllSubject`,
      method: 'get'
    })
  },
  getCourseInfo(courseId) {
    return request({
      url: `/eduservice/coursefront/getFrontCourseInfo/${courseId}`,
      method: 'get'
    })
  }
}

