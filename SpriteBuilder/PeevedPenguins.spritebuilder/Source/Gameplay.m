//
//  Gameplay.m
//  PeevedPenguins
//
//  Created by Paul Romlow on 3/12/14.
//  Copyright (c) 2014 Apportable. All rights reserved.
//

#import "Gameplay.h"
#import "Penguin.h"

static const float MIN_SPEED = 5.0;

@implementation Gameplay
{
    CCPhysicsNode *_physicsNode;
    CCAction *_followPenguin;
    CCNode *_contentNode;
    
    CCNode *_catapult;
    CCNode *_catapultArm;
    CCNode *_pullbackNode;
    
    CCNode *_levelNode;
    
    CCPhysicsJoint *_catapultJoint;
    CCPhysicsJoint *_pullbackJoint;
    
    CCNode *_mouseJointNode;
    CCPhysicsJoint *_mouseJoint;
    
    Penguin *_currentPenguin;
    CCPhysicsJoint *_penguinCatapultJoint;

}
// is called when CCB file has completed loading
- (void)didLoadFromCCB
{
    // tell this scene to accept touches
    self.userInteractionEnabled = TRUE;
    CCScene *level = [CCBReader loadAsScene:@"Levels/Level1"];
    [_levelNode addChild:level];
    
    _physicsNode.debugDraw = YES;
    [_catapultArm.physicsBody setCollisionGroup:_catapult];
    [_catapult.physicsBody setCollisionGroup:_catapult];
    
    _catapultJoint = [CCPhysicsJoint connectedPivotJointWithBodyA:_catapultArm.physicsBody bodyB:_catapult.physicsBody anchorA:_catapultArm.anchorPointInPoints];
    
    // nothing shall collide with our invisible node
    _pullbackNode.physicsBody.collisionMask = @[];
    // create a spring joint for bringing arm in upright position and snapping
    // back when player shoots
    _pullbackJoint = [CCPhysicsJoint connectedSpringJointWithBodyA:_pullbackNode.physicsBody
                                                             bodyB:_catapultArm.physicsBody
                                                           anchorA:ccp(0,0) anchorB:ccp(34,138)
                                                        restLength:60.0 stiffness:500.0 damping:40.0];
    
    _mouseJointNode.physicsBody.collisionMask = @[];
    _physicsNode.collisionDelegate = self;
}

- (void)update:(CCTime)delta
{
    if (_currentPenguin.launched) {
        // if speed is below minimum, assume this attempt is over
        if (ccpLength(_currentPenguin.physicsBody.velocity) < MIN_SPEED)
        {
            [self nextAttempt];
            return;
        }
    
        int xMin = _currentPenguin.boundingBox.origin.x;
    
        if (xMin < self.boundingBox.origin.x)
        {
            [self nextAttempt];
            return;
        }
    
        int xMax = xMin + _currentPenguin.boundingBox.size.width;
    
        if (xMax > (self.boundingBox.origin.x + self.boundingBox.size.width))
        {
            [self nextAttempt];
            return;
        }
    }
}

// called on every touch in this scene
- (void)touchBegan:(UITouch *)touch withEvent:(UIEvent *)event
{
    CGPoint touchLocation = [touch locationInNode:_contentNode];
    
    // start catapult dragging when a touch inside the catapult arm occurs
    if (CGRectContainsPoint([_catapultArm boundingBox], touchLocation))
    {
        // move mouseJointNode to touch position
        _mouseJointNode.position =  touchLocation;
        
        // set up a spring joint between mouseJointNode and catapultArm
        _mouseJoint = [CCPhysicsJoint connectedSpringJointWithBodyA:_mouseJointNode.physicsBody
                                                              bodyB:_catapultArm.physicsBody
                                                            anchorA:ccp(0,0) anchorB:ccp(34,138)
                                                         restLength: 0.0 stiffness:3000.0
                                                            damping:150.0];
        
        // create the penguin
        _currentPenguin = (Penguin*)[CCBReader load:@"Penguin"];
        // position it in the scoop. 34,138 is the position in the node space of the catapult arm
        CGPoint penguinPosition = [_catapultArm convertToWorldSpace:ccp(34, 138)];
        // transfrom the world position to the node space wherein the penguin will be added
        _currentPenguin.position = [_physicsNode convertToNodeSpace:penguinPosition];
        // add it to the physics wold
        [_physicsNode addChild:_currentPenguin];
        // don't allow penguin to rotate in the scoop
        _currentPenguin.physicsBody.allowsRotation = NO;
        
        // create a joint to keep the penguin fixed to the scoop until the catapult is released
        _penguinCatapultJoint = [CCPhysicsJoint connectedPivotJointWithBodyA:_currentPenguin.physicsBody
                                                                     bodyB:_catapultArm.physicsBody
                                                                     anchorA:_currentPenguin.anchorPointInPoints];
        
    }
}

