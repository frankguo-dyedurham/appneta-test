import React, { Component } from 'react';
import ReactTable from "react-table";
import "react-table/react-table.css";

class App extends Component {
  
  render() {
    const data = [{
      name: 'Roy Agasthyan',
      age: 26
    },{
      name: 'Sam Thomason',
      age: 22
    },{
      name: 'Michael Jackson',
      age: 36
    },{
      name: 'Samuel Roy',
      age: 56
    },{
      name: 'Rima Soy',
      age: 28
    },{
      name: 'Suzi Eliamma',
      age: 28
    }]

    const columns = [{
      Header: 'Name',
      accessor: 'name'
    },{
      Header: 'Age',
      accessor: 'age'
    }]

    return (
          <div>
              <ReactTable
                data={data}
                columns={columns}
              />
          </div>      
    )

  }
}

export default App;