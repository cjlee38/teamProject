import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Recommend from "./page/Homepage/Recommend";
import Check from './page/Homepage/Check';
import LoginPage from './page/LoginPage/LoginPage';
import Main from './page/MainPage/MainPage';
import Signuppage from './page/Signuppage/Signuppage';


function route() {
    return (
        <div className="Route">

            <Router>

                <div>
                    <Switch>

                        <Route path="/" exact={true} component={Main} />
                        <Route path="/Main" exact={true} component={Main} />
                        <Route path="/Login" component={LoginPage} />
                        <Route path="/Check" component={Check} />
                        <Route path="/Recommend" component={Recommend} />
                        <Route path="/Signup" component={Signuppage} />

                    </Switch>

                </div>

            </Router>

        </div>
    )
}

export default route