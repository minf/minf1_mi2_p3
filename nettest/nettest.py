#!/usr/bin/env python
# -*- coding: iso-8859-15 -*-

"""
Option ID: aaa-ggg:Ascii-Strings, bel. Länge

tcp, 60666, ASCII

GET\r\n
GET aaa,bbb,cbb,dbb\r\n
ERR 1..X\r\n
OK eee,fff,ggg\r\n
"""

import socket
import signal
from threading import Thread

bRun = True

class test(Thread):
	def __init__(self, i):
		Thread.__init__(self)	
		self.sck = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
		self.sck.connect(("localhost", 60666))
		self.id = i
		self.start()

	def testget(self, l = []):
		s = ",".join(l)
		if s: s = " " + s
		self.sck.send("GET%s\r\n" % s)
		resp = self.sck.recv(4096)
		#print resp.encode("hex"), "-", resp,
		l = resp.split()
		if l[0] != "OK":
			raise
		if len(l) < 2: return []
		return l[1].split(",")

	def testrun(self, l1):
		l2 = self.testget(l1)
		for i in l2:
			if not bRun:
				break
			#print l2
			if not i in l1:
				self.testrun(l1 + [i])

	def run(self):
		self.testrun([])
		print self.id, "All good"


def signal_sigint(signal, frame):
	global bRun
	bRun = False

for i in range(3): # parallel tasks
	test(i)

signal.signal(signal.SIGINT, signal_sigint)
signal.pause()

