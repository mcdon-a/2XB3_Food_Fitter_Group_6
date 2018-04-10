import React, { Component } from 'react';
import { AsyncStorage, View, Text } from 'react-native';
import { Actions } from 'react-native-router-flux';
import { Button } from './common/Button'

class SavedMeal extends Component{
    state = { item: null, presets: null, nutrients: null }
    async componentWillMount(){
        this.setState({ item: { "Nutrients" : ["Calories", "Protein", "Carbs"], "Values" : ['2000', '9g', '2g'], "Day" : 'March 22 Lunch', "Items": ['eggs', 'yam', 'apples', 'bubble tea', 'hamburger']}, presets: JSON.parse(await AsyncStorage.getItem('presets')), nutrients: JSON.parse(await AsyncStorage.getItem('nutrient')) });
    }
    renderChoices(){
        const presetarray = []
        const presetfoods = ["Hot chocolate, cocoa, homemade, prepared with whole milk", "Cheese souffle", "Muffin, plain, homemade, made with 2% milk", "Apple, canned, sweetened, sliced, heated"];
        presetarray.push(this.state.presets.presets[0]);
        presetarray.push(this.state.presets.presets[1]);
        for (i = 0; i < presetfoods.length; i++){
            presetarray.push(presetfoods[i]);
        }
        const choices = presetarray.map((item) => {
            return(
                <View style={{ marginRight: 20, marginLeft: 20, backgroundColor: '#ced7dc', height: 50, borderBottomWidth: 1, borderColor: 'white', justifyContent: 'center' }}>
                    <Text style={{ marginLeft: 20, color: '#859499', fontSize: 20 }}>{item}</Text>
                </View>
            );
        });
        return(
            [...choices]
        );
    }
    renderNutrients(){
        const nutrients = [];
        for (i = 0; i < this.state.nutrients.nutrients.length; i++){
            nutrients.push(<Text style={{ marginTop: 10, marginLeft: 20, color: '#859499', fontSize: 22 }}>Total {this.state.nutrients.nutrients[i]}: {this.state.nutrients.values[i]}</Text>);
        }
        return (
            [...nutrients]
        );
    }
    render(){
        if (this.state.item !== null && this.state.presets != null && this.state.nutrients != null){
            return(
                <View style={{ backgroundColor: 'white', flex: 1 }}>
                    <Text style={{ fontSize: 25, marginLeft: 20, marginTop: 20, backgroundColor: 'white', marginBottom: 20 }}>Your Personalized Meal</Text>
                    {this.renderChoices()}
                    {this.renderNutrients()}
                    <Button onPress={() => Actions.PlannedMeals()} style={{ marginTop: 20, width: 350 }}>Back</Button>
                </View>
            );
        }
        else{
            return(
                <View />
            );
        }
    }
}

export { SavedMeal };