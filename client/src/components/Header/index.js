import React, { Component } from 'react';
import { Link } from 'react-router';
import cssModules from 'react-css-modules';
import { Menu, Button, Icon } from 'antd';
import styles from './index.css';

const SubMenu = Menu.SubMenu;
class Index extends Component {
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
    const { route = '', type = '' } = this.props;
    const mainMenus = [
      { text: 'Home', link: '' },
      { text: 'Join us',
        link: '/joinUs',
        children: [
        { text: '纳新要求', link: 'require' },
        { text: '纳新报名', link: 'signUp' },
        { text: '面试题', link: 'interview' },
        { text: '免试题', link: 'free' },
        { text: '面试安排', link: 'students' },
        ]
      },
      { text: 'About us',
        link: 'about',
        children: [
        { text: '实验室简介', link: 'about/lab' },
        { text: '成员简介', link: 'about/people' },
        { text: '开发组', link: 'about/development' },
        { text: '安全组', link: 'about/security' }]
      },
      { text: 'wiki',
        link: 'wiki'
      }
    ];
    const wikiMenus = [
      { text: 'Home', link: '' },
      { text: '开发',
        link: 'wiki/software',
        // children: [
        // { text: 'JAVA后台', link: 'wiki/software/java' },
        // { text: 'php', link: 'wiki/software/php' },
        // { text: 'web前端', link: 'wiki/software/webFF' },
        // { text: 'android', link: 'wiki/software/android' },
        // ]
      },
      { text: '安全',
        link: 'wiki/safe',
      }
    ];
    const menus = !type || type === 'mainHeader' ? mainMenus : wikiMenus;

    return (
      <div styleName="header">
        <Link to="/" styleName="logo" />
        <Menu
          mode="horizontal"
          theme="dark"
          styleName="pcMenu"
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

        <div styleName="responsiveContaner">
          <Button onClick={this.toggleCollapsed} style={{ marginBottom: 16 }} >
            <Icon type={this.state.collapsed ? 'menu-unfold' : 'menu-fold'} />
          </Button>
          <Menu
            mode="inline"
            theme="dark"
            styleName="menu"
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
        <Link to={'/login'} styleName="pcLogin">登录</Link>
        <Link to={'/login'} styleName="responsiveLogin"><Icon type="user" style={{ fontSize: 22, color: '#FFF' }} /></Link>
      </div>
    );
  }
}
export default cssModules(Index, styles);
