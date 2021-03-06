import React, {Component} from "react";
import axios from "axios";
import {Button, Col, Container, Row, Table} from "react-bootstrap";

export default class Cart extends Component {
    constructor(props) {
        super(props);
        this.state = {
            clientId: 2,
            cart: JSON.parse(localStorage.getItem("cart")) ? JSON.parse(localStorage.getItem("cart")) : {}
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
    }

    static addToCart(id, quantity) {
        // TODO add confirmation dialog
        let cartCopy = JSON.parse(localStorage.getItem("cart")) ? JSON.parse(localStorage.getItem("cart")) : {}
        Object.assign(cartCopy, {id: "value3"});
        cartCopy[parseInt(id)] = parseInt(quantity);
        localStorage.setItem("cart", JSON.stringify(cartCopy));
    }

    removeFromCart(id) {
        let cartCopy = this.state.cart;
        delete cartCopy[id];
        this.setState({cart: cartCopy});
        localStorage.setItem("cart", JSON.stringify(this.state.cart));
    }

    placeOrder(){
        let createOrder = {
            clientId: 2,
            productAmountMap: JSON.parse(localStorage.getItem("cart"))
        }
        axios.post('/service-1.0-SNAPSHOT/orders/create',JSON.stringify(createOrder),  {
            headers: {
                'Content-Type': 'application/json',
            }})
            .then(response => console.log(response))
    }

    render() {
        // If could not load products
        if (!this.state.productList)
            return (<p>Loading data</p>)

        // TODO create get products by ids endpoint
        // Filter products
        let filteredProducts = this.state.productList.data.filter(
            product => {
                return this.state.cart.hasOwnProperty(product.id);
            }
        );

        let sum = filteredProducts.reduce((ans, product) => {
            return ans + (parseFloat(product.price) * parseFloat(this.state.cart[product.id]));
        }, 0);

        return (
            <Container fluid>
                <Row className="justify-content-md-center">
                    <Col lg={8}>
                        <Table striped bordered hover variant="dark">
                            <thead>
                                <th>Name</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th></th>
                            </thead>
                            <tbody>
                                {filteredProducts.map(product =>
                                    <tr>
                                        <td>{product.name}</td>
                                        <td>{product.price} PLN</td>
                                        <td>{this.state.cart[product.id]}</td>
                                        <td>
                                            <Button variant="outline-danger" onClick={() => this.removeFromCart(product.id)}>
                                                Remove
                                            </Button>
                                        </td>
                                    </tr>
                                )}
                                <tr>
                                    <td>Sum:</td>
                                    <td> {sum.toFixed(2)} PLN</td>
                                    <td></td>
                                    <td></td>
                                </tr>
                            </tbody>
                        </Table>
                    </Col>
                </Row>
                <Button variant="outline-primary" className="mt-5" onClick={this.placeOrder}>Buy</Button>
            </Container>
        )
    }
}
