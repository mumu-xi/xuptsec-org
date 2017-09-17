export default {
  api: {
    admin: {
      getVerfic: 'http://mu.s1.natapp.cc/api/manager/verification',
      login: 'http://mu.s1.natapp.cc/api/manager/login',
      auth: '/api/admin/token'
    },
    signup: 'http://mu.s1.natapp.cc /api/participator /insert',
    // g4yriq.natappfree.cc/api/participator/find?pageNum=1&&pageSize=10 一次查十条报名人的信息
    getStudents: 'http://mu.s1.natapp.cc/api/manager/find',
    getPeople: 'http://mu.s1.natapp.cc/api/member/find'
  },
  cookie: {
    secure: false,
    httpOnly: false
  },
};
