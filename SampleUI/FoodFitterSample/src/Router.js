import React from 'react';
import { Image } from 'react-native';
import { Scene, Router } from 'react-native-router-flux';
import { MainMenu } from './Components/MainMenu';

const RouterComponent = () => (
        <Router
        duration={0}
        >
            <Scene 
            key="MainMenu"
            component={MainMenu}
            hideNavBar
            initial 
            />
                 
        </Router>
    );

export default RouterComponent;