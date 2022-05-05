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

  test('setting tab click', async () => {
    const component = <App />

    const {findByText, findByTestId} = render(component)
    const toClick = await findByTestId('settingsTab')

    fireEvent(toClick, 'press')
    const settings = await findByText('Settings!')

    expect(settings).toBeTruthy()
  })
})
