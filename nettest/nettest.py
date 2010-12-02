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


sck = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
sck.connect(("localhost", 60666))
sck.send("GET\r\n")
