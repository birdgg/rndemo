import * as React from 'react'
import {View, Text, StyleSheet, requireNativeComponent} from 'react-native'
import {HomeTabScreenProps} from '../naivgation/types'
import WebView from 'react-native-webview'

const SteamWebView = requireNativeComponent('SteamWebView')

export const HomeScreen = ({}: HomeTabScreenProps<'Home'>) => {
  return <SteamWebView url="https://www.baidu.com" style={{flex: 1}} />
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
})
