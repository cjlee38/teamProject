import React, {Component} from 'react';
import { Navbar, Nav, Form, FormControl, Button, NavDropdown } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import './Nav.scss'
import Logo from './image/logo.png';


class Navigation extends Component {
  render() {
    const { logged, onLogout } = this.props;
    console.log(logged)

    return (
      <div id="nav">

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
                {logged ?
                  <Nav.Link href="/" onclink={onLogout}>로그아웃</Nav.Link> :
                  <Nav.Link href="/Login">로그인/회원가입</Nav.Link>
                }
                {/* <Nav.Link href="/Login">Login</Nav.Link> */}
              </Nav>
            </Navbar.Collapse>
          </Navbar>
        </div>        </div>

    )
  }
}

export default Navigation