import React, { Component } from 'react';
import { View, Text, TextInput } from 'react-native';
import { Button } from './common/Button';

class MainMenu extends Component{
    state = { list: null, text: 'Enter Food', fulltext: null }

    componentWillMount(){
        this._fetchDataAsync();
    }
    async _fetchDataAsync() {
        try {
          let response = await fetch('http://172.17.118.127:8000/jsonFile.json');
          let responseJson = await response.json();
          this.setState({fulltext: JSON.stringify(responseJson)});
        } catch(error) {
          console.error(error);
        }
        console.log("Async done")
      }
    render(){
        return(
            <View>
                <Text>Welcome to FoodFitter!</Text>
                <TextInput 
                style={{height: 70, borderColor: 'gray', borderWidth: 1}}
                onChangeText={(text) => this.setState({text})}
                value={this.state.text} 
                />
                <View style={{height: 100, width: 300, alignSelf: 'center'}}>
                    <Button onPress={() => console.log("Wee!")}>Enter</Button>
                </View>
                <Text>{this.state.fulltext}</Text>
            </View>
        );
    }
}

export { MainMenu };