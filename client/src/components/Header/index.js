import React, { Component } from 'react';
import { Link } from 'react-router';

import { Menu, Button, Icon } from 'antd';
import styles from './index.css';

const SubMenu = Menu.SubMenu;
export default class extends Component {
  state={
    collapsed: true,
    menuStyle: {
      left: -300
    }
  }
  toggleCollapsed = () => {
    if (this.state.collapsed) {
      this.setState({
        collapsed: false,
        menuStyle: {
          left: -20
        }
      });
    } else {
      this.setState({
        collapsed: true,
        menuStyle: {
          left: -500
        }
      });
    }
  };
  render() {
    const menus = [
      { text: 'Home', link: '' },
      { text: 'Join us',
        link: '/joinUs',
        children: [
        { text: '纳新要求', link: 'require' },
        { text: '纳新报名', link: 'signUp' },
        { text: '面试题', link: 'interview' },
        { text: '免试题', link: 'free' }]
      },
      { text: 'About us',
        link: 'aboutUs',
        children: [
        { text: '实验室简介', link: 'aboutUs/lab' },
        { text: '成员简介', link: 'joinUs/people' },
        { text: '开发组', link: 'aboutUs/open' },
        { text: '安全组', link: 'aboutUs/safe' }]
      },
      { text: 'blog', link: '#' }// 放博客链接
    ];

    const { route = '' } = this.props;
    return (
      <div className={styles.header}>
        <Link to="/"><div className={styles.logo} /></Link>
        <Menu
          mode="horizontal"
          theme="dark"
          selectedKeys={route ? [`.$${route}`] : ['.$']}
          className={styles.pcMenu}
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
        <div className={styles.responsiveContaner}>
          <Button onClick={this.toggleCollapsed} style={{ marginBottom: 16 }} >
            <Icon type={this.state.collapsed ? 'menu-unfold' : 'menu-fold'} />
          </Button>
          <Menu
            mode="inline"
            theme="dark"
            className={styles.menu}
            style={this.state.menuStyle}
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
      </div>
    );
  }
}

