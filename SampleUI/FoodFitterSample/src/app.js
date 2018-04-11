import React, { Component } from 'react';
import { MainMenu } from './Components/MainMenu';
import { PlannedMeals } from './Components/PlannedMeals'
import RouterComponent from './Router';

class App extends Component {
    render() {
        return (
            <RouterComponent />
        );
    }
}

export default App;