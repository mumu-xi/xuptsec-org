import React from 'react';
import { Route, Router, IndexRoute, browserHistory } from 'react-router';
import { render } from 'react-dom';

import Index from './view/Index/index';
import JoinUs from './view/JoinUs/joinUs';
import Home from './view/Home/index';

const App = (
  <Router history={browserHistory} >
    <Route path="/" component={Index} >
      <IndexRoute component={Home} />
      <Route path="/joinUs" component={JoinUs} />
    </Route>
  </Router>
);
render(
  App
, document.getElementById('app'));

