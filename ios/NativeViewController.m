//
//  NativeViewController.m
//  rndemo
//
//  Created by birdgg on 2022/05/13.
//

#import "NativeViewController.h"

@interface NativeViewController ()

@end

@implementation NativeViewController

- (void)viewDidLoad {
    [super viewDidLoad];
  [self.view addSubview:self.textLabel];
    self.view.backgroundColor = UIColor.whiteColor;
    // Do any additional setup after loading the view.
}

-(UILabel *)textLabel {
  UILabel *nooLabel = [[UILabel alloc] init];
  nooLabel.text = [NSString stringWithFormat:@"123123"];
  nooLabel.frame = CGRectMake(75, 75, 100, 50);
  nooLabel.textColor = [UIColor blackColor];
  return nooLabel;
}
/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
