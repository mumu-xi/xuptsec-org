import React, { Component } from 'react';
import cssModules from 'react-css-modules';
import styles from './index.css';

 class Index extends Component {
  render() {
    return (
      <div styleName="wrapper">
        <div styleName="bg" />
        {console.log('琛琛哥说你再进这个页面就给他打电话')}
      </div>
    );
  }
}
export default cssModules(Index, styles);
