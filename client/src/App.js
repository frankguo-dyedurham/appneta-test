import React, { Component } from 'react';
import './App.css';
import PlayerContainer from './container/PlayerContainer.js'
import 'bootstrap/dist/css/bootstrap.min.css';

class App extends Component {
  render() {
    return (
      <div className="App">
        <header className="App-header">
          <h1 className="App-title">AppNeta</h1>
        </header>
        <PlayerContainer/>
      </div>
    );
  }
}

export default App;
