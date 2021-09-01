import request from '@/utils/request'
export default{
  getChapterVideo(courseId) {
    return request({
      url: `/eduservice/edu-chapter/getChapterVideo/${courseId}`,
      method: 'get'
    })
  },
  addChapterVideo(eduChapter) {
    return request({
      url: `/eduservice/edu-chapter/addChapter`,
      method: 'post',
      data: eduChapter
    })
  },
  getChapterInfo(chapterId) {
    return request({
      url: `/eduservice/edu-chapter/getChapterInfo/${chapterId}`,
      method: 'get'
    })
  },
  updateChapter(eduChapter) {
    return request({
      url: `/eduservice/edu-chapter/updateChapter`,
      method: 'post',
      data: eduChapter
    })
  },
  deleteChapter(chapterId) {
    return request({
      url: `/eduservice/edu-chapter/deleteChapter/${chapterId}`,
      method: 'delete'
    })
  }

}
