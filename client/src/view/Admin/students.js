import React, { Component } from 'react';
import { Table } from 'antd';

const columns = [
  { title: '姓名', dataIndex: 'name' },
  { title: '性别', dataIndex: 'sex' },
  { title: '班级', dataIndex: 'class' },
  { title: '学号', dataIndex: 'studentId' },
  { title: '联系电话', dataIndex: 'phoneNum' },
  { title: '面试方向', dataIndex: 'direction' }
];
const dataSource = [{
  key: '1',
  name: '唐梦',
  sex: '女',
  class: '通工1501',
  studentId: '03146031',
  phoneNum: '15667027513',
  direction: '开发'
}, {
  key: '2',
  name: '王琛',
  sex: '男',
  class: '对抗1401',
  studentId: '03146007',
  phoneNum: '45672614',
  direction: '安全'
}];

export default class extends Component {
  render() {
    return (
      <div>
        <Table dataSource={dataSource} columns={columns} />
      </div>
    );
  }
}
