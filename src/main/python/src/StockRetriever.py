import json

import websocket
import os

from websocket import WebSocketApp


def on_open(ws: WebSocketApp, stock: str):
    print("Connection open")

    data = {
        "token": f"{os.getenv("KEY")}"
    }

    ws.send(json.dumps(data))
    ws.send('{"type":"subscribe","symbol":' + stock + '"}')


def on_message(ws, message):
    message = json.loads(message)
    print(message)


def on_close(ws):
    print("Closed connection")


def get_data(stock: str) -> None:
    """
    Gets data about a stock
    """

    ws = "wss://ws.finnhub.io"
    socket = websocket.WebSocketApp(ws)

    socket.on_open = on_open(socket, stock)
    socket.on_message = on_message
    socket.on_close = on_close

    socket.run_forever()

get_data("AAPL")