package main

import "fmt"

type Phone interface {
	call()
}

type Huawei struct{}

func (huawei *Huawei) call() {
	fmt.Println("I am HuaWei")
}

type Iphone struct{}

func (iphone *Iphone) call() {
	fmt.Println("I am Iphone")
}

func main() {
	var phone Phone
	phone = new(Huawei)
	phone.call()
}
