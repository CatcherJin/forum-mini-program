// pages/new/new.js
var app = getApp();
var topic_begin = app.globalData.topics;
Page({
  data: {
    title: null,
    content: null,
  },
  feedBackTitle: function (e) {
    this.setData({
      "title": e.detail.value
    })
  },
  feedBackInput: function (e) {
    this.setData({
      "content": e.detail.value
    })
  },
  submit: function () {
    if (!this.data.title) {
      wx.showToast({
        title: '请填写标题',
        icon: 'success',
        duration: 2000
      })
    } else if (!this.data.content) {
      wx.showToast({
        title: '请填写内容',
        icon: 'success',
        duration: 2000
      })
    } else {
      wx.showToast({
        title: '正在提交回复',
        icon: 'loading',
        duration: 2000
      })
      wx.request({
        url: 'http://localhost:8080/wechat/ServletDemo1',
        method: "GET",
        header: {
          'content-type': 'application/x-www-form-urlencoded'
        },
        data: {
          title: this.data.title,
          content: this.data.content,
        },
        success: (res) => {
          wx.hideToast();
          console.log(res.data);         
          app.globalData.topics = res.data.concat(topic_begin);  
          console.log(app.globalData.topics);                        
        }
      })
    }
  },
  onLoad: function (options) {
    // 页面初始化 options为页面跳转所带来的参数
  },
  onReady: function () {
    // 页面渲染完成
  },
  onShow: function () {
    // 页面显示
  },
  onHide: function () {
    // 页面隐藏
  },
  onUnload: function () {
    // 页面关闭
  },
  feedBackContent: function (e) {
  }
})