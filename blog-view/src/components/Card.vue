<template>
  <div class="card">
    <!--展示名片头部--头像名称-->
    <div class="card-header">
      <el-avatar :size="50" :src="user.avatar"></el-avatar>
      <p style="margin-top: 0px;">星野</p>
      <el-divider></el-divider>
    </div>
    <!--卡片底部--个人喜好-->
    <div class="card-footer">
      <el-collapse>
        <el-collapse-item name="4" style="padding-left: 20px;padding-right: 20px" title="最喜欢的电视剧">
          <div>Peaky Blinders</div>
        </el-collapse-item>
        <el-collapse-item name="4" style="padding-left: 20px;padding-right: 20px" title="最喜欢的电影">
          <div>这个杀手不太冷</div>
        </el-collapse-item>
        <el-collapse-item name="1" style="padding-left: 20px;padding-right: 20px" title="最喜欢的动漫">
          <div>一人之下</div>
        </el-collapse-item>
        <el-collapse-item name="2" style="padding-left: 20px;padding-right: 20px" title="最喜欢的女孩子">
          <div>她</div>
        </el-collapse-item>
        <el-collapse-item name="3" style="padding-left: 20px;padding-right: 20px" title="最喜欢的游戏">
          <div>lol</div>
        </el-collapse-item>
      </el-collapse>
    </div>
  </div>
</template>

<script>
export default {
  name: "Card",
  data() {
    return {
      hasLogin: false,
      fit: 'fill',
      user: {
        avatar: require('@/assets/tx.jpg')
      },
    }
  },
  methods: {
    //跳转到个人github
    goToGithub() {
      window.location.href = "https://github.com/yubifeng"
    }
  },
  created() {
  }
}
</script>

<style scoped>
.card {
  width: 300px;
  padding: 20px 0px 0px 0px;
  background-color: white;
}
.card-header {
  text-align: center;
}
.card-footer {
  margin-top: 10px;
}
.card:hover {
  -webkit-box-shadow: #ccc 0px 10px 10px;
  -moz-box-shadow: #ccc 0px 10px 10px;
  box-shadow: #ccc 0px 10px 10px;
}
</style>