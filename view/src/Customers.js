import React, {Component} from 'react';
import {Card, Button} from 'react-bootstrap'
import CustomerDetails from './CustomerDetails'
import axios from 'axios'

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
  };

  render() {
    if (!this.state.customerList)
      return (<p>Loading data</p>)
    return (<div className="addmargin">
      <div className="col-md-3">
        {

          this.state.customerList.data.map(customer =>
          <Card key={customer.id} className="center-align">
            <Card.Header>
              <Card.Title>{customer.firstName}</Card.Title>
            </Card.Header>
            <Card.Body>
              <p>{customer.lastName}</p>
              <p>{customer.city}</p>
              <Button bsStyle="info" onClick={() => this.setState({selectedCustomer: customer.id})}>

                Click to View Details

              </Button>

            </Card.Body>
          </Card>)
        }
      </div>
      <div className="col-md-6">

      </div>
    </div>)
  }

}
