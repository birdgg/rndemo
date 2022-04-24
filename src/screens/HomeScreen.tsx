import * as React from 'react'
import {View, Text, StyleSheet} from 'react-native'
import {HomeTabScreenProps} from '../naivgation/types'

export const HomeScreen = ({}: HomeTabScreenProps<'Home'>) => {
  return (
    <View style={styles.container}>
      <Text testID="home">Home Screen</Text>
    </View>
  )
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
})
