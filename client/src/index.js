import React from 'react';
import { Route, Router, IndexRoute, browserHistory } from 'react-router';
import { render } from 'react-dom';

import Index from './view/Index/index';
import Home from './view/Home/index';

import Require from './view/JoinUs/require';
import SignUp from './view/JoinUs/signUp';
import Interview from './view/JoinUs/interview';
import Free from './view/JoinUs/free';

const App = (
  <Router history={browserHistory} >
    <Route path="/" component={Index} >
      <IndexRoute component={Home} />

      <Route path="/require" component={Require} />
      <Route path="/signUp" component={SignUp} />
      <Route path="/interview" component={Interview} />
      <Route path="/free" component={Free} />
    </Route>
  </Router>
);
render(
  App
, document.getElementById('app'));

