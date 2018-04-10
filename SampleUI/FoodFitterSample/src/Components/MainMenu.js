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
            let sendData = fetch('https://192.168.2.19:9999/hello', {
                method: 'POST',
                headers: {
                  'Accept':       'application/json',
                  'Content-Type': 'application/json',
                }, 
                body: JSON.stringify({
                  cal : '700',
                  prot: '19'
                }),
              }).then((response) => {console.log(response) }).catch((error) => console.log(error));
            //let response = await fetch('http://192.168.2.19:9999/testPost/sayhello');
            //let reponseJson = await response;
            //this.setState({ fulltext: response.toString()});

        } catch(error){
            console.log(error);
        }
    }
    async _fetchDataAsync() {
        try {
          let response = await fetch('http://172.17.23.168:9999/hello/sayhello');
          let responseJson = await response.text();
          console.log(responseJson);
          this.setState({fulltext: response});
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
            </View>
        );
    }
}

export { MainMenu };