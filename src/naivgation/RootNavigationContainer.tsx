import * as React from 'react'
import {NavigationContainer} from '@react-navigation/native'

export const RootNavigationContainer = ({
  children,
}: {
  children: React.ReactNode
}) => {
  return <NavigationContainer>{children}</NavigationContainer>
}
