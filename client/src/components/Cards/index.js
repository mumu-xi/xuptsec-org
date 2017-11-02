import React, { Component } from 'react';
import { Link } from 'react-router';
import CSSModules from 'react-css-modules';
import styles from './index.css';

class Index extends Component {
  render() {
    // const { data=[] } = this.props;
    const data = [{
      title: 'javascript的几种基本类型',
      author: '木木木夕',
      date: '2017-11-1',
      tags: 'javascript',
      outline: '在英语中，Loop这个词指的是由弯曲的曲线所产生的形状。类似的概念，Loop这个词已经被用于编程中。如果你看到下图，你就会清楚的知道指令的流动是如何在一个循环的动作中不断重复的。在编程中，循环的概念并不是什么新概念，它们常常在编码时使用。虽然不是的语言其语法不同，但基本概念是相同的，根据需要重复相同的代码块。JavaScript增加了循环类型（包括各种类型的循环），并使其与它们的工作更加舒适和高效。在本文中，我们将学习JavaScript中所有可用的循环。',
      cardsId: 1
    },
    {
      title: 'javascript的几种基本类型',
      author: '木木木夕',
      date: '2017-11-1',
      tags: 'javascript',
      outline: '在英语中，Loop这个词指的是由弯曲的曲线所产生的形状。类似的概念，Loop这个词已经被用于编程中。如果你看到下图，你就会清楚的知道指令的流动是如何在一个循环的动作中不断重复的。在编程中，循环的概念并不是什么新概念，它们常常在编码时使用。虽然不是的语言其语法不同，但基本概念是相同的，根据需要重复相同的代码块。JavaScript增加了循环类型（包括各种类型的循环），并使其与它们的工作更加舒适和高效。在本文中，我们将学习JavaScript中所有可用的循环。',
      cardsId: 2
    }];
    return (
      <div styleName="container">
        {
          data.length ? data.map((card) => {
            const { title, author, date, tags, outline, cardsId } = card;
            return (
              <div key={cardsId} styleName="node">
                <h1><Link to="#">{title}</Link></h1>
                <div styleName="nodeHeader">
                  <span>作者: {author}</span>
                  <span>日期: {date}</span>
                  <span><i /> {tags} </span>
                </div>
                <p>{outline}</p>
                <div styleName="more"><Link to="#">阅读全文</Link></div>
              </div>
            );
          }) : <div styleName="nodata">暂无该内容帖子</div>
        }
      </div>
    );
  }
}
export default CSSModules(Index, styles);

/**
 * data={
 *  title: '',
 *  author: '',
 *  date: '',
 *  cardsType: '',
 *  outline: ''
 * }
*/
