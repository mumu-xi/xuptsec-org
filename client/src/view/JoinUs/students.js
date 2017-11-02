import React, { Component } from 'react';
import { Table, message } from 'antd';
import PostalReactMixin from 'postal-react-mixin';
import 'whatwg-fetch';
import appendQuery from 'append-query';
import config from '../../config';
import styles from './index.css';

const { studentSchedule } = config.api;
const columns = [
  { title: '姓名', dataIndex: 'stuName' },
  { title: '班级', dataIndex: 'stuClass' },
  { title: '面试方向', dataIndex: 'stuGroup' },
  { title: '面试时间', dataIndex: 'schedule' }
];

class Index extends Component {
  mixins: [PostalReactMixin]
  state = {
    data: [],
    pagination: {},
    loading: false,
  }

  componentDidMount() {
    this.getData();
  }
  getData = (params = {}) => {
    this.setState({ loading: true });
    const url = appendQuery(studentSchedule, {
      pageNum: 1, ...params
    }, { removeNull: true });
    fetch(url).then(res => res.json()).then((res) => {
      const { data, total, state } = res;
      console.log(res);
      if (state === 'true') {
        const pagination = { ...this.state.pagination };
        pagination.total = total;
        this.setState({
          loading: false,
          data,
          pagination
        });
      } else {
        message.error(res.message);
      }
    }).catch((error) => {
      message.error(error);
    });
  }

  handleChange = (pagination) => {
    const paper = { ...this.state.pagination };
    paper.current = pagination.current;
    this.setState({
      pagination: paper
    });
    this.getData({
      pageNum: pagination.current,
      pageSize: pagination.pageSize
    });
  }

  render() {
    return (
      <div>
        <Table
          dataSource={this.state.data}
          columns={columns}
          onChange={this.handleChange}
          pagination={this.state.pagination}
          loading={this.state.loading}
          rowKey={record => record.id}
          style={{ height: '100vh' }}
        />
      </div>
    );
  }
}
export default Index;
