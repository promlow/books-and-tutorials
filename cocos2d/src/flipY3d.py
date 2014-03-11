import cocos
import random
from cocos.actions import *

class HelloWorld(cocos.layer.ColorLayer):
    def __init__(self):
        #blueish color
        super(HelloWorld, self).__init__(64,64,224,255)
        sprite = cocos.sprite.Sprite('A.png')
        sprite.position = 320,240
        sprite.scale = 1
        self.add(sprite, z=1)
        flipy = FlipY3D()
        sprite.do( Repeat (flipy) )

if __name__ == '__main__':
    cocos.director.director.init()
    hello_layer = HelloWorld()
    main_scene = cocos.scene.Scene(hello_layer)
    cocos.director.director.run(main_scene)
