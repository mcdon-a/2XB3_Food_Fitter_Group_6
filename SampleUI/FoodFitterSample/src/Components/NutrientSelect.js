import React, { Component } from 'react';
import { AsyncStorage, View, Text, Picker, TouchableOpacity, TextInput } from 'react-native';
import { Actions } from 'react-native-router-flux';
import { Button } from './common/Button'
import Autocomplete from 'react-native-autocomplete-input';

class NutrientSelect extends Component{
    state = { item: null, nutrients: ["Calories", "Protein", "Carbs"], query: '', query1: '', query2: '', nu: null, nu1: null, nu2: null}
    renderNutrients(){
        const nutrients = this.state.nutrients.map((nutrient) => <Picker.Item label={nutrient} value={nutrient} />);
        return(
            [...nutrients]
        );
    }
    render(){
        return(
            <View style={{
                backgroundColor: '#ced7dc',
                flex: 1,
                paddingTop: 25
              }}>
              <Text style={{ backgroundColor: '#ced7dc', fontSize: 25, marginLeft: 20, marginTop: 20, marginBottom: 20, color: '#859499' }}>Enter Up to 3 Nutrients</Text>
              <Picker
                selectedValue={this.state.query}
                onValueChange={(value) => this.setState({query: value})}>
                {this.renderNutrients()}
            </Picker>
            <TextInput
            style={{backgoundColor: 'white',height: 40, borderColor: 'gray', borderWidth: 1}}
            onChangeText={(text) => this.setState({nu: text})}
            value={this.state.nu}
            />
            <Picker
                selectedValue={this.state.query1}
                onValueChange={(value) => this.setState({query1: value})}>
                {this.renderNutrients()}
            </Picker>
            <TextInput
            style={{backgoundColor: 'white',height: 40, borderColor: 'gray', borderWidth: 1}}
            onChangeText={(text) => this.setState({nu1: text})}
            value={this.state.nu1}
            />
            <Picker
                selectedValue={this.state.query2}
                onValueChange={(value) => this.setState({query2: value})}>
                {this.renderNutrients()}
            </Picker>
            <TextInput
            style={{backgoundColor: 'white', height: 40, borderColor: 'gray', borderWidth: 1}}
            onChangeText={(text) => this.setState({nu2: text})}
            value={this.state.nu2}
            />
                <Button onPress={() => {
                    const nutrients = [];
                    const values = [];
                    if (this.state.nu != null && this.state.nu != ''){
                        nutrients.push(this.state.query);
                        values.push(this.state.nu);
                    }
                    if (this.state.nu1 != null && this.state.nu1 != ''){
                        nutrients.push(this.state.query1);
                        values.push(this.state.nu1);
                    }
                    if (this.state.nu2 != null && this.state.nu2 != ''){
                        nutrients.push(this.state.query2);
                        values.push(this.state.nu2);
                    }
                    AsyncStorage.setItem('nutrient', JSON.stringify({'nutrients' : nutrients, 'values' : values}));
                    Actions.SavedMeal()}} style={{ marginTop: 50, width: 350 }}>Next</Button>
            </View>
    
        );
    }
}

export { NutrientSelect };