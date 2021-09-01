import request from '@/utils/request'
export default{
  // 添加小节
  addVideo(eduChapter) {
    return request({
      url: `/eduservice/edu-video/addVideo`,
      method: 'post',
      data: eduChapter
    })
  },
  // 获取小节信息
  getVideoInfo(videoId) {
    return request({
      url: `/eduservice/edu-video/getVideo/${videoId}`,
      method: 'get'
    })
  },
  // 更新小节信息
  updateVideo(video) {
    return request({
      url: `/eduservice/edu-video/updateVideo`,
      method: 'post',
      data: video
    })
  },
  // 删除小节
  deleteVideo(videoId) {
    return request({
      url: `/eduservice/edu-video/removeVideo/${videoId}`,
      method: 'delete'
    })
  },
  deleteVod(id) {
    return request({
      url: `/eduvod/video/removeVideo/${id}`,
      method: 'delete'
    })
  }

}
