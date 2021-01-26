import React, {Component} from 'react';
import axios from 'axios'
import {ListGroup} from 'react-bootstrap';

export default class Customers extends Component {

  constructor(props) {
    super(props)
    this.state = {
      selectedCustomer: 1
    }
  }

  //function which is called the first time the component loads
  componentDidMount() {
    this.getCustomerData();
  }

  //Function to get the Customer Data from json
  getCustomerData() {
    axios.get('/service-1.0-SNAPSHOT/clients').then(response => {
      this.setState({customerList: response})
    })
  }

  render() {
    if (!this.state.customerList)
      return (<p>Loading data</p>)
    return (
      <div className="col-md-10">
        {
          this.state.customerList.data.map(customer =>
            <ListGroup horizontal>
              <ListGroup.Item>{customer.lastName}</ListGroup.Item>
              <ListGroup.Item>{customer.city}</ListGroup.Item>
            </ListGroup>
          )
        }
      </div>
      )
  }
}
