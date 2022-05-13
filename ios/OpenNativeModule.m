//
//  OpenNativeModule.m
//  rndemo
//
//  Created by birdgg on 2022/05/13.
//

#import "OpenNativeModule.h"
#import "AppDelegate.h"
#import "NativeViewController.h"

@implementation OpenNativeModule

// 导出 rn module
RCT_EXPORT_MODULE();

// 导出方法给 js 调用
RCT_EXPORT_METHOD(openNativeVC) {
  // 在主线程操作
  dispatch_async(dispatch_get_main_queue(), ^{
    AppDelegate *app= (AppDelegate *)[[UIApplication sharedApplication] delegate];
    NativeViewController *nativeVC = [[NativeViewController alloc] init];
    [app.navi pushViewController:nativeVC animated:YES];
  });
}


//RCT_EXPORT_METHOD(pop) {
//    AppDelegate *app= (AppDelegate *)[[UIApplication sharedApplication] delegate];
//    [app.navi popViewControllerAnimated:YES];
//}

@end
