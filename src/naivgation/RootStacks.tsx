import * as React from 'react'
import {createNativeStackNavigator} from '@react-navigation/native-stack'
import {DetailsScreen} from '../screens/DetailScreen'
import {HomeTab} from './HomeTab'
import {SwipeableList} from '../screens/SwipeableList'
import {RootStackParamList} from './types'

const Stack = createNativeStackNavigator<RootStackParamList>()

export const RootStacks = () => {
  return (
    <Stack.Navigator>
      <Stack.Screen
        name="Tab"
        component={HomeTab}
        options={{headerShown: false}}
      />
      <Stack.Screen name="Detail" component={DetailsScreen} />
      <Stack.Screen name="SwipeableList" component={SwipeableList} />
    </Stack.Navigator>
  )
}
