import React, { Component } from 'react';
import { Input, Form, Button,message, Spin } from 'antd';
import { browserHistory } from 'react-router';
import 'whatwg-fetch';
import xss from 'xss';

import config from '../../config';
import styles from './index.css';

const { signup } = config.api;
const FormItem = Form.Item;
const { TextArea } = Input;
const options = {
  whiteList: {
    span: ['style'],
    p: ['style'],
    ins: ['style'],
    strong: ['style'],
    em: ['style'],
    del: ['style'],
    code: ['style'],
    sup: ['style'],
    sub: ['style'],
    h1: ['style'],
    h2: ['style'],
    h3: ['style'],
    h4: ['style'],
    h5: ['style'],
    h6: ['style'],
    blockquote: ['style'],
    ul: ['style'],
    li: ['style'],
    ol: ['style'],
    a: ['style', 'href', 'target'],
    iframe: ['width', 'height', 'style', 'src', 'frameborder'],
    img: ['src', 'alt', 'style']
  },
  stripIgnoreTag: true,      // filter out all HTML not in the whilelist
  stripIgnoreTagBody: ['script'] // the script tag is a special case, we need to filter out its content
};
const myxss = new xss.FilterXSS(options);


class Index extends Component {
  state={
    loading: false,
    data: []
  }
  handleSubmit=(e) => {
    e.preventDefault();
    this.props.form.validateFields((err, value) => {
      value.stuIntro = myxss.process(value.stuIntro);
      if (err) { return false; }
      this.setState({ loading: true });
      fetch(signup, {
        mode: 'cors',
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(value),
        credentials: 'same-origin',
      }).then((res) => res.json()).then((res) => {
        const { state } = res;
        this.setState({ loading: false });
        if (state) {
          message.success(`${value.stuName}同学，你已经报名成功啦，等待近期面试通知~`);
          browserHistory.push('/');
        } else {
          message.error('报名失败');
        }
      }).catch((error) => {
        message.error(error);
      });
    });
  }
  render() {
    const { getFieldDecorator } = this.props.form;
    return (
      <div>
        <h3 className={styles.title}>纳新报名</h3>
        <div style={{ margin: '0 auto', width: 50, position: 'fixed', top: '50%', left: '50%', zIndex: 999 }}>
          <Spin spinning={this.state.loading} size="large" />
        </div>
        <Form
          className={styles.form}
          onSubmit={this.handleSubmit}
        >
          <FormItem >
            {getFieldDecorator('stuName', {
              rules: [{
                required: true, message: '请输入姓名',
              }]
            })(<Input placeholder="请输入姓名" className={styles.input} />)}
          </FormItem>
          <FormItem >
            {getFieldDecorator('stuSex', {
              rules: [{
                required: true, message: '请输入姓别',
              }]
            })(<Input placeholder="请输入姓别" className={styles.input} />)}
          </FormItem>
          <FormItem >
            {getFieldDecorator('stuClass', {
              rules: [{
                required: true, message: '请输入班级',
              }]
            })(<Input placeholder="请输入班级" className={styles.input} />)}
          </FormItem>
          <FormItem >
            {getFieldDecorator('stuNumber', {
              rules: [{
                required: true, message: '请输入学号',
              }]
            })(<Input placeholder="请输入学号" className={styles.input} />)}
          </FormItem>
          <FormItem >
            {getFieldDecorator('stuTel', {
              rules: [{
                required: true, message: '请输入电话号码',
              }]
            })(<Input placeholder="联系电话" className={styles.input} />)}
          </FormItem>
          <FormItem >
            {getFieldDecorator('stuGroup', {
              rules: [{
                required: true, message: '请选择',
              }]
            })(<Input placeholder="面试方向（安全组/开发组/待定)" className={styles.input} />)}
          </FormItem>
          <FormItem>
            {getFieldDecorator('stuIntro', {
              rules: [{
                required: true, message: '简单介绍一下自己呗~'
              }]
            })(<TextArea rows={7} placeholder="自我介绍" className={styles.textarea} />)}
          </FormItem>
          <Button type="primary" htmlType="submit" className={styles.signUpBtn}>
             报名
          </Button>
        </Form>

      </div>
    );
  }
}
export default Form.create()(Index);
