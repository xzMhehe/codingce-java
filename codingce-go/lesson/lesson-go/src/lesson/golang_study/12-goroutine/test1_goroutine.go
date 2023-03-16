package main

import (
	"fmt"
	"time"
)

//子goroutine
func newTask() {
	i := 0
	for {
		i++
		fmt.Printf("new Goroutine : i = %d\n", i)
		time.Sleep(1 * time.Second)
	}
}

//主goroutine
func main() {
	//创建一个go程 去执行newTask() 流程
	go newTask()

	fmt.Println("main goroutine exit")

	// 如果main推出的话其他goroutine都会死掉

	//i := 0
	//for {
	//	i++
	//	fmt.Printf("main goroutine: i = %d\n", i)
	//	time.Sleep(1 * time.Second)
	//}
}
