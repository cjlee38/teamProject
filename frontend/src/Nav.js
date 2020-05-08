import React from 'react';
import { Navbar, Nav, Form, FormControl, Button, NavDropdown } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import './Nav.scss'
import Logo from './image/logo.png';

function Navigation (){
    return (
        <div className="Navigation">
<Navbar collapseOnSelect expand="lg" bg="light" variant="light" fixed="top"><img src={Logo} classname="logo" alt="logo" width='5%' />
  <Navbar.Brand href="/" className="title">졸업 플래너</Navbar.Brand>
  <Navbar.Toggle aria-controls="responsive-navbar-nav" />
  <Navbar.Collapse id="responsive-navbar-nav">
    <Nav className="mr-auto">
      <Nav.Link href="/Check">졸업요건</Nav.Link>
      <Nav.Link href="/Recommend">시간표</Nav.Link>

      {/* <NavDropdown title="Dropdown" id="collasible-nav-dropdown">
        <NavDropdown.Item href="#action/3.1">Action</NavDropdown.Item>
        <NavDropdown.Item href="#action/3.2">Another action</NavDropdown.Item>
        <NavDropdown.Item href="#action/3.3">Something</NavDropdown.Item>
        <NavDropdown.Divider />
        <NavDropdown.Item href="#action/3.4">Separated link</NavDropdown.Item>
      </NavDropdown> */}
    </Nav>
    <Nav>
    <Nav.Link href="/Login">Login</Nav.Link>
    </Nav>
  </Navbar.Collapse>
</Navbar>
  </div>
    )
}

export default Navigation