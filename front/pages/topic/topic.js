// pages/topic/topic.js
var app = getApp();
Page({
  onPullDownRefresh:function(){ 
    this.setData({
      "topics": app.globalData.topics
    });
    wx.stopPullDownRefresh();
  },
  data:{
    topics: app.globalData.topics
  },
  onLoad:function(options){
    topics: app.globalData.topics;
    this.loadTopics();
  },
  onReady:function(){
    topics: app.globalData.topics;
    this.loadTopics();
    // 页面渲染完成
  },
  onShow:function(){
   
    this.loadTopics();
    // 页面显示
  },
  onHide:function(){
    // 页面隐藏
  },
  onLaunch: function () {
    topics: app.globalData.topics;
    this.loadTopics();
  },
  onUnload:function(){
    // 页面关闭
  },
  onReachBottom:function(){   
    wx.showToast({
      title: '已经是最底部了',
      icon: 'success',
      duration: 1000
    }) 
  },
  loadTopics:function(){
    topics: app.globalData.topics;
    var that = this;
    var old = this.data.topics;
    // 页面初始化 options为页面跳转所带来的参数
    wx.showToast({
      title: '请下拉刷新',
      icon: 'success',
      duration: 1000
    })    
  }
})