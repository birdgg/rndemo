import React from 'react'
import {NavigationContainer} from '@react-navigation/native'
import {RootStacks} from './src/naivgation/RootStacks'

const App = props => {
  console.log('get props from native', props)
  return (
    <NavigationContainer>
      <RootStacks />
    </NavigationContainer>
  )
}

export default App
