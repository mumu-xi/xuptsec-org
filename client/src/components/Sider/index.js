import React, { Component } from 'react';
import { Link } from 'react-router';
import { Layout, Menu, Icon, Button, message } from 'antd';
import styles from './index.css';

const { Sider, Content } = Layout;

export default class extends Component {
  render() {
    const menus = [
      { text: '2013级', link: '2013' },
      { text: '2014级', link: '2014' },
      { text: '2015级', link: '2015' },
      { text: '2016级', link: '2016' },
    ];
    return (
      <Layout>
        <Sider className={styles.sider}>
          <Menu mode="inline">
            {
              menus.map((menu) => {
                const { text, link } = menu;
                return (
                  <Menu.Item key={link}>
                    <Link to={`/about/people/${link}`}>
                      <span>{text}</span>
                    </Link>
                  </Menu.Item>
                );
              })
            }
          </Menu>
        </Sider>
        <Layout>
          <Content>
            {this.props.children}
          </Content>
        </Layout>
      </Layout>
    );
  }
}
