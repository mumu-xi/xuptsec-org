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
  componentWillMount() {
    window.scrollTo(0, 0);
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
      this.setState({
        loading: false
      });
      if (res.state === 'true') {
        this.setState({
          data: res.data,
          total: res.total
        });
      } else {
        message.error(res.message);
      }
    }).catch((error) => {
      this.setState({
        loading: false
      });
      message.error(error);
    });
  }
  render() {
    return (
      <div>
        <div style={{ margin: '0 auto', width: 50, position: 'fixed', top: '50%', left: '50%', zIndex: 999 }}>
          <Spin spinning={this.state.loading} delay={500} size="large" />
        </div>
        <h3 styleName="title" className={styles.titleBlack}>人物简介</h3>
        <People data={this.state.data} />
        <Pagination
          total={this.state.total}
          onChange={(page) => { this.getMenberInfo(page); }}
          styleName="peoplePagination"
        />
      </div>
    );
  }
}
export default cssmodules(Index, styles);
