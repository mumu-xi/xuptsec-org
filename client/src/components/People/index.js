import React, { Component } from 'react';
import styles from './index.css';

export default class extends Component {
  render() {
    const { data } = this.props;
    return (
      <div className={styles.container}>
        {
          data.length ? data.map((person) => {
            const { name, intro, picurl } = person;
            return (
              <div className={styles.member} key={name}>
                <img src={picurl} alt="人物介绍图片" />
                <div className={styles.info}>
                  <h2>{name}</h2>
                  <p>{intro}</p>
                </div>
              </div>
            );
          }) : <div className={styles.nodata}>暂无人物简介</div>
        }
      </div>
    );
  }
}
