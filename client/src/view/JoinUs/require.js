import React, { Component } from 'react';
import styles from './index.css';

export default class extends Component {
  render() {
    const requires = [
      { step: 1, title: '纳新要求', text: '这里写纳新要求' },
      { step: 2, title: '宣讲会信息', text: '这里写宣讲会信息' },
      { step: 3, title: '面试安排', text: '这里是面试安排信息' },
    ];
    return (
      <div className={styles.requireWrapper}>{
        requires.map((require) => {
          const { step, title, text } = require;
          return (
            <div className={styles.step} key={step}>
              <span className={styles.stepIndex}>
                <span>{step}</span>
                <div className={styles.dotted} />
              </span>
              <div className={styles.description} >
                <h4 className={styles.requireTitle}>{title}</h4>
                <p>{text}</p>
              </div>
            </div>
          );
        })
      }</div>
    );
  }
}
