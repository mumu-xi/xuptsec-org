import React, { Component } from 'react';
import cssModules from 'react-css-modules';
import styles from './index.css';
import config from '../../config';

const { freeQR } = config.api;
class Index extends Component {
  render() {
    return (
      <div styleName="freeWrapper">
        <img src={freeQR} styleName="freeQR" alt="免试题" />
        <p>{console.log('%cTips:Ascii-HEX-base64-隐写术', 'color: #fff; background: #f40; font-size: 16px;')}</p>
      </div>
    );
  }
}
export default cssModules(Index, styles);
