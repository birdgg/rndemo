import * as React from 'react'
import {createNativeStackNavigator} from '@react-navigation/native-stack'
import {DetailsScreen} from '../screens/DetailScreen'
import {HomeTab} from './HomeTab'

const Stack = createNativeStackNavigator()

export const RootStacks = () => {
  return (
    <Stack.Navigator>
      <Stack.Screen name="Tab" component={HomeTab} />
      <Stack.Screen name="Detail" component={DetailsScreen} />
    </Stack.Navigator>
  )
}
