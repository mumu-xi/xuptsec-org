import React, { Component } from 'react';
import cssModules from 'react-css-modules';
import styles from './index.css';

class Index extends Component {
  render() {
    const data = [
      '安全组主要技术方向为渗透测试与逆向破解',
      '多次在北理，杭电，西电等高校举办的CTF比赛中名列前茅',
      '在前年的全国大学生电子设计大赛信息安全邀请赛的动态演练赛中率先拿下1000分，获得积分第一',
      '挖掘并撰写测试报告等相关安全测试活动'
    ];
    return (
      <div styleName="wrapper" className={styles.safeBg}>
        <h3 styleName="title" className={styles.fadeInDown}>安全组简介</h3>
        <ul styleName="dataWrapper" className={styles.fadeInDownBefore}>
          {
          data.map((text) => (
            <li key={text}>{text}</li>
            )
          )
        }
        </ul>
      </div>
    );
  }
}
export default cssModules(Index, styles);
