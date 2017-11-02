import React from 'react';
import { Route, Router, IndexRoute, browserHistory } from 'react-router';
import { render } from 'react-dom';

// 首页
import Index from './view/Index/index';
import Home from './view/Home/index';
// 加入我们
import Require from './view/JoinUs/require';
import SignUp from './view/JoinUs/signUp';
import Interview from './view/JoinUs/interview';
import Free from './view/JoinUs/free';
import LoginStudents from './view/JoinUs/students';
// 关于我们
import AboutIndex from './view/AboutUs';
import Lab from './view/AboutUs/lab';
import People from './view/AboutUs/people';
import Development from './view/AboutUs/development';
import Security from './view/AboutUs/security';
// 用户管理
import UserLogin from './view/Admin/login';
import Students from './view/Admin/students';
// wiki
import WikiIndex from './view/Wiki/index';
import safeGroup from './view/Wiki/safeGroup';
import softwareGroup from './view/Wiki/softwareGroup';
// 404
import Http404 from './view/Http404';

const App = (
  <Router history={browserHistory} >
    <Route path="/" component={Index} >
      <IndexRoute component={Home} />
      <Route path="/require" component={Require} />
      <Route path="/signUp" component={SignUp} />
      <Route path="/interview" component={Interview} />
      <Route path="/free" component={Free} />
      <Route path="/students" component={LoginStudents} />

      <Route path="/about" component={AboutIndex}>
        <Route path="lab" component={Lab} />
        <Route path="people" component={People} />
        <Route path="development" component={Development} />
        <Route path="security" component={Security} />
      </Route>
    </Route>

    <Route path="/wiki" component={WikiIndex}>
      <IndexRoute component={safeGroup} />
      <Route path="safe" component={safeGroup} />
      <Route path="software" component={softwareGroup} />
    </Route>

    <Route path="login" component={UserLogin} />
    <Route path="admin/students" component={Students} />
    <Route path="*" component={Http404} />
  </Router>
);
render(
  App
, document.getElementById('app'));

