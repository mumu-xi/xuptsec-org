import React, { Component } from 'react';
import cssModules from 'react-css-modules';
import styles from './index.css';

class Index extends Component {
  render() {
    return (
      <div styleName="interviewWrapper">
        <h1 stylesName="interviewContainer" >interview</h1>
      </div>
    );
  }
}

export default cssModules(Index, styles);
