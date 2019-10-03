import React from "react";
import ReactTable from "react-table";
import 'react-table/react-table.css'
import { InputGroup, FormControl, Row, Col, Container } from 'react-bootstrap';

class Player extends React.Component {
    render(){
        const { filteredPlayers, countryCounts, updateKeyword } = this.props;

        const columns = [{
            Header: 'Number',
            accessor: 'id'
          }, {
            Header: 'Name',
            accessor: 'name'
          }, {
            Header: 'Nationality',
            accessor: 'nationality'
          }, {
            Header: 'Position',
            accessor: 'pos'
          }, {
            Header: 'Height',
            accessor: 'height',
            Cell: props => <div>{props.value != null ? props.value.toFixed(2) : ""}</div>
          }, {
            Header: 'Weight',
            accessor: 'weight'
          }, {
            Header: 'Date Of Birth',
            accessor: 'dateOfBirth'
          },{
          Header: 'City of Birth',
          accessor: 'cityOfBirth'
          }
        ]

        return (
          <div>
            <Container style={{maxWidth: "100%"}}>
            <br/>
            <Row>
            <InputGroup onChange={updateKeyword}>
              <InputGroup.Prepend>
                <InputGroup.Text id="basic-addon1">
                  <span className="fa fa-search"></span>
                </InputGroup.Text>
              </InputGroup.Prepend>
              <FormControl
                placeholder="Key Word"
                aria-label="search"
                aria-describedby="basic-addon1"
              />
            </InputGroup>
            </Row>
            <br/>
            <Row>
                {(()=>{
                  let keys = Object.keys(countryCounts)
                  if(keys.length !== 0) {
                    let len = keys.length > 6 ? 6 : keys.length                  
                    let colWidth = Math.floor(12 / len)
                    let cols = keys.map((country, idx) => {
                      return (
                        <Col md={colWidth} sm={colWidth} key={idx}
                          style={{borderWidth: "3px", borderStyle: "solid"}}>
                          {country}: {countryCounts[country]}
                        </Col>    
                      )
                    })
                    return cols
                  }
                })()}
            </Row>
              <br/>                         
            <Row>
            <ReactTable
              data={filteredPlayers}
              columns={columns}
              showPagination={false}
              pageSize={filteredPlayers.length}
              style={{
                height: "500px",
                width: "100%"
              }}
              className="-striped -highlight"
            />
            </Row>
            </Container>
          </div>)
    }
}

export default Player;