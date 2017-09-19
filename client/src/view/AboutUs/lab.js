import React, { Component } from 'react';
import cssModules from 'react-css-modules';
import styles from './index.css';

class Index extends Component {
  render() {
    const data = [
      '西邮信息安全实验室位于信息安全与信息对抗实验教学中心，是以信息安全为主题的多元化学生实验室.',
      '下设安全小组CWST以及开发小组AWD',
      '安全小组由09级王依民与10级卢兴民创立，目前开发组由15级梁广鹏负责，安全组由16级耿明旭负责',
      '各小组学习风气自由浓厚，每周会定时由大三，大四学长学姐带来技术讲座，给大家拓展知识层'
    ];
    return (
      <div styleName="wrapper" className={styles.labBg}>
        <h3 styleName="title" className={styles.fadeInDown}>实验室简介</h3>
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
