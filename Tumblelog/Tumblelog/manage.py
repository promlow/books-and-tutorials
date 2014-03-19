#Set the path
import os, sys
sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))

from flask.ext.script import Manager, Server
from tumblelog import app

manager = Manager(app)

#Turn on debugger and reloader by default
manager.add_command("runserver", Server(
    use_debugger = True,
    use_reloader = True,
    host = '127.0.0.1') #tutorial says 0.0.0.0, but I don't want to bind to public IP
)

if __name__ == '__main__':
    manager.run()

