import React, { Component } from 'react';
import styles from './index.css';
import People from '../../components/People';
import config from '../../config';
const { peopleUrl } = config.api;

const data = [
  { name: '王琛 2014级 安全组负责人', intro: '免试保送进入浙江大学竺可桢学院求是科学班安徽省理科并列第四名考入清华计算机系，后保送清华高性能计算研究所，获硕士学位。多年深耕技术创业领域，在云计算、移动社交、多媒体处理等领域有着多年的技术积累。', picurl: '../../public/pic.jpg' },
  { name: '史欣鹭 2014级 开发组负责人', intro: '技术背景的连续创业者。安徽省理科并列第四名考入清华计算机系安徽省理科并列第四名考入清华计算机系，后保送清华高性能计算研究所，获硕士学位。多年深耕技术创业领域，在云计算、移动社交、多媒体处理等领域有着多年的技术积累。', picurl: '../../public/pic.jpg' }
];
// const data=[];
export default class extends Component {
  // ComponentDidMount(){
  //   fetch(peopleUrl, {
  //     method:'GET'
  //   }).then((res)=>res.json()).then((res)=>{
  //     const { state,data }=res;
  //     this.props.data=data;
  //   })
  // }
  render() {
    return (
      <div>
        <h3 className={styles.title}>人物简介</h3>
        <People data={data} />
      </div>
    );
  }
}
