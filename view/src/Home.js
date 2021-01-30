import React, {Component} from 'react';
import {Card, Button, Col, Row, Container, Jumbotron} from 'react-bootstrap';
import heading_image from './heading_image.png';

export default class Home extends Component {

    constructor(props) {
        super(props)
    }

    //function which is called the first time the component loads
    componentDidMount() {
        //this.getProductData();
    }

    render() {
        return (
            <Container fluid>
                <Row>
                    <Col md={5} className="pt-5 heading-wrapper">
                        <img src={heading_image} className="mw-100 mh-100"/>
                    </Col>
                    <Col md={7} className="pt-5">
                        <Jumbotron fluid className="heading px-5">
                            <h1>Welcome to our amazing online store!</h1>
                            <p>
                                This web page was designed and developed by <b>Krzysztof Polak</b> and
                                <b> Adam Księżyk</b> within the subject <b>Data Bases</b>.
                            </p>
                            <p>
                                <a href="/view/productlist" className="btn btn-outline-primary">Show products</a>
                            </p>
                        </Jumbotron>
                    </Col>
                </Row>
            </Container>
        );
    }
}
