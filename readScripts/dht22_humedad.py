import pigpio
from time import sleep
# this connects to the pigpio daemon which must be started first
pi = pigpio.pi()
# Pigpio DHT22 module should be in same folder as your program
import DHT22 
s = DHT22.sensor(pi, 5)
s.trigger()
sleep(.03) # Necessary on faster Raspberry Pi's
#print('{:3.2f}'.format(s.humidity() / 1.)$'{:3.2f}'.format(s.temperature() / 1.))
valor = '{0:0.1f}'.format(s.humidity() / 1.)
if valor == '-999.0':
	print('read_error')
else:
	print(valor)
s.cancel()
pi.stop()