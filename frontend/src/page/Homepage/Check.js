import React, { Component } from 'react';
import TabContent from './components/TabContent';
import { BrowserRouter as Router, Route, Switch, Link } from 'react-router-dom';
import Crawler from './components/Crawler';
import './Check.scss'

class Check extends React.Component {

  constructor(props) {
    super(props)
    this.state = {
      userID:this.props.userId,
      dataChange:false
    }

  }
  Change = async () => {
    console.log(this.state.dataChange)
    await this.setState((prevState) => ({dataChange: !prevState.dataChange}))
    
  }

  render() {
    console.log(this.state.dataChange)

    return (

      <>

        <div className="CheckPage">
          <div className="body">
            <div className="head">내 졸업 요건 확인</div>
            <div className="Crwaler">

              <Crawler Change={this.Change}/>
            </div>

            <TabContent userID={this.props.userId} test={this.state.dataChange}/>


          </div>

          <div className="foot">
          </div>
        </div>
      </>
    );
  }
}

export default Check;