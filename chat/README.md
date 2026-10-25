# 🗨️ Java Socket Chat Application

## 📘 Overview
This project is a simple **multi-user chat application** built in **Java** using **socket programming**. It allows multiple clients to connect to a central server, send messages, and receive messages from other users in real-time — all through the command line interface.

The application demonstrates the use of:
- Java I/O (`java.io.*`)
- Networking classes (`java.net.*`)
- Multithreading for concurrent client handling
- Basic client-server architecture

This project contains two main Java files:
1. **ChatServer.java** — manages all client connections and message broadcasting.  
2. **ChatClient.java** — connects users to the server, enabling message sending and receiving.

---

## ⚙️ Features
- Supports **multiple clients** connected to one server.
- Each user is assigned a **unique ID** upon connection.
- Real-time **message broadcasting** between all connected users.
- Automatic handling of **client disconnection**.
- **Multithreaded server** to manage multiple clients simultaneously.
- Simple **command-line interface** for chat interaction.

---

## 🧩 File Structure
