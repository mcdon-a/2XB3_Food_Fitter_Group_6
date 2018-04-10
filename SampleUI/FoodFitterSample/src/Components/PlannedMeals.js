import React, { Component } from 'react';
import { View, TouchableOpacity, Text, Dimensions, Image, AsyncStorage } from 'react-native';
import { Card } from './common/Card'
import { Actions } from 'react-native-router-flux'
import { CardSection } from './common/CardSection'

class PlannedMeals extends Component{
    createItems(){
        const items = ['March 22 Lunch', 'March 22 Dinner', 'March 21 Dinner'];
        const ItemArray = items.map((item) => 
        <TouchableOpacity onPress={ () => {
            Actions.SavedMeal();
            }}>
        <CardSection style={{ width: Dimensions.get('window').width, height: 50, backgroundColor: '#ced7dc', justifyContent: 'space-between' }}>
                    <Text style={{ color: '#859499', fontSize: 20 }}>{item}</Text>
                    <Text style={{ color: '#859499', fontSize: 20 }}>></Text>
        </CardSection>
        </TouchableOpacity>
    );
        return(
            [...ItemArray]
        );
    }
    render(){
        AsyncStorage.setItem('SampleMeal', JSON.stringify({ "Day" : 'March 22 Lunch', "Items": ['eggs', 'yam', 'apples', 'bubble tea', 'hamburger']}));
        return(
            <View style={{ backgroundColor: '#ced7dc', flex: 1 }}>
            <View style={{ height: 200, alignItems: 'center', justifyContent: 'center' }}>
                <Image source={require('./Images/profilepic.png')} style={{ height: 50, width: 50 }} />
                <Text style={{ color: '#859499', fontSize: 30 }}>Bruno Steban</Text>
                <Text style={{ color: '#859499', fontSize: 20 }}>Male, 22</Text>
            </View>
            <View style={{ flex: 1, flexDirection: 'column', alignItems: 'stretch' }}>
                <CardSection style={{ width: Dimensions.get('window').width, height: 50, backgroundColor: '#859499' }}>
                    <Text style={{ color: 'white', fontSize: 20 }}>Planned Meals</Text>
                </CardSection>
                {this.createItems()}
            <TouchableOpacity onPress={() => Actions.PresetFoods()}>
            <View style={{ marginLeft: 150, marginTop: 20, width: 50, height: 50, borderRadius: 50/2, backgroundColor: '#859499', alignItems: 'center', justifyContent: 'center'}}>
                <Text style={{ marginBottom: 5, color: 'white', fontSize: 50 }}>+</Text>
            </View>
            </TouchableOpacity>
            </View>
            </View>
        );
    }
}

export { PlannedMeals };