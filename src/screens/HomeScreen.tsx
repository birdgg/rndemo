import * as React from 'react'
import {
  View,
  Text,
  StyleSheet,
  requireNativeComponent,
  Button,
  NativeModules,
} from 'react-native'
import {SafeAreaView} from 'react-native-safe-area-context'
import {HomeTabScreenProps} from '../naivgation/types'

const OpenNativeModule = NativeModules.OpenNativeModule

export const HomeScreen = ({navigation}: HomeTabScreenProps<'Home'>) => {
  const openNativePage = () => {
    OpenNativeModule.openNativeVC()
  }
  return (
    <SafeAreaView style={{flex: 1}}>
      <Text>这是首页</Text>
      <Button title="打开 native 页面" onPress={openNativePage} />
      <Button
        onPress={() => navigation.push('SwipeableList')}
        title="swipeable list"
      />
    </SafeAreaView>
  )
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
})
