import React, { Component } from 'react';
import cssModules from 'react-css-modules';
import styles from './index.css';
import Cards from '../../components/Cards';

class Index extends Component {
  render() {
    return (
      <div styleName="cardsWrapper">
        <Cards />
      </div>
    );
  }
}
export default cssModules(Index, styles);
