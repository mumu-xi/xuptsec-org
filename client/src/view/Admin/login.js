import React from 'react';
import { Link, browserHistory } from 'react-router';
import {
  Form,
  Icon,
  Input,
  Button,
  message
} from 'antd';
import styles from './index.css';


const FormItem = Form.Item;

class Index extends React.Component {

  handleSubmit = (e) => {
    e.preventDefault();
    const { actions, mes, user } = this.props;
    this.props.form.validateFields((err, values) => {
      if (err) { return false; }
      actions.login(values).then(() => {
        const {loginState} = this.props;
        // 返回用户数据，登录成功
        if (loginState) {
          message.success(mes);
          browserHistory.push('/');
        } else {
          message.error(mes);
        }
      })
      .catch(() => {
        message.error('未知错误');
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
