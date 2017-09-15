import React, { Component } from 'react';
import cssModules from 'react-css-modules';
import styles from './index.css';

class Index extends Component {
  render() {
    return (
      <div styleName="wrapper">
        <h1 >devo</h1>
      </div>
    );
  }
}
export default cssModules(Index, styles);
