import React, { Component } from 'react';
import { View, Text, TextInput } from 'react-native';
import { Button } from './common/Button';

class MainMenu extends Component{
    state = { list: null, text: 'Enter Food', fulltext: null }

    componentWillMount(){
        this._promptDataAsync();
        //this._fetchDataAsync();
    }
    async _promptDataAsync(){
        try{
            let sendData = fetch('http://172.17.39.218:8000/posthandler', {
                method: 'POST',
                headers: {
                  'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                  name: 'Alexie',
                  age: '19',
                }),
            });
            let response = await fetch('http://172.17.39.218:8000/posthandler');
            let reponseJson = await response.json();
            this.setState({ fulltext: JSON.stringify(responseJson)});

        } catch(error){
            console.log(error);
        }
    }
    async _fetchDataAsync() {
        try {
          let response = await fetch('http://172.17.39.218:8000/jsonFile.json');
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