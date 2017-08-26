import React, { Component } from 'react';
import styles from './index.css';

export default class extends Component {
  state = {
    wrapperStyle: { backgroundPositionX: '50%', backgroundPositionY: '50%' }
  }
  componentDidMount() {
    this.setState({
      wrapperStyle: {
        backgroundPositionX: '50%',
        backgroundPositionY: '50%'
      }
    });
  }

  handleMove = (e) => {
    let x = (e.pageX * -1 / 3);
    let y = (e.pageY * -1 / 3);
    this.setState({
      wrapperStyle: {
        backgroundPositionX: x,
        backgroundPositionY: y
      }
    });
  }
  render() {
    return (
      <div className={styles.homeWrapper} >
        <div className={styles.home} onMouseMove={this.handleMove} style={this.state.wrapperStyle}>
          <h1 className={styles.welc}> WELCOME TO XUPTSEC</h1>
        </div>
      </div>
    );
  }
}
