import React from 'react';
import { Image } from 'react-native';
import { Scene, Router } from 'react-native-router-flux';
import { MainMenu } from './Components/MainMenu';
import { PlannedMeals } from './Components/PlannedMeals'
import { SavedMeal } from './Components/SavedMeal'
import { PresetFoods } from './Components/PresetFoods'
import { NutrientSelect } from './Components/NutrientSelect';

const RouterComponent = () => (
        <Router
        duration={0}
        >
        <Scene>
            <Scene 
            key="MainMenu"
            component={MainMenu}
            hideNavBar
            //initial 
            />

            <Scene
            key="PlannedMeals"
            component={PlannedMeals}
            hideNavBar
            initial
            />

            <Scene
            key="SavedMeal"
            component={SavedMeal}
            hideNavBar
            />

            <Scene
            key="PresetFoods"
            component={PresetFoods}
            hideNavBar
            />

            <Scene
            key="NutrientSelect"
            component={NutrientSelect}
            hideNavBar
            />
        </Scene>
                 
        </Router>
    );

export default RouterComponent;