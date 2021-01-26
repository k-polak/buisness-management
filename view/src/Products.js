import React, {Component} from 'react';
import {Card, Button, Col, Row, Container, FormControl, Form, Navbar} from 'react-bootstrap';
import axios from 'axios';
import no_image from './no_image.svg';

export default class Products extends Component {

  constructor(props) {
    super(props)
    this.state = {
      selectedProduct: 1,
      search: "",
      priceLow: null,
      priceHigh: null
    }
  }

  // Dynamic products search
  search(event) {
    this.setState({search: event.target.value})
  }

  // Filter price
  filterPriceLow(event) {
    this.setState({priceLow: event.target.value})
  }

  filterPriceHigh(event) {
    this.setState({priceHigh: event.target.value})
  }

  //function which is called the first time the component loads
  componentDidMount() {
    this.getProductData();
  }

  //Function to get the Customer Data from json
  getProductData() {
    axios.get('/service-1.0-SNAPSHOT/products').then(response => {
      this.setState({productList: response})
    })
  }

  render() {
    // If could not load products
    if (!this.state.productList)
      return (<p>Loading data</p>)

    // Filter products
    let filteredProducts = this.state.productList.data.filter(
        (product) => {
          return (product.name.toLowerCase().indexOf(this.state.search.toLowerCase()) !== -1) &&
              (this.state.priceLow ? parseInt(product.price) >= this.state.priceLow : true) &&
              (this.state.priceHigh ? parseInt(product.price) <= this.state.priceHigh : true);
        }
    );

    return (
    <Container fluid className="pt-5">
      <Navbar variant="dark" expand="md" className="mb-5">
        <Navbar.Toggle aria-controls="responsive-navbar-nav" />
        <Navbar.Collapse id="responsive-navbar-nav">
          <Form inline>
            <FormControl type="text" placeholder="Search" onChange={this.search.bind(this)} className="m-3" />
            <FormControl type="number" value={this.state.priceLow} min={0} placeholder="Price from"
                         onChange={this.filterPriceLow.bind(this)} id="price-min" className="m-3" />
            <FormControl type="number" value={this.state.priceHigh} min={0} placeholder="Price to"
                         onChange={this.filterPriceHigh.bind(this)} id="price-max" className="m-3" />
          </Form>
        </Navbar.Collapse>
      </Navbar>
      <Row xs={1} sm={2} md={3} lg={4}>
        {filteredProducts.map(product =>
            <Col className="mb-5">
              <Card key={product.id} bg="dark" text="light" className="h-100">
                <Card.Img variant="top" src={no_image} className="product-image my-3" />
                <Card.Body>
                  <Card.Title>{product.name}</Card.Title>
                </Card.Body>
                <Card.Footer>
                  <Card.Title>Cena: {product.price} zł</Card.Title>
                  <p>Dostępna ilość: {product.quantity}</p>
                  <Button variant="outline-primary" onClick={() => this.setState({selectedProduct: product.id})}>
                    Dodaj do koszyka
                  </Button>
                </Card.Footer>
              </Card>
            </Col>)
        }
      </Row>
    </Container>);
  }

}
