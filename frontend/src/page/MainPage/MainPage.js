import React from 'react';
import {BrowserRouter as Router, Route, Switch, Link} from 'react-router-dom';
import Button from './components/Button';
import Title from './components/Title';
import Logo from '../../image/logo.png';
import './MainPage.scss';
import Recommend from "../Homepage/Recommend";
import Check from '../Homepage/Check';
import LoginPage from '../LoginPage/LoginPage';


function MainPage () {
    console.log('mainpage')
    return (
        <Router> 
         
                 
 

                <Switch>

                <Route path="/" exact={true} component={Main} />
                <Route path="/Main" exact={true} component={Main} />
                <Route path="/Login" component={LoginPage} />

                    <Route path="/Check" component={Check} />
                    <Route path="/Recommend" component={Recommend} />
                </Switch>

                
   
        </Router>

    )
} 


const Main = ({match}) => {
    console.log(match.params.id)
    return (
        
        <div className="Main">
            {/* <img src={Logo} className="logo" alt="logo"/> */}
            <div className="body">
                <Title/>
                <Link to="/Check">
                    <Button name={"졸업요건 확인하기"} value={"check"}/>
                </Link>
                <Link to="/Recommend">
                    <Button name={"시간표 추천받기"} value={"check"}/>
                </Link>
                <Link to="/Login">
                    <Button name={"로그인"} value={"check"}/>
                </Link>
               
            </div>
        </div>
    )
}




export default MainPage