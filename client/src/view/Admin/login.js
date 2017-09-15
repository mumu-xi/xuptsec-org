import React from 'react';
import { browserHistory } from 'react-router';
import {
  Form,
  Icon,
  Input,
  Button,
  message,
} from 'antd';
import 'whatwg-fetch';
import cssModules from 'react-css-modules';
import config from '../../config';
import styles from './index.css';

const FormItem = Form.Item;
const { login, getVerfic } = config.api.admin;
class Index extends React.Component {
  state = {
    disabled: false,
    inputVal: '点击获取手机验证码',
    count: 60,
  };

  setTime = () => {
    if (this.state.count === 0) {
      this.setState({
        count: 60,
        inputVal: '点击获取手机验证码',
        disabled: false
      });
      return;
    }
    this.setState({
      count: this.state.count - 1,
      inputVal: `重新发送(${this.state.count})s`,
      disabled: true
    });
    setTimeout(() => {
      this.setTime();
    }, 1000);
  }

  getVerification = (e) => {
    e.preventDefault();
    this.setTime();
    // console.log(1);
    console.log(this.state.username);
    // debugger;
    this.props.form.validateFields((err, value) => {
      if (err) { return false; }
      const formData = new FormData();
      formData.append('username', value.username);
      console.log(formData);

      fetch(getVerfic, {
        method: 'POST',
        mode: 'cors',
        body: formData
      }).then((res) => res.json()).then((res) => {
        // 返回用户数据，登录成功
        const { state } = res;
        console.log(res);
        if (state === 'true') {
          // 成功
          console.log(1);
        } else {
          // 失败
          message.error(res.message);
        }
      })
      .catch((error) => {
        message.error(error);
      });
    });
  }
  handleSubmit = (e) => {
    e.preventDefault();
    this.props.form.validateFields((err, value) => {
      if (err) { return false; }
      console.log(value);
      fetch(login, {
        method: 'POST',
        headers: { 'Content-type': 'application/json' },
        body: JSON.stringify(value)
      }).then((res) => res.json()).then((res) => {
        // 返回用户数据，登录成功
        const { state } = res;
        console.log(res);
        if (state === 'true') {
          // console.log(1);
          message.success('登录成功');
          browserHistory.push('/admin/students');
        } else {
          // console.log(2)
          message.error(res.message);
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
      <div styleName="wrapper">
        <div styleName="loginTitle">XUPT SEC</div>
        <Form onSubmit={this.handleSubmit} styleName="loginForm">
          <FormItem>
            {this.props.form.getFieldDecorator('username', {
              rules: [
                { required: true, message: '输入用户名' },
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
          <div style={{ display: 'flex' }}>
            <FormItem>
              {this.props.form.getFieldDecorator('verification', {

                validateTrigger: 'onBlur',
              })(<Input type="text" placeholder="验证码" style={{ width: 100, marginRight: '20px' }} />)}
            </FormItem>
            <Input
              type="button"
              disabled={this.state.disabled}
              value={this.state.inputVal}
              onClick={this.getVerification}
            />
          </div>

          <Button type="primary" htmlType="submit" styleName="loginBtn">
            登录
          </Button>
        </Form>
      </div>
    );
  }
}
Index.propTypes = {};
export default Form.create()(cssModules(Index, styles));

