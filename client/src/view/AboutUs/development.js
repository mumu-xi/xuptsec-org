import React, { Component } from 'react';
import cssModules from 'react-css-modules';
import styles from './index.css';

class Index extends Component {
  render() {
    const data = [
      '开发组主要技术方向为的Windows开发',
      'Android的开发，web前端开发，java后台开发',
      'PHP，JSP，Linux的运维，手机测试',
      '拥有数项发明专利，专利如：主机保护系统，基于安卓的无线网络保护系统小组',
      '成员开发经验丰富,多次获得校，省，国家级奖项，小组同学曾获得蓝桥杯乙组省级一等奖，国赛优秀奖的好成绩'
    ];
    return (
      <div styleName="wrapper" className={styles.devolpBg}>
        <h3 styleName="title" className={styles.fadeInDown}>开发组简介</h3>
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
