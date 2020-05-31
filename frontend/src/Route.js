import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Recommend from "./page/Homepage/Recommend";
import Check from './page/Homepage/Check';
import LoginPage from './page/LoginPage/LoginPage';
import Main from './page/MainPage/MainPage';
import Signuppage from './page/Signuppage/Signuppage';
import T2 from './page/RecoPage/RecoAll';
import { Navbar, Nav } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import './Nav.scss'
import Logo from './image/logo.png';

export default function Route1() {
    const [isLogin, SetIsLogin] = useState(false)
    console.log(isLogin)
    return (
        <>

<div className="Navigation">
      <Navbar collapseOnSelect expand="lg" bg="light" variant="light" fixed="top"><img src={Logo} classname="logo" alt="logo" width='5%' />
        <Navbar.Brand href="/" className="title">졸업 플래너</Navbar.Brand>
        <Navbar.Toggle aria-controls="responsive-navbar-nav" />
        <Navbar.Collapse id="responsive-navbar-nav">
          <Nav className="mr-auto">
            <Nav.Link href="/Check">졸업요건</Nav.Link>
            <Nav.Link href="/Recommend">시간표</Nav.Link>
          </Nav>
          <Nav>
            <Nav.Link href="/Login">Login</Nav.Link>
          </Nav>
        </Navbar.Collapse>
      </Navbar>
    </div>
        <div className="Route">

            <Router>

                <div>
                    <Switch>

                        <Route path="/" exact={true} component={LoginPage} />
                        <Route path="/Main" exact={true} component={Main} />
                        <Route path="/Login" component={LoginPage} />
                        <Route path="/Check" component={Check} />
                        <Route path="/Recommend" component={T2} />
                        <Route path="/Signup" component={Signuppage} />

                    </Switch>

                </div>

            </Router>

        </div>
        </>
    )
}

