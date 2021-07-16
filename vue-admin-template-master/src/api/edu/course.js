import request from '@/utils/request'

export default{
  addCourseInfo(courseInfoVo) {
    return request({
      url: `/eduservice/edu-course/addCourseInfo`,
      method: 'post',
      data: courseInfoVo
    })
  },
  getListTeacher() {
    return request({
      url: `/eduservice/edu-teacher/findAll`,
      method: 'get'
    })
  },
  getCourseInfoId(courseId) {
    return request({
      url: `/eduservice/edu-course/getCourseInfo/${courseId}`,
      method: 'get'
    })
  },
  updateCourseInfo(courseInfoVo) {
    return request({
      url: `/eduservice/edu-course/updateCourseInfo`,
      method: 'post',
      data: courseInfoVo
    })
  },
  getCourseInfo(courseId) {
    return request({
      url: `/eduservice/edu-course/getCoursePublishInfo/${courseId}`,
      method: 'get'
    })
  },
  publishCourse(courseId) {
    return request({
      url: `/eduservice/edu-course/publishCourse/${courseId}`,
      method: 'post'
    })
  },
  getListCourse() {
    return request({
      url: `/eduservice/edu-course`,
      method: 'get'
    })
  },
  getCourseListPage(current, limit, eduCourse) {
    return request({
      url: `/eduservice/edu-course/pageCourseCondition/${current}/${limit}`,
      method: 'post',
      data: eduCourse
    })
  },
  deleteCourse(courseId) {
    return request({
      url: `/eduservice/edu-course/${courseId}`,
      method: 'delete'
    })
  }

}
