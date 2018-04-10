import React from 'react';
import { View } from 'react-native';

const Card = (props) => (
        <View style={[styles.containerStyle, props.style]}>
            {props.children}
        </View>
    );

const styles = {
    containerStyle: {
        borderWidth: 1,
        borderRadius: 2,
        borderColor: '#ddd',
        borderBottomWidth: 0,
        elevation: 2,
        marginLeft: 5,
        marginRight: 5,
        marginTop: 60
    }
};
export { Card };
