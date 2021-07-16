import request from '@/utils/request'

export default {
  createOrders(courseId) {
    return request({
      url: `/order/order/createOrder/${courseId}`,
      method: 'post'
    })
  },
  getOrdersInfo(id) {
    return request({
      url: `/order/order/getOrderInfo/${id}`,
      method: 'get'
    })
  },
  createNative(orderNo) {
    return request({
      url: `/order/pay-log/createNative/${orderNo}`,
      method: 'get'
    })
  },
  queryPayStatus(orderNo) {
    return request({
      url: `/order/pay-log/queryPayStatus/${orderNo}`,
      method: 'get'
    })
  }
}
