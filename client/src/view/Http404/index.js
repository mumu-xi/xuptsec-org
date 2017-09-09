import React, { Component } from 'react';
import styles from './index.css';

export default class extends Component {
  render() {
    return (
      <div className={styles.wrapper}>
        <div className={styles.bg} />
        {console.log('琛琛哥说你再进这个页面就给他打电话')}
      </div>
    );
  }
}
