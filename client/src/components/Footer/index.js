import React, { Component } from 'react';
import styles from './index.css';

export default class extends Component {
  render() {
    return (
      <footer className={styles.footer}>
        <span className={styles.copyRight}>copyRight &copy; 2009-2017西邮信息安全实验室 All right reserved</span>
        <div className={styles.qrWrapper}>
          <p>关注我们</p>
          <img className={styles.qr} src="../../../public/qrcode.png" alt="QR" />
        </div>
      </footer>
    );
  }
}
