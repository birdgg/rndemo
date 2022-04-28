describe('App', () => {
  beforeAll(async () => {
    await device.launchApp({newInstance: false})
  })

  // beforeEach(async () => {
  //   await device.reloadReactNative()
  // })

  it('should show the hello message', async () => {
    await expect(element(by.id('home'))).toBeVisible()
  })

  it('show go to setting tab', async () => {
    await element(by.id('settingsTab')).tap()
    await expect(element(by.text('Settings!'))).toBeVisible()
  })
})
