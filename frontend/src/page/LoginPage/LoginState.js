import React, {Component, useState} from 'react';
import {BrowserRouter as Router, Route, Switch, Link} from 'react-router-dom';
import Header from './Layout/Header';
import Store from './Store/store';
import LoginP from './components/LoginContainer';
import Rout from '../../Route';
import Navigation from '../../Nav';

class LoginState extends Component{

    constructor(props){
        super(props)
        this.state={
            logged:false,
            onLogin:this.onLogin,
            onLogout: this.onLogout
        }
    }

//Login Function
onLogin=()=>{
    this.setState({
        logged:true
    })
    console.log(this.state.logged);
}

//Logout Func
onLogout=()=>{
    this.setState({
        logged:false
    });
}

  render(){
      const{ logged, onLogout} = this.state;
      console.log(logged);
      return(
        <Store.Provider value={this.state}>
                <Navigation logged={logged} onLogout={onLogout} />
              {/* <Header logged={logged} onLogout={onLogout}/>                   */}
             <Rout>
                {/* <LoginP/> */}
                </Rout>
        </Store.Provider> 
      );
    }
}

export default LoginState