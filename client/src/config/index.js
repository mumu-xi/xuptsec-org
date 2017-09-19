export default {
  api: {
    admin: {
      // getVerfic: 'http://mu.s1.natapp.cc/api/manager/verification',
      login: 'http://198.181.45.8/api/manager/login',
      getStudents: 'http://198.181.45.8/api/manager/find',
    },
    // signup: 'http://mu.s1.natapp.cc/api/participator/insert',
    signup: 'http://198.181.45.8/api/participator/insert',
    getPeople: 'http://198.181.45.8/api/member/find',
    getFileUrl: 'http://198.181.45.8/api/participator/interview',
    freeQR: 'http://198.181.45.8/api/participator/freeQR'
  },
  cookie: {
    secure: false,
    httpOnly: false
  },
};
