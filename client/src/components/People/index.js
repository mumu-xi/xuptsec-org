import React, { Component } from 'react';
import CSSModules from 'react-css-modules';
import styles from './index.css';

class Index extends Component {
  render() {
    const { data } = this.props;
    return (
      <div styleName="container">
        {
          data.length ? data.map((person) => {
            const { peopleName, peopleIntro, picurl, peopleId } = person;
            return (
              <div styleName="member" key={peopleId}>
                <img src={picurl} alt="人物介绍图片" />
                <div styleName="info">
                  <h2>{peopleName}</h2>
                  <p>{peopleIntro}</p>
                </div>
              </div>
            );
          }) : <div styleName="nodata">暂无人物简介</div>
        }
      </div>
    );
  }
}
export default CSSModules(Index, styles);
