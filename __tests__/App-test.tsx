import * as React from 'react'
import {NavigationContainer} from '@react-navigation/native'
import {render, fireEvent} from '@testing-library/react-native'

import App from '../App'

describe('Testing react navigation', () => {
  test('find home screen text', async () => {
    const component = <App />

    const {findByText} = render(component)

    const header = await findByText('Home Screen')

    expect(header).toBeTruthy()
  })
})
