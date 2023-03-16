package main

import "fmt"

func main() {
	fmt.Print("测试")
	server := NewServer("127.0.0.1", 8888)
	server.Start()
}
