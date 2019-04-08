import * as React from 'react';
import './App.css';
import Beer from './Beer';

import logo from './logo.svg';

class App extends React.Component {
    render() {
        return (
            <div className="App">
                <header className="App-header">
                    <img src={logo} className="App-logo" alt="logo" />
                </header>
                <p className="App-intro">
                    <Beer/>
                </p>
            </div>
        );
    }
}

export default App;
