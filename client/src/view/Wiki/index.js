import React, { Component } from 'react';
import { Layout } from 'antd';
import Footer from '../../components/Footer';
import Header from '../../components/Header';

const { Content } = Layout;

export default class extends Component {
  render() {
    return (
      <div>
        <Header type="wikiHeader" />
        <Layout>
          <Content>
            {this.props.children || <div />}
          </Content>
        </Layout>
        <Footer />
      </div>
    );
  }
}
