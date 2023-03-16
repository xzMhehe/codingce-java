package main

import "fmt"

func fibonacii(c, quit chan int) {
	defer fmt.Println("方法执行结束")
	x, y := 1, 1

	for {
		select {
		case c <- x:
			//如果c可写，则该case就会进来
			x = y
			y = x + y
		case <-quit:
			fmt.Println("quit")
			return
		}
	}
}

func main() {
	defer fmt.Println("主进程执行结束")

	c := make(chan int)
	quit := make(chan int)

	//sub go
	go func() {
		for i := 0; i < 10; i++ {
			fmt.Println(<-c)
		}

		quit <- 0
	}()

	//main go
	fibonacii(c, quit)
}
