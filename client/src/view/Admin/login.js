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
import config from '../../config';
import styles from './index.css';

const FormItem = Form.Item;
const { login } = config.api.admin;
class Index extends React.Component {

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
          console.log(2)
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
