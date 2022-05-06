import * as React from 'react'
import {Button, Text, View} from 'react-native'
import {createBottomTabNavigator} from '@react-navigation/bottom-tabs'
import {HomeScreen} from '../screens/HomeScreen'
import {HomeTabScreenProps} from './types'

function SettingsScreen({navigation}: HomeTabScreenProps<'Home'>) {
  const onPress = () => {
    navigation.push('Detail', {id: '1'})
  }

  return (
    <View style={{flex: 1, justifyContent: 'center', alignItems: 'center'}}>
      <Text>Settings!</Text>
      <Button title="go to detail" onPress={onPress} />
    </View>
  )
}

const Tab = createBottomTabNavigator()

export function HomeTab() {
  return (
    <Tab.Navigator screenOptions={{headerShown: false}}>
      <Tab.Screen
        name="Home"
        component={HomeScreen}
        options={{tabBarTestID: 'homeTab'}}
      />
      <Tab.Screen
        name="Settings"
        component={SettingsScreen}
        options={{tabBarTestID: 'settingsTab'}}
      />
    </Tab.Navigator>
  )
}
