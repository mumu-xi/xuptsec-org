import React, { Component } from 'react';
import cssModules from 'react-css-modules';
import styles from './index.css';

class Index extends Component {
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
    const x = (e.pageX * -1 / 3);
    const y = (e.pageY * -1 / 3);
    this.setState({
      wrapperStyle: {
        backgroundPositionX: x,
        backgroundPositionY: y
      }
    });
  }
  render() {
    return (
      <div styleName="homeWrapper">
        <div styleName="home" onMouseMove={this.handleMove} style={this.state.wrapperStyle}>
          <h1 styleName="welc"> WELCOME TO XUPTSEC</h1>
        </div>
      </div>
    );
  }
}
export default cssModules(Index, styles);
