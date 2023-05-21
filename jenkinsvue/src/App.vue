<template>
  <div>
    用户名:<input type="text"  placeholder="请输入用户名"/>
    <br><br>
    密码： <input type="password" placeholder="请输入密码"/>
    <br><br>
    <button>登录</button>
  </div>
</template>

<script>

export default {
  name: 'Login',
  data () {
    return {
      loginForm: {
        username: '',
        password: ''
      },
      responseResult: []
    }
  },
  methods: {}
}
</script>

