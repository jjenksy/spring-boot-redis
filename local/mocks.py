import os
import logging
import json as jsn
import requests as reqs
import random
import string

logger = logging.getLogger('turq')
logger.setLevel(logging.DEBUG)

def generate_id(number):
    chars = random.sample(string.ascii_uppercase + string.digits, number)
    id = ''.join(map(str, chars))
    return id

def log(message):
    logging.getLogger('turq').debug("[DEBUG] %s" % message)

# ----------------------------------------------------------------------------------------
# JUST FOR TEST PURPOSE. REMOVE THIS WHEN ADD REAL MOCKS
# ----------------------------------------------------------------------------------------
if route('/test') and GET:
    json({"message": "OK"})
if route('/user') and GET:
    text(generate_id(6))