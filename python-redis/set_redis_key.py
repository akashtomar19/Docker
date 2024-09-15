import redis

# Connect to the Redis container using its container name or IP
r = redis.Redis(host='redis', port=6379, db=0)

try:
    # Set a key in Redis
    r.set('example_key', 'Hello from Dockerized Python!')

    # Retrieve and print the key to confirm it's set correctly
    value = r.get('example_key')
    print(f"example_key: {value.decode('utf-8')}")
except redis.exceptions.ConnectionError as e:
    print(f"Could not connect to Redis: {e}")
