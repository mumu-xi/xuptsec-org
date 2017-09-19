import React, { Component } from 'react';
import { Layout } from 'antd';

const { Content } = Layout;

export default class extends Component {
  render() {
    return (
      <div>
        <Layout>
          <Content>
            {this.props.children || <div />}
          </Content>
        </Layout>
      </div>
    );
  }
}
