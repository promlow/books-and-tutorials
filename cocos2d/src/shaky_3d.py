import cocos
import random
from cocos.actions import *

class Shaky3D(Grid3DAction):
    def init(self, randrange=6, *args, **kw):
        super(Shaky3D, self).init(*args, **kw)
        self.randrange = randrange

    def update(self, t):
        for i in xrange(0, self.grid.x+1):
            for j in xrange(0, self.grid.y+1):
                x,y,z = self.get_original_vertex(i,j)
                x += random.randrange( -self.randrange, self.randrange+1 )
                y += random.randrange( -self.randrange, self.randrange+1 )
                z += random.randrange( -self.randrange, self.randrange+1 )

                self.set_vertex( i,j, (x,y,z) )

class HelloWorld(cocos.layer.ColorLayer):
    def __init__(self):
        #blueish color
        super(HelloWorld, self).__init__(64,64,224,255)
        sprite = cocos.sprite.Sprite('A.png')
        sprite.position = 320,240
        sprite.scale = 1
        self.add(sprite, z=1)
        shaky = Shaky3D()
        sprite.do( Repeat (shaky) )

if __name__ == '__main__':
    cocos.director.director.init()
    hello_layer = HelloWorld()
    main_scene = cocos.scene.Scene(hello_layer)
    cocos.director.director.run(main_scene)
