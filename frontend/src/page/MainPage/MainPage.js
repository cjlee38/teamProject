import React from 'react';
import {BrowserRouter as Router, Route, Link} from 'react-router-dom';
import Button from './components/Button';
import Title from './components/Title';
import Logo from '../../image/logo.png';
import './MainPage.scss';
import Recommend from "../Homepage/Recommend";
import Check from '../Homepage/Check';


function MainPage () {
    return (
        <Router> 
            <div style={{margin:20}}> 
                 
                <div>   
                 <hr />
                    <Route path="/" exact={true} component={Main} />
                    <Route path="/Check" component={Check} />
                    <Route path="/Recommend" component={Recommend} />
                </div>
                
            </div> 
        </Router>
    )
} 


const Main = () => {
    return (
        <div className="Main">
            <img src={Logo} className="logo" alt="logo"/>
            <div className="body">
                <Title/>
                <Link to="/Check">
                    <Button name={"졸업요건 확인하기"} value={"check"}/>
                </Link>
                <Link to="/Recommend">
                    <Button name={"시간표 추천받기"} value={"check"}/>
                </Link>
            </div>
        </div>
    )
}


const Login=({match}) =>{
    console.log(match.params)

}

export default MainPage
