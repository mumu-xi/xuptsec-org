import React, { Component } from 'react';
import { Input, Form, Button, message, Spin } from 'antd';
import { browserHistory } from 'react-router';
import cssmodules from 'react-css-modules';
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
        // credentials: 'include',
        credentials: 'same-origin'
      }).then((res) => res.json()).then((res) => {
        const { state } = res;
        this.setState({ loading: false });
        if (state) {
          message.success(`${value.stuName}同学，你已经报名成功啦，等待近期面试通知~`);
          browserHistory.push('/');
        } else {
          this.setState({ loading: false });
          message.error(res.message);
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
        <h3 styleName="title">纳新报名</h3>
        <div style={{ margin: '0 auto', width: 50, position: 'fixed', top: '50%', left: '50%', zIndex: 999 }}>
          <Spin spinning={this.state.loading} size="large" />
        </div>
        <Form
          styleName="form"
          onSubmit={this.handleSubmit}
        >
          <FormItem >
            {getFieldDecorator('stuName', {
              rules: [{
                required: true, message: '请输入姓名',
              }, {
                pattern: '[\\u4e00-\\u9fa5]+', message: '请勿输入除中文外的非法字符'
              }],
              validateTrigger: 'onBlur',
            })(<Input placeholder="请输入姓名" styleName="input" />)}
          </FormItem>
          <FormItem >
            {getFieldDecorator('stuSex', {
              rules: [{
                required: true, message: '请输入性别',
              }, {
                pattern: '[\\u4e00-\\u9fa5]+', message: '请勿输入除男/女外的非法字符'
              }],
              validateTrigger: 'onBlur',
            })(<Input placeholder="请输入性别" styleName="input" />)}
          </FormItem>
          <FormItem >
            {getFieldDecorator('stuClass', {
              rules: [{
                required: true, message: '请输入班级',
              }, {
                pattern: '[\\d\\u4e00-\\u9fa5]+', message: '你确定这是你的班级嘛'
              }],
              validateTrigger: 'onBlur',
            })(<Input placeholder="请输入班级" styleName="input" />)}
          </FormItem>
          <FormItem >
            {getFieldDecorator('stuNumber', {
              rules: [{
                required: true, message: '请输入学号',
              }, {
                pattern: /^\d{6,8}$/, message: '你确定学号正确嘛~'
              }],
              validateTrigger: 'onBlur',
            })(<Input placeholder="请输入学号" styleName="input" />)}
          </FormItem>
          <FormItem >
            {getFieldDecorator('stuTel', {
              rules: [{
                required: true, message: '请输入电话号码',
              }, {
                pattern: /^\d{10,11}$/, message: '请输入正确的手机号码噢~'
              }]
            })(<Input placeholder="联系电话" styleName="input" />)}
          </FormItem>
          <FormItem >
            {getFieldDecorator('stuGroup', {
              rules: [{
                required: true, message: '请选择',
              }, {
                pattern: '[\\u4e00-\\u9fa5]+', message: '选择填写正确的方向噢'
              }]
            })(<Input placeholder="面试方向（安全组/开发组/待定)" styleName="input" />)}
          </FormItem>
          <FormItem>
            {getFieldDecorator('stuIntro', {
              rules: [{
                required: true, message: '简单介绍一下自己呗~'
              }]
            })(<TextArea rows={7} placeholder="自我介绍" styleName="textarea" />)}
          </FormItem>
          <Button type="primary" htmlType="submit" styleName="signUpBtn" disabled>
             报名
          </Button>
        </Form>
      </div>
    );
  }
}
export default Form.create()(cssmodules(Index, styles));
