import * as React from 'react'
import {View, Text} from 'react-native'
import {RootStackScreenProps} from '../naivgation/types'

export function DetailsScreen({}: RootStackScreenProps<'Detail'>) {
  return (
    <View style={{flex: 1, alignItems: 'center', justifyContent: 'center'}}>
      <Text>Details Screen</Text>
    </View>
  )
}
