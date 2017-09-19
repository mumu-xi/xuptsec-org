import React, { Component } from 'react';
import cssmodules from 'react-css-modules';
import Steps from '../../components/Steps';
import styles from './index.css';


class Index extends Component {
  render() {
    const requires = [
      { step: 1, title: '安全组-纳新要求(WEB安全/移动安全/二进制安全)', text: ['团队合作意识', '主动学习能力', '有一定的语言基础', '对信息安全热爱'] },
      { step: 2, title: '开发组-纳新要求(WEB前端/java后台/C++后台/php开发/安卓开发)', text: ['热爱技术，特别是对新技术保持敏感的触觉', '善于沟通，乐于分享，对擅长的领域有自己的理解', '有C、java、javascript、php、python开发经验优先'] },
      { step: 3,
        title: '面试安排',
        text: [
        { order: '一面', event: '9.26-9.27 晚上7:30 通院229' },
        { order: '一面面试结果 ', event: '将于 9.28 or 9.29在官网公布第一次面试结果和二面试题' },
        { order: '二面', event: '10.8 晚7:00第二次面试' }
        ]
      },
    ];
    return (
      <div styleName="requireWrapper">{
        requires.map((require) => {
          const { step, title, text } = require;
          return (
            <div styleName="step" key={step}>
              <span styleName="stepIndex">
                <span>{step}</span>
                <div styleName="dotted" />
              </span>
              <div styleName="description" >
                <h4 styleName="requireTitle">{title}</h4>
                {
                  step === 3 ? <Steps text={text} /> : <ul>
                    {
                      text.map(t => (
                        <ol key={t} styleName="requireIntro">{t}</ol>
                        ))
                    }
                  </ul>
                }

              </div>
            </div>
          );
        })
      }</div>
    );
  }
}
export default cssmodules(Index, styles);
