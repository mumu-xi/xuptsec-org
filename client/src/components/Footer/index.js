import React, { Component } from 'react';
import cssModules from 'react-css-modules';
import styles from './index.css';

 class Index extends Component {
   render() {
     return (
       <footer styleName="footer">
         <span styleName="copyRight">&copy; 2009-2017西邮信息安全实验室 All right reserved</span>
         <div styleName="qrWrapper">
           <p>关注我们</p>
           <img styleName="qr" src="../../../public/qrcode.png" alt="QR" />
         </div>
       </footer>
     );
   }
}
export default cssModules(Index, styles);
