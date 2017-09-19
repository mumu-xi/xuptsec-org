import React, { Component } from 'react';
import cssModules from 'react-css-modules';
import 'whatwg-fetch';
import { Icon } from 'antd';
import styles from './index.css';
import config from '../../config';

const { getFileUrl } = config.api;

class Index extends Component {
  downFile = () => {
    const file = document.createElement('a');
    file.download = '信息安全实验室纳新题';
    file.style.display = 'none';
    file.href = getFileUrl;
    document.body.appendChild(file);
    file.click();
    document.body.removeChild(file);
  }
  render() {
    return (
      <div styleName="interviewWrapper">
        <h3 styleName="title" >实验室面试题</h3>
        <div styleName="downBtn" onClick={this.downFile}>
          <Icon type="cloud-download" style={{ fontSize: 30 }} />
          <span>点击下载到本地</span>
        </div>
      </div>
    );
  }
}

export default cssModules(Index, styles);
