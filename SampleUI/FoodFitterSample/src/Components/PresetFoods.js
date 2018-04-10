import React, { Component } from 'react';
import { AsyncStorage, View, Text, Picker, TouchableOpacity } from 'react-native';
import { Actions } from 'react-native-router-flux';
import { Button } from './common/Button'
import Autocomplete from 'react-native-autocomplete-input';

class PresetFoods extends Component{
    state = { item: null, foods: ["Chicken leg, stewed", "Chicken leg, fried", "Cheese souffle", "Cereal, Reese Puffs (Peanut butter)", "Melon, cantaloupe"], query: '', query1: '', query2: ''}
    findFood(query){
        if (query === ''){
            return []
        }
        return this.state.foods.filter(food => food.search(new RegExp(query)) >= 0);
    }
    render(){
        const foodz = this.findFood(this.state.query);
        const foodz1 = this.findFood(this.state.query1);
        const foodz2 = this.findFood(this.state.query2);
        return(
            <View style={{
                backgroundColor: '#ced7dc',
                flex: 1,
                paddingTop: 25
              }}>
              <Text style={{ backgroundColor: '#ced7dc', fontSize: 25, marginLeft: 20, marginTop: 20, marginBottom: 20, color: '#859499' }}>Enter Up to 2 Preset Foods</Text>
                <Autocomplete
                autoCapitalize="none"
                autoCorrect={false}
                containerStyle={{
                    width: 300,
                    left: 20,
                    position: 'absolute',
                    right: 0,
                    top: 100,
                    zIndex: 2
                  }}
                data={foodz}
                defaultValue={this.state.query}
                onChangeText={text => 
                    this.setState({ query: text })}
                placeholder="Enter Food"
                renderItem={(item) => (
                    <TouchableOpacity onPress={() => this.setState({ query: item })}>
                  <Text style={{
        fontSize: 15,
        margin: 2
      }}>
                    {item}
                  </Text>
                </TouchableOpacity>
                )} />
                <Autocomplete
                autoCapitalize="none"
                autoCorrect={false}
                containerStyle={{
                    width: 300,
                    left: 20,
                    position: 'absolute',
                    right: 0,
                    top: 150,
                    zIndex: 1
                  }}
                data={foodz1}
                defaultValue={this.state.query1}
                onChangeText={text => 
                    this.setState({ query1: text })}
                placeholder="Enter Food"
                renderItem={(item) => (
                    <TouchableOpacity onPress={() => this.setState({ query1: item })}>
                  <Text style={{
        fontSize: 15,
        margin: 2
      }}>
                    {item}
                  </Text>
                </TouchableOpacity>
                )} />
                <Button onPress={() => {
                    const presets = [];
                    if (this.state.query != null && this.state.query != ''){
                        presets.push(this.state.query);
                    }
                    if (this.state.query1 != null && this.state.query1 != ''){
                        presets.push(this.state.query1);
                    }
                    AsyncStorage.setItem('presets', JSON.stringify({'presets' : presets}));
                    Actions.NutrientSelect()}} style={{ marginTop: 150, width: 350 }}>Next</Button>
            </View>
    
        );
    }
}

export { PresetFoods };