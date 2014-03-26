//
//  WaitingPenguin.m
//  PeevedPenguins
//
//  Created by Paul Romlow on 3/25/14.
//  Copyright (c) 2014 Apportable. All rights reserved.
//

#import "WaitingPenguin.h"

@implementation WaitingPenguin

- (void)didLoadFromCCB
{
    float delay = (arc4random() % 2000) / 1000.0;
    [self performSelector:@selector(startBlinkAndJump) withObject:nil afterDelay:delay];
}

- (void)startBlinkAndJump
{
    CCBAnimationManager *animationManager = self.userObject;
    [animationManager runAnimationsForSequenceNamed:@"Blink and Jump" tweenDuration:0.2];
}

@end
