import React, { Component } from 'react';
import logo from './logo_light.svg';
import './App.css';
import Home from './Home';
import Customers from './Customers';
import Products from './Products';
import { BrowserRouter as Router, Switch, Route} from 'react-router-dom';
import register from './registerServiceWorker';
import {Navbar, Nav} from "react-bootstrap";
import AddProduct from "./AddProduct";
import Cart from "./Cart";

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
                <Nav.Link href="/view">Home</Nav.Link>
                <Nav.Link href="/view/productlist">Products</Nav.Link>
                <Nav.Link href="/view/addproduct">Add product</Nav.Link>
                <Nav.Link href="/view/cart">Cart</Nav.Link>
              </Nav>
            </Navbar.Collapse>
          </Navbar>
          <main className="App-main min-vh-100">
            <Switch>
              <Route exact path= "/" component={Home} />
              <Route exact path='/customerlist' component={Customers} />
              <Route exact path='/productlist' component={Products} />
              <Route exact path='/addproduct' component={AddProduct} />
              <Route exact path='/cart' component={Cart} />
            </Switch>
          </main>
        </div>
      </Router>
    );
  }
}

export default App;
