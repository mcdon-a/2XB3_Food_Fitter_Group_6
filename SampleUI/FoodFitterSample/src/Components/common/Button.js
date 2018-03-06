import React from 'react';
import { Text, TouchableOpacity } from 'react-native';

const Button = ({ onPress, children, style, textsStyle }) => {
  const { buttonStyle, textStyle } = styles;

  return (
    <TouchableOpacity onPress={onPress} style={[buttonStyle, style]}>
      <Text style={[textStyle, textsStyle]}>
        {children}
      </Text>
    </TouchableOpacity>
  );
};

const styles = {
  textStyle: {
    alignSelf: 'center',
    justifyContent: 'center',
    color: '#7ba4d9',
    fontSize: 27, //27 for android
    fontWeight: '100',
    paddingTop: 10, //Issues with padding for ios. Is 10 for android
    paddingBottom: 10,
    alignItems: 'flex-end'
  },
  buttonStyle: {
    flex: 1,
    backgroundColor: 'white',
    alignSelf: 'stretch',
    borderRadius: 10,
    borderWidth: 1,
    borderColor: '#7ba4d9',
    marginLeft: 5,
    marginRight: 5,
    alignItems: 'center',
    justifyContent: 'center', 
    flexWrap: 'wrap',
    height: 500
  }
};

export { Button };