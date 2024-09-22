#socket server for transaction input
import socket
import time
import random

# Define server details
HOST = '0.0.0.0'  # Bind to all available network interfaces
PORT = 9999       # Port to listen on

def start_server():
    # Create a TCP/IP socket
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as server_socket:
        server_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        server_socket.bind((HOST, PORT))  # Bind the socket to the address and port
        server_socket.listen(5)           # Listen for incoming connections (max 5 queued connections)

        print(f"Server started, listening on port {PORT}")

        while True:
            # Wait for a connection
            client_socket, addr = server_socket.accept()
            print(f"Connection established with {addr}")

            with client_socket:
                count = 0
                while True:
                    try:
                        # Send data to the connected client every second
                        amt = random.randint(1, 100)
                        id = random.randint(1, 10)
                        # message = f"TransactionAmount:100 TransactionId:{id}\n"
                        message = f"{amt} {id}\n"
                        client_socket.sendall(message.encode())
                        print(f"Sent: {message.strip()}")
                        
                        count += 1
                        time.sleep(1)  # Wait for 1 second before sending the next message

                    except (BrokenPipeError, ConnectionResetError) as e:
                        print(f"Connection lost with {addr}: {e}")
                        break  # Exit the loop to wait for a new connection

if __name__ == "__main__":
    start_server()
