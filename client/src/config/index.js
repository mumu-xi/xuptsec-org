export default {
  api: {
    admin: {
      login: 'http://g4yriq.natappfree.cc/api/manager/login',
      auth: '/api/admin/token'
    },
    signup: 'http://g4yriq.natappfree.cc/api/participator/insert',
    // g4yriq.natappfree.cc/api/participator/find?pageNum=1&&pageSize=10 一次查十条报名人的信息
    people: 'api/participator/find'
  },
  cookie: {
    secure: false,
    httpOnly: false
  },
};
