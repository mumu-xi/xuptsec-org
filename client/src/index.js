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
// 关于我们
import AboutIndex from './view/AboutUs';
import Lab from './view/AboutUs/lab';
import People from './view/AboutUs/people';
import Development from './view/AboutUs/development';
import Security from './view/AboutUs/security';
// 后台管理
import AdminLogin from './view/Admin/login';
import Students from './view/Admin/students';
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

      <Route path="/about" component={AboutIndex}>
        <Route path="lab" component={Lab} />
        <Route path="people" component={People} />
        <Route path="development" component={Development} />
        <Route path="security" component={Security} />
      </Route>
    </Route>
    <Route path="admin/login" component={AdminLogin} />
    <Route path="admin/students" component={Students} />
    <Route path="*" component={Http404} />
  </Router>
);
render(
  App
, document.getElementById('app'));

