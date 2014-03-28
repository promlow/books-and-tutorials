//
//  Goal.m
//  FlappyFly
//
//  Created by Paul Romlow on 3/28/14.
//  Copyright (c) 2014 Apportable. All rights reserved.
//

#import "Goal.h"

@implementation Goal

- (void)didLoadFromCCB
{
    self.physicsBody.collisionType = @"goal";
    self.physicsBody.sensor = YES;
}

@end
