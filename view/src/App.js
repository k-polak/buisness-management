import React, { Component } from 'react';
import logo from './logo_light.svg';
import './App.css';
import Home from './Home';
import Customers from './Customers';
import Products from './Products';
import { BrowserRouter as Router, Switch, Route, Redirect} from 'react-router-dom';
import register from './registerServiceWorker';
import {Navbar, Nav, Form, FormControl, Button} from "react-bootstrap";

register();

class App extends Component {
  render() {
    return (
      <Router basename='/view'>
        <div className="App">
          <Navbar variant="dark" bg="dark" expand="md" fixed="top"
                  className="border-bottom border-primary">
            <Navbar.Brand href="/view">
              <img src={logo} className="App-logo" alt="logo" />
            </Navbar.Brand>
            <Navbar.Toggle aria-controls="responsive-navbar-nav" />
            <Navbar.Collapse id="responsive-navbar-nav">
              <Nav className="mr-auto">
                <Nav.Link href="/view/">Home</Nav.Link>
                <Nav.Link href="#features">Products</Nav.Link>
                <Nav.Link href="#pricing">Clients</Nav.Link>
              </Nav>
              <Form inline>
                <FormControl type="text" placeholder="Search" className="mr-sm-2" />
                <Button variant="outline-primary">Search</Button>
              </Form>
            </Navbar.Collapse>
          </Navbar>
          <main className="App-main">
            <Switch>
              <Route exact path= "/" component={Home} />
              <Route exact path='/customerlist' component={Customers} />
              <Route exact path='/productlist' component={Products} />
            </Switch>
          </main>
        </div>
      </Router>
    );
  }
}

export default App;