- (void)touchMoved:(UITouch *)touch withEvent:(UIEvent *)event
{
    // update the position of mouseJointNode to the touch position
    CGPoint touchLocation = [touch locationInNode:_contentNode];
    _mouseJointNode.position = touchLocation;
}

- (void)touchEnded:(UITouch *)touch withEvent:(UIEvent *)event
{
    [self releaseCatapult];
}

- (void)touchCancelled:(UITouch *)touch withEvent:(UIEvent *)event
{
    [self releaseCatapult];
}

-(void)ccPhysicsCollisionPostSolve:(CCPhysicsCollisionPair *)pair seal:(CCNode *)nodeA wildcard:(CCNode *)nodeB
{
    float energy = [pair totalKineticEnergy];
    
    if (energy > 5000.0)
    {
        [self sealRemoved:nodeA];
    }
}

- (void)sealRemoved:(CCNode*)seal
{
    CCParticleSystem *explosion = (CCParticleSystem*) [CCBReader load:@"SealExplosion"];
    explosion.autoRemoveOnFinish = YES;
    explosion.position = seal.position;
    [seal.parent addChild:explosion];
    
    [seal removeFromParent];
}

- (void)releaseCatapult {
    if (_mouseJoint != nil)
    {
        // release the joint and let catapult snap back
        [_mouseJoint invalidate];
        _mouseJoint = nil;
        
        [_penguinCatapultJoint invalidate];
        _penguinCatapultJoint = nil;
        
        // rotation is OK after launch
        _currentPenguin.physicsBody.allowsRotation = YES;
        
        // follow the flying penguin
        _followPenguin = [CCActionFollow actionWithTarget:_currentPenguin worldBoundary:self.boundingBox];
        [_contentNode runAction:_followPenguin];
        _currentPenguin.launched = YES;
    }
}

//- (void)launchPenguin
//{
//    // loads the Penguin.ccb we have set up in Spritebuilder
//    CCNode* penguin = [CCBReader load:@"Penguin"];
//    // position the penguin at the bowl of the catapult
//    penguin.position = ccpAdd(_catapultArm.position, ccp(16, 50));
//    
//    // add the penguin to the physicsNode of this scene (because it has physics enabled)
//    [_physicsNode addChild:penguin];
//    
//    // manually create & apply a force to launch the penguin
//    CGPoint launchDirection = ccp(1, 0);
//    CGPoint force = ccpMult(launchDirection, 8000);
//    [penguin.physicsBody applyForce:force];
//    // ensure followed object is in visible area when starting
//    self.position = ccp(0,0);
//    CCActionFollow *follow = [CCActionFollow actionWithTarget:penguin worldBoundary:self.boundingBox];
//    [_contentNode runAction:follow];
//    
//}

- (void)nextAttempt {
    _currentPenguin = nil;
    [_contentNode stopAction:_followPenguin];
    
    CCActionMoveTo *actionMoveTo = [CCActionMoveTo actionWithDuration:1.0 position:ccp(0, 0)];
    [_contentNode runAction:actionMoveTo];
}

- (void)retry
{
    // reload this level
    [[CCDirector sharedDirector] replaceScene: [CCBReader loadAsScene:@"Gameplay"]];
}

@end
