import request from '@/utils/request'

export default {
  // 根据邮箱发送验证码
  sendCode(email) {
    return request({
      url: `/edumsm/msm/send/${email}`,
      method: 'get'
    })
  },
  registeMember(memberInfo) {
    return request({
      url: `/ucenter/member/register`,
      method: 'post',
      data: memberInfo
    })
  }
}
