import React, { Component } from 'react';
import 'whatwg-fetch';
import cssmodules from 'react-css-modules';
import { message, Spin, Pagination } from 'antd';
import appendQuery from 'append-query';
import styles from './index.css';
import People from '../../components/People';
import config from '../../config';

const { getPeople } = config.api;

class Index extends Component {
  state = {
    data: [],
    loading: false,
    total: 10,
    page: 1,
  }
  componentDidMount() {
    this.getMenberInfo();
  }
  getMenberInfo = (page) => {
    this.setState({ loading: true });
    const url = appendQuery(getPeople, {
      pageNum: page
    }, { removeNull: true });

    fetch(url, {
      method: 'GET'
    }).then((res) => res.json()).then((res) => {
      // console.log(res);
      if (res.state === 'true') {
        this.setState({
          data: res.data,
          loading: false,
          total: res.total
        });
        console.log(this.state.total);
      } else {
        message.error(res.message);
      }
    }).catch(() => {
      this.setState({
        loading: false
      });
    });
  }
  render() {
    return (
      <Spin spinning={this.state.loading}>
        <h3 styleName="title" className={styles.titleBlack}>人物简介</h3>
        <People data={this.state.data} />
        <Pagination
          total={this.state.total}
          onChange={(page) => { this.getMenberInfo(page); }}
          styleName="peoplePagination"
        />
      </Spin>
    );
  }
}
export default cssmodules(Index, styles);
