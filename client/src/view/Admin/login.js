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
import config from '../../config';
import styles from './index.css';

const FormItem = Form.Item;
const { login } = config.api.admin;
class Index extends React.Component {
  state = {
    disabled: false,
    inputVal: '点击获取手机验证码',
    count: 60
  };
  handleVal = () => {
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
      this.handleVal();
    }, 1000);
  }
  handleSubmit = (e) => {
    e.preventDefault();
    this.props.form.validateFields((err, value) => {
      if (err) { return false; }
      fetch(login, {
        method: 'POST',
        headers: { 'Content-type': 'application/json' },
        body: JSON.stringify(value)
      }).then((res) => res.json()).then((res) => {
        // 返回用户数据，登录成功
        const { state } = res;
        console.log(res);
        if (state) {
          console.log(1);
          message.success('登录成功');
          browserHistory.push('/admin/students');
        } else {
          // console.log(2)
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
      <div className={styles.wrapper}>
        <div className={styles.loginTitle}>XUPT SEC</div>
        <Form onSubmit={this.handleSubmit} className={styles.loginForm}>
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
          <FormItem>
            {this.props.form.getFieldDecorator('key', {
              rules: [
                { required: true, message: '请输入验证码' }
              ],
              validateTrigger: 'onBlur',
            })(
              <div style={{ display: 'flex' }}>
                <Input type="text" placeholder="验证码" style={{ width: 100, marginRight: '20px' }} />
                <Input
                  type="button"
                  disabled={this.state.disabled}
                  value={this.state.inputVal}
                  onClick={this.handleVal}
                  style={{ width: 120 }}
                />
              </div>
            )}
          </FormItem>
          <Button type="primary" htmlType="submit" className={styles.loginBtn}>
            登录
          </Button>
        </Form>
      </div>
    );
  }
}
Index.propTypes = {};
export default Form.create()(Index);
