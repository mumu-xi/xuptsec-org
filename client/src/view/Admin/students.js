import React, { Component } from 'react';
import { Table, message } from 'antd';
import { browserHistory } from 'react-router';
import PostalReactMixin from 'postal-react-mixin';
import 'whatwg-fetch';
import appendQuery from 'append-query';
import config from '../../config';

const { getStudents } = config.api.admin;
const columns = [
  { title: '姓名', dataIndex: 'stuName' },
  { title: '性别', dataIndex: 'stuSex' },
  { title: '班级', dataIndex: 'stuClass' },
  { title: '学号', dataIndex: 'stuId' },
  { title: '联系电话', dataIndex: 'stuTel' },
  { title: '面试方向', dataIndex: 'stuGroup' }
];

class Index extends Component {
  mixins: [PostalReactMixin]
  state = {
    data: [],
    pagination: {},
    loading: false,
    isLogined: false
  }

  componentDidMount() {
    subscriptions: {
      changeLoginState = (data) => {
        this.setState({ isLogined: data.isLogined });
      };
    }
    if (this.state.isLogined) {
      this.getData();
    }
  }
  getData = (params = {}) => {
    this.setState({ loading: true });
    const url = appendQuery(getStudents, {
      pageNum: 1, ...params
    }, { removeNull: true });
    fetch(url).then(res => res.json()).then((res) => {
      const { data, total, state } = res;
      if (state === 'true') {
        const pagination = { ...this.state.pagination };
        pagination.total = total;
        message.success(res.message);
        this.setState({
          loading: false,
          data,
          pagination
        });
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
        {
          this.state.isLogined
          ? <Table
            dataSource={this.state.data}
            columns={columns}
            onChange={this.handleChange}
            pagination={this.state.pagination}
            rowKey={record => record.stuName}
          /> : <div>{browserHistory.push('/404')}</div>
        }
      </div>
    );
  }
}
export default Index;
