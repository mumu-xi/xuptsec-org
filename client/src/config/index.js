export default {
  api: {
    admin: {
      login: 'http://198.181.45.8/api/manager/login',
      getStudents: 'http://198.181.45.8/api/manager/find',
    },
    signup: 'http://198.181.45.8/api/participator/insert',
    getPeople: 'http://198.181.45.8/api/member/find',
    getFileUrl: 'http://198.181.45.8/api/participator/interview',
    freeQR: 'http://198.181.45.8/api/participator/freeQR',
    studentSchedule: 'http://198.181.45.8/api/participator/schedule'
  },
  cookie: {
    secure: false,
    httpOnly: false
  },
};
