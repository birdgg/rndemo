import React from 'react'
import {NavigationContainer} from '@react-navigation/native'
import {RootStacks} from './src/naivgation/RootStacks'

const App = () => {
  return (
    <NavigationContainer>
      <RootStacks />
    </NavigationContainer>
  )
}

export default App
