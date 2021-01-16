import React, {Component} from 'react';
import {Card, Button, Col, Row, Container} from 'react-bootstrap';
import axios from 'axios';
import no_image from './no_image.png';

export default class Products extends Component {

  constructor(props) {
    super(props)
    this.state = {
      selectedProduct: 1
    }
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
  };

  render() {
    if (!this.state.productList)
      return (<p>Loading data</p>)
    return (
    <Container fluid className="addmargin">
      <Row sm={2} md={3} lg={4}>
        {this.state.productList.data.map(product =>
            <Col className="mb-5">
              <Card key={product.id} border="secondary" className="h-100">
                <Card.Img variant="top" src={no_image} />
                <Card.Body>
                  <Card.Title>{product.name}</Card.Title>
                </Card.Body>
                <Card.Footer className="bg-white">
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
