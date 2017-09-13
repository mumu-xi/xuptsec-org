import React, { Component } from 'react';
import styles from './index.css';

export default class extends Component {
  render() {
    return (
      <div className={styles.freeWrapper}>
        <div className={styles.freeQR} />
        <p>{console.log('%cTips:Ascii-HEX-base64-隐写术', 'color: #fff; background: #f40; font-size: 16px;')}</p>
      </div>
    );
  }
}
