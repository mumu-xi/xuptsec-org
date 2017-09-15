import React, { Component } from 'react';
import CSSModules from 'react-css-modules';
import styles from './index.css';

class Index extends Component {
  render() {
    return (
      <div styleName="wrapper">
        <h1 >lab</h1>
      </div>
    );
  }
}
export default CSSModules(Index, styles);
