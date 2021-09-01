import axios from 'axios'
import cookie from 'js-cookie'
// eslint-disable-next-line no-unused-vars
import { MessageBox, Message } from 'element-ui'
// 创建axios实例
const service = axios.create({
  baseURL: 'http://localhost:8333', // api的base_url
  timeout: 20000 // 请求超时时间
})
service.interceptors.request.use(
  config => {
    if (cookie.get('user_token')) {
      config.headers['token'] = cookie.get('user_token')
    }
    return config
  }
)
export default service
