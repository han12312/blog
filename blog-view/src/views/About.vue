<template>
  <div class="blog-detail">

    <div class="mblog">

      <div class="home-title">
        <h2>{{ blog.title }}</h2>
      </div>

      <el-link v-if="ownBlog" icon="el-icon-edit">
        <router-link :to="{name: 'BlogEdit', params: {blogId: blog.id}}">
          编辑
        </router-link>
      </el-link>
      <el-divider></el-divider>
      <div class="markdown-body" v-html="blog.content"></div>


    </div>

    <div>
      <Comment></Comment>

    </div>

  </div>
</template>

<script>
import 'github-markdown-css'
import Comment from "@/components/Comment";

export default {
  name: "About",
  components: {Comment},

  data() {
    return {
      blog: {
        id: "",
        title: "",
        content: ""
      },
      ownBlog: false
    }
  },
  created() {

    const _this = this
    this.$axios.get('/about').then(res => {
      console.log("1-->")
      console.log(JSON.stringify(res.data.data))
      const blog = res.data.data
      _this.blog.id = blog.id
      _this.blog.title = blog.title
      var MardownIt = require("markdown-it")
      var md = new MardownIt()

      var result = md.render(blog.content)
      _this.blog.content = result
      if (_this.$store.getters.getUser) {
        _this.ownBlog = (blog.userId === _this.$store.getters.getUser.id)
      }
    })
  }
}
</script>

<style scoped>
.mblog {
  min-height: 600px;


  padding: 5px 35px 5px 35px;
  text-align: left;
}

.home-title {
  margin-bottom: 40px;
}

.markdown-body {
  text-align: left;
}

.blog-detail {
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  background-color: white;
}

.author-message {
  text-align: left;
  background-color: honeydew;
  padding: 10px 5px 10px 5px;
  font-size: 14px;

}

.el-divider {
  margin: 1rem 0 !important;
}
</style>