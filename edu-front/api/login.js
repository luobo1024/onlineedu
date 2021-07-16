import request from '@/utils/request'

export default {
  // 登录
  loginMember(memberInfo) {
    return request({
      url: `/ucenter/member/login`,
      method: 'post',
      data: memberInfo
    })
  },
  getMemberInfo() {
    return request({
      url: `/ucenter/member/getMemberInfo`,
      method: 'get'
    })
  }
}

