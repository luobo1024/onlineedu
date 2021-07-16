import request from '@/utils/request'

export default {
  getIndexdata() {
    return request({
      url: '/eduservice/indexfront/index',
      method: 'get'
    })
  }
}
