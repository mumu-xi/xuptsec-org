import React, { Component } from 'react';
import 'whatwg-fetch';
import { message, Spin } from 'antd';
import styles from './index.css';
import People from '../../components/People';
import config from '../../config';

const { getPeople } = config.api;

export default class extends Component {
  state = {
    data: [],
    loading: false
  }
  componentDidMount() {
    this.getMenberInfo();
  }
  getMenberInfo = () => {
    this.setState({ loading: true });
    fetch(getPeople, {
      method: 'GET'
    }).then((res) => res.json()).then((res) => {
      if (res.state === 'true') {
        this.setState({
          data: res.data,
          loading: false
        });
      } else {
        message.error(res.message);
      }
    });
  }
  render() {
    return (
      <Spin spinning={this.state.loading}>
        <h3 className={styles.title}>人物简介</h3>
        <People data={this.state.data} />
      </Spin>
    );
  }
}
