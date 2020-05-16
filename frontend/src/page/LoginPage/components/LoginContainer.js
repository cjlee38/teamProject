import React, {Component} from 'react';
import Store from '../Store/store';
import Login from '../LoginPage';

function LoginContainer(){
    console.log("cont")
    return(
    <Store.Consumer>
        {store => (
            <Login onLogin = {store.onLogin}/>)}
    </Store.Consumer>
)
 }
export default LoginContainer;