//
//  NativeViewController.m
//  rndemo
//
//  Created by birdgg on 2022/05/13.
//

#import "NativeViewController.h"
#import "RNViewController.h"
#import <React/RCTBridge.h>
#import <React/RCTBundleURLProvider.h>
#import <React/RCTRootView.h>


@interface NativeViewController ()

@end

@implementation NativeViewController

- (void)viewDidLoad {
    [super viewDidLoad];
  [self.view addSubview:self.textLabel];
  [self.view addSubview:self.btn];
    self.view.backgroundColor = UIColor.whiteColor;
    // Do any additional setup after loading the view.
}

-(UILabel *)textLabel {
  UILabel *nooLabel = [[UILabel alloc] init];
  nooLabel.text = [NSString stringWithFormat:@"这是原生页面"];
  nooLabel.frame = CGRectMake(75, 75, 100, 50);
  nooLabel.textColor = [UIColor blackColor];
  return nooLabel;
}

-(UIButton *)btn {
  UIButton  *btn = [UIButton buttonWithType:UIButtonTypeRoundedRect];
  btn.frame = CGRectMake(75, 100, 75, 200);
  [btn setTitle:@"跳rn" forState:UIControlStateNormal];
  [btn addTarget:self action:@selector(btnSelected:)
                    forControlEvents:UIControlEventTouchUpInside];
  [btn.titleLabel setFont:[UIFont boldSystemFontOfSize:20.0]];
  return btn;
}

- (void)btnSelected:(UIButton*)sender{
  NSURL *jsCodeLocation = [NSURL URLWithString:@"http://localhost:8081/index.bundle?platform=ios"];
  RCTRootView *rootView =
    [[RCTRootView alloc] initWithBundleURL: jsCodeLocation
                                moduleName: @"rndemo"
                         initialProperties:
                           @{
                             @"scores" : @[
                               @{
                                 @"name" : @"Alex",
                                 @"value": @"42"
                                },
                               @{
                                 @"name" : @"Joel",
                                 @"value": @"10"
                               }
                             ]
                           }
                             launchOptions: nil];
  UIViewController *vc = [[UIViewController alloc] init];
  vc.view = rootView;
  [self presentViewController:vc animated:YES completion:nil];
}


@end
