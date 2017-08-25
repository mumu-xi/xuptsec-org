import React, { Component } from 'react';
import { Link } from 'react-router';

import { Menu } from 'antd';
import styles from './index.css';

const SubMenu = Menu.SubMenu;
export default class extends Component {
  render() {
    const menus = [{
      text: 'Home',
      link: ''
    }, {
      text: 'Join us',
      link: '/joinUs',
      children: [{
        text: '纳新要求',
        link: 'joinUs/require'
      }, {
        text: '纳新报名',
        link: 'joinUs/signUp'
      }, {
        text: '面试题',
        link: 'joinUs/interview'
      }, {
        text: '免试题',
        link: 'joinUs/free'
      }]
    }, {
      text: 'About us',
      link: 'aboutUs',
      children: [{
        text: '实验室简介',
        link: 'aboutUs/lab'
      }, {
        text: '成员简介',
        link: 'joinUs/people'
      }, {
        text: '开发组',
        link: 'aboutUs/open'
      }, {
        text: '安全组',
        link: 'aboutUs/safe'
      }]
    }, {
      text: 'blog',
      link: '#' // 放博客链接
    }];
    const { route='' } = this.props;
    console.log('header-index.js', `${route}`);
    return (
      <div className={styles.header}>
        <div className={styles.logo} />
        <Menu
          mode="horizontal"
          theme="dark"
          className={styles.menu}
          selectedKeys={route ? [`.$${route}`] : ['.$']}
          onClick={this.toggleCollapsed}
        >
          {
            menus.map((menu) => {
              const { link, text } = menu;
              const children = menu.children || [];
              return (
                children.length ? <SubMenu title={text} key={link}>{
                   children.map((child) => {
                     const { link, text } = child;
                     return (
                       <Menu.Item key={link}>
                         <Link to={`/${link}`}>{text}</Link>
                       </Menu.Item>
                     );
                   })
                  }
                </SubMenu>
                : <Menu.Item key={link}>
                  <Link to={`/${link}`}>{text}</Link>
                </Menu.Item>
              );
            })
          }
        </Menu>
      </div>
    );
  }
}

