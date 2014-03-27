//
//  Obstacle.m
//  FlappyFly
//
//  Created by Paul Romlow on 3/27/14.
//  Copyright (c) 2014 Apportable. All rights reserved.
//

#import "Obstacle.h"

@implementation Obstacle {
    CCNode *_topPipe;
    CCNode *_bottomPipe;
}

#define ARC4RANDOM_MAX 0x100000000

//visibility on a 3.5-inch iPHone ends at 88 pts
static const CGFloat minimumYPositionTopPipe = 128.0;
//visibility ends at 480
static const CGFloat maximumYPositionBottomPipe = 440.0;
//distance between top and bottom pipe
static const CGFloat pipeDistance = 142.0;
//caclutate the end of the range of top pipe
static const CGFloat maximumYPositionTopPipe = maximumYPositionBottomPipe - pipeDistance;

- (void)didLoadFromCCB
{
    _topPipe.physicsBody.collisionType = @"level";
    _topPipe.physicsBody.sensor = YES;
    _bottomPipe.physicsBody.collisionType = @"level";
    _bottomPipe.physicsBody.sensor = YES;
}

- (void)setupRandomPosition
{
    // value between 0.0 and 1.0
    CGFloat random = ((double) arc4random() / ARC4RANDOM_MAX);
    CCLOG(@"random: %f\n", random);
    CGFloat range = maximumYPositionTopPipe - minimumYPositionTopPipe;
    _topPipe.position = ccp(_topPipe.position.x, minimumYPositionTopPipe + (random * range));
    _bottomPipe.position = ccp(_bottomPipe.position.x, _topPipe.position.y + pipeDistance);
}


@end
