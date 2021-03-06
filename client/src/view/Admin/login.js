import React from 'react';
import { browserHistory } from 'react-router';
import {
  Form,
  Icon,
  Input,
  Button,
  message
} from 'antd';
import 'whatwg-fetch';
import cssModules from 'react-css-modules';
import PostalReactMixin from 'postal-react-mixin';
import { EventEmitter } from 'events';
import config from '../../config';
import styles from './index.css';

const FormItem = Form.Item;
const { login } = config.api.admin;
class Index extends React.Component {
  mixins: [PostalReactMixin]

  handleSubmit = (e) => {
    e.preventDefault();

    this.props.form.validateFields((err, value) => {
      if (err) { return false; }
      fetch(login, {
        method: 'POST',
        headers: { 'Content-type': 'application/json' },
        body: JSON.stringify(value)
      }).then((res) => res.json()).then((res) => {
        const { state } = res;
        if (state) {
          message.success('登录成功');
          // 更新当前登录状态
          this.publish('changeLoginState', {
            isLogined: true
          });

          browserHistory.push('/admin/students');
        } else {
          message.error('请求失败');
        }
      })
      .catch((error) => {
        message.error(error);
      });
    });
  }

  render() {
    const { getFieldDecorator } = this.props.form;
    return (
      <div>
        <div styleName="loginHeader">
          <Icon type="arrow-left" style={{ fontSize: 22, color: '#FFF', margin: '10px 0 0 20px' }} />
        </div>
        <div styleName="wrapper">
          <div styleName="loginTitle">XUPT SEC</div>
          <Form onSubmit={this.handleSubmit} styleName="loginForm">
            <FormItem>
              {this.props.form.getFieldDecorator('username', {
                rules: [
                  { required: true, message: '输入用户名' },
                  { min: 6, max: 10, message: '请输入6~10位用户名' }
                ],
                validateTrigger: 'onBlur',
              })(
                <Input prefix={<Icon type="user" style={{ fontSize: 13 }} />} placeholder="username" />
              )}
            </FormItem>
            <FormItem>
              {this.props.form.getFieldDecorator('password', {
                rules: [
                  { required: true, message: '输入密码' }
                ],
                validateTrigger: 'onBlur',
              })(
                <Input prefix={<Icon type="lock" style={{ fontSize: 13 }} />} type="password" placeholder="Password" />
              )}
            </FormItem>
            <Button type="primary" htmlType="submit" styleName="loginBtn">
              登录
            </Button>
          </Form>
        </div>
      </div>

    );
  }
}
Index.propTypes = {};
export default Form.create()(cssModules(Index, styles));
