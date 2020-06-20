import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Check from './page/Homepage/Container';
import LoginPage from './page/LoginPage/Container';
import Main from './page/MainPage/MainPage';
import Signuppage from './page/Signuppage/Signuppage';
import T2 from './page/RecoPage/Container';
import { Navbar, Nav } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import './Nav.scss'
import Logo from './image/logo.png';
import Store from './Store/Store'
import { Redirect } from 'react-router-dom';
import Result from './page/ResultPage/Container'
import mytable from './page/Mytablepage/Container';

export default class Route1 extends Component{
    constructor(props){
      super(props)
      this.state = {
        logged: false,
        onLogin : this.onLogin,
        onLogout : this.onLogout,
        userId: null
      }
    
    }

    onLogin = (id) => {
      this.setState({
        logged:true,
        userId: id
      })
    }
    onLogout = async() => {
      await this.setState({
        logged:false,
        userId: null
      })
      window.sessionStorage.clear();

    }
    
    componentWillMount() {
      const id = parseInt(window.sessionStorage.getItem('id'));
      if(id){
        this.onLogin(id);}
      else {
        this.onLogout();
      
      }
    }

    render() {
      const {logged, onLogout} = this.state;
    return (
        <>

<Store.Provider value={this.state}>
<div className="Navigation">
      <Navbar collapseOnSelect expand="lg" bg="light" variant="light" fixed="top"><img src={Logo} classname="logo" alt="logo" width='5%' />
        <Navbar.Brand href="/" className="title">졸업 플래너</Navbar.Brand>
        <Navbar.Toggle aria-controls="responsive-navbar-nav" />
        <Navbar.Collapse id="responsive-navbar-nav">
          <Nav className="mr-auto">
            <Nav.Link href="/Check">졸업요건</Nav.Link>
            <Nav.Link href="/Recommend">시간표추천</Nav.Link>
            
            {logged? <Nav.Link href="/mytable">내 시간표</Nav.Link>:<> </>}
          </Nav>
          <Nav>
            
            {logged? <Nav.Link href="/Login" onClick={onLogout}>Logout</Nav.Link> :<Nav.Link href="/Login">Login</Nav.Link>}

          </Nav>
        </Navbar.Collapse>
      </Navbar>
    </div>
        <div className="Route">

            <Router>
            {(this.state.logged === false) && <Redirect to="/Login"/>}

                <div>
                    <Switch>

                        <Route path="/" exact={true} component={Check} />
                        <Route path="/Main" exact={true} component={Main} />
                        <Route path="/Login" component={LoginPage} />
                        <Route path="/Check" component={Check} />
                        <Route path="/Recommend" component={T2} />
                        <Route path="/Signup" component={Signuppage} />
                        <Route path="/Result" component={Result} />
                        <Route path="/mytable" component={mytable} />


                    </Switch>

                </div>

            </Router>

        </div>
        </Store.Provider>
        </>
    )
}}

