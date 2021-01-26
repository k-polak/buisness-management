import React, {Component} from 'react';
import {Button, Container, Form} from 'react-bootstrap';
import axios from 'axios';

export default class AddProduct extends Component {

    constructor(props) {
        super(props)
        this.state = {
            name: "",
            code: "",
            quantity: 0.0,
            price: 0.0
        }
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    //Function to get the Customer Data from json
    getProductData() {
        axios.get('/service-1.0-SNAPSHOT/products').then(response => {
            this.setState({productList: response})
        })
    }

    handleSubmit(){
        axios.post('/service-1.0-SNAPSHOT/products/create', JSON.stringify(this.state),  {
            headers: {
                'Content-Type': 'application/json',
            }}).then(response => {
            console.log(response)
        })
    }

    handleChange = (event) => {
        let nam = event.target.name;
        let val = event.target.value;
        this.setState({[nam]: val});
    }

    render() {
        return (
            <Container className="pt-5">
                <Form>
                    <Form.Group controlId="formNameId">
                        <Form.Label>Product name</Form.Label>
                        <Form.Control type="text" placeholder="Product name" name="name" onChange={this.handleChange} />
                    </Form.Group>
                    <Form.Group controlId="formCodeId">
                        <Form.Label>Code</Form.Label>
                        <Form.Control type="text" placeholder="Code" name="code" onChange={this.handleChange} />
                    </Form.Group>
                    <Form.Group controlId="formQuantityId">
                        <Form.Label>Quantity</Form.Label>
                        <Form.Control type="number" min="0" placeholder="Quantity" name="quantity" onChange={this.handleChange}/>
                    </Form.Group>
                    <Form.Group controlId="formPriceId">
                        <Form.Label>Price</Form.Label>
                        <Form.Control type="number" step="0.01" min="0" placeholder="Price" name="price" onChange={this.handleChange}/>
                    </Form.Group>
                    <Button variant="primary" onClick={this.handleSubmit} type="button">
                        Submit
                    </Button>
                </Form>
            </Container>);
    }
}

