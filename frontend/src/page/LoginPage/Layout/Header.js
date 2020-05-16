import React, {Component, useState} from 'react';
import {BrowserRouter as Router, Route, Switch, Link} from 'react-router-dom';
import LoginState from '../LoginState';
import LoginContainer from '../components/LoginContainer'

class Header extends Component{
    render(){
        const { logged, onLogout }= this.props;
        console.log(logged);
        return(
            <div>
                    {logged ?
                    <Link to="/" onclink={onLogout}>로그아웃</Link>:
                    <Link to="/Login">로그인/회원가입</Link>
                    }
            </div>  
        )
    }
}

export default Header;