import React from 'react';
import { Route, Router, IndexRoute, browserHistory } from 'react-router';
import { render } from 'react-dom';

import Index from './view/Index/index';
import Home from './view/Home/index';

import Require from './view/JoinUs/require';
import SignUp from './view/JoinUs/signUp';
import Interview from './view/JoinUs/interview';
import Free from './view/JoinUs/free';

import AboutIndex from './view/AboutUs';
import Lab from './view/AboutUs/lab';

import People from './view/AboutUs/people';
import Sider from './components/Sider';

import Development from './view/AboutUs/development';
import Security from './view/AboutUs/security';

import AdminLogin from './view/Admin/login';
import Students from './view/Admin/students';

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
        <Route path="people" component={Sider} >
          <Route path=":id" component={People} />
        </Route>
        <Route path="development" component={Development} />
        <Route path="security" component={Security} />
      </Route>
    </Route>
    <Route path="admin/login" component={AdminLogin} />
    <Route path="admin/students" component={Students} />
  </Router>
);
render(
  App
, document.getElementById('app'));

