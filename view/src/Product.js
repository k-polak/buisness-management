import React, {Component} from "react";
import axios from "axios";
import {Button, Card, Col, Container, Form, FormControl, FormLabel, Navbar, Row} from "react-bootstrap";
import no_image from './no_image.svg';
import Cart from "./Cart";

export default class Product extends Component {
    constructor(props) {
        super(props);
        this.state = {
            productId: props.match.params.id,
            quantity: 1
        };
    }

    //function which is called the first time the component loads
    componentDidMount() {
        this.getProductData();
    }

    //Function to get the Customer Data from json
    getProductData() {
        axios.get(`/service-1.0-SNAPSHOT/products/find?id=${parseInt(this.state.productId)}`).then(response => {
            this.setState({product: response.data})
        })
    }

    render() {
        // If could not load products
        if (!this.state.product)
            return (<p>Loading data</p>)

        return (
            <Container fluid>
                <Row className="justify-content-md-center">
                    <Col md={5}>
                        <img src={no_image} className="product-image px-5"/>
                    </Col>
                    <Col md={3}>
                        <Card bg="dark" text="light">
                            <Card.Header><Card.Title>{this.state.product.name}</Card.Title></Card.Header>
                            <Card.Body>
                                <p className="text-left">Price: {this.state.product.price} PLN</p>
                                <p className="text-left">Available amount: {this.state.product.quantity}</p>
                                <Form inline>
                                    <FormLabel>Quantity:</FormLabel>
                                    <FormControl type="number" value={this.state.quantity} min={1}
                                                 max={this.state.product.quantity}
                                                 onChange={event => this.setState({quantity: event.target.value})}
                                                 id="price-max" className="m-3" />
                                </Form>
                                <Button variant="outline-primary" onClick={() => Cart.addToCart(
                                    this.state.product.id, this.state.quantity)}>
                                    Add to cart
                                </Button>
                            </Card.Body>
                        </Card>
                    </Col>
                </Row>
            </Container>
        );
    }
}