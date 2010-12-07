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

def testget(l = []):
	s = ",".join(l)
	if s: s = " " + s
	sck.send("GET%s\r\n" % s)
	resp = sck.recv(128)
	#print resp.encode("hex"), "-", resp,
	l = resp.split()
	if l[0] != "OK":
		raise
	if len(l) < 2: return []
	return l[1].split(",")

sck = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
sck.connect(("localhost", 60666))

def test(l1):
	l2 = testget(l1)
	for i in l2:
		print l2
		if not i in l1:
			test(l1 + [i])

test([])
print "All good"
