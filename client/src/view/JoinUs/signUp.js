import React, { Component } from 'react';
import { Input, Form, Button } from 'antd';
import styles from './index.css';

const FormItem = Form.Item;
const { TextArea } = Input;

class Index extends Component {
  render() {
    const { getFieldDecorator } = this.props.form;
    return (
      <div>
        <h3 className={styles.title}>纳新报名</h3>
        <Form className={styles.form}>
          <FormItem >
            {getFieldDecorator('StudentName', {
              rules: [{
                required: true, message: '请输入姓名',
              }]
            })(<Input placeholder="请输入姓名" className={styles.input} />)}
          </FormItem>
          <FormItem >
            {getFieldDecorator('studentSex', {
              rules: [{
                required: true, message: '请输入姓别',
              }]
            })(<Input placeholder="请输入姓别" className={styles.input} />)}
          </FormItem>
          <FormItem >
            {getFieldDecorator('StudentClass', {
              rules: [{
                required: true, message: '请输入班级',
              }]
            })(<Input placeholder="请输入班级" className={styles.input} />)}
          </FormItem>
          <FormItem >
            {getFieldDecorator('StudentId', {
              rules: [{
                required: true, message: '请输入学号',
              }]
            })(<Input placeholder="请输入学号" className={styles.input} />)}
          </FormItem>
          <FormItem >
            {getFieldDecorator('phoneNumber', {
              rules: [{
                required: true, message: '请输入电话号码',
              }]
            })(<Input placeholder="联系方式" className={styles.input} />)}
          </FormItem>
          <FormItem >
            {getFieldDecorator('volunteer', {
              rules: [{
                required: true, message: '请选择',
              }]
            })(<Input placeholder="面试方向（安全组/开发组/待定)" className={styles.input} />)}
          </FormItem>
          <TextArea rows={7} placeholder="自我介绍" className={styles.textarea} />
          <Button type="primary" htmlType="submit" className={styles.signUpBtn}>
             报名
          </Button>
        </Form>

      </div>
    );
  }
}
export default Form.create()(Index);
