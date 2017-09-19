import React, { Component } from 'react';
import CSSModules from 'react-css-modules';
import styles from './index.css';

class Index extends Component {
  render() {
    const { text } = this.props;
    return (
      <div styleName="container">
        {
           text.map((t) => {
             const { order, event } = t;
             return (
               <div key={order} styleName="stepWrapper">
                 <span styleName="dotted" />
                 <span>{order}</span>
                 <div styleName="stepIntro">{event}</div>
               </div>
             );
           })
        }
      </div>
    );
  }
}
export default CSSModules(Index, styles);
// text.length ||
