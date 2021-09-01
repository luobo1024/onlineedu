import request from '@/utils/request'

export function getAllArticle() {
  return request({
    url: '/article/edu-article/getAllArticle',
    method: 'get'
  })
}
export function getPageArticle(searchCondition,current,limit) {
  return request({
    url: `/article/edu-article/getPageArticle/${current}/${limit}`,
    method: 'post',
    data: searchCondition
  })
}
export function addArticle(articleForm) {
  return request({
    url: `/article/edu-article/getArticleForm`,
    method: 'post',
    data: articleForm
  })
}
export function getArticleById(id) {
  return request({
    url: `/article/edu-article/getEditArticle/${id}`,
    method: 'get',
  })
}
export function updateArticleById(id, articleForm) {
  return request({
    url: `/article/edu-article/updateArticle/${id}`,
    method: 'post',
    data: articleForm
  })
}
export function deleteArticleById(id) {
  return request({
    url: `/article/edu-article/deleteArticle/${id}`,
    method: 'delete',
  })
}


