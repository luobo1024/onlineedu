import request from '@/utils/request'

export default{
  getSubjectList() {
    return request({
      url: `/eduservice/edu-subject/getAllSubject`,
      method: 'get'
    })
  }
}
