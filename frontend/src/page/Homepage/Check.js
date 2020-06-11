import React, { Component } from 'react';
import TabContent from './components/TabContent';
import { BrowserRouter as Router, Route, Switch, Link } from 'react-router-dom';
import Crawler from './components/Crawler';
import Spinner from 'react-bootstrap/Spinner';
import Button from '@material-ui/core/Button';

import './Check.scss'

class Check extends React.Component {

  constructor(props) {
    super(props)
    this.state = {
      userID:this.props.userId,
      dataChange:true
    }

  }
  Change = async () => {
    // console.log(this.state.dataChange)
    await this.setState((prevState) => ({dataChange: !prevState.dataChange}))
    
  }

  render() {
    // console.log(this.state.dataChange)

    return (

      <>

        <div className="CheckPage">
          <div className="body">
            <div className="head">내 졸업 요건 확인</div>
            <div className="Crwaler">
      
              <Crawler Change={this.Change}/>
            </div>
          {this.state.dataChange? <TabContent userID={this.props.userId} test={this.state.dataChange}/> :  <><Spinner style={{marginTop:"5%"}}animation="grow" variant="info" /><div className="spinner" style={{marginTop:"5%"}}>데이터 가져오는 중..(약 1분 소요)</div></> }
           


          </div>

          <div className="foot">
          </div>
        </div>
      </>
    );
  }
}

export default Check;