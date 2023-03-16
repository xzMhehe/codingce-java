package main

import "fmt"

func main() {
	fmt.Println("lesson07 函数")
	value1 := 290.1
	value2 := kelvinToCelsius(value1)

	fmt.Println(value1, "°K is", value2, "°C")

	sum := addAll(3, 4, 5, 6, 7)
	fmt.Println(sum)

	foo1("111", 7)

	ma, mb := foo2("1", 2)
	fmt.Println("ma = ", ma, " mb = ", mb)

	mr1, mr2 := foo3("1", 2)
	fmt.Println("mr1 = ", mr1, " mr2 = ", mr2)
}

// kelvinToCelsius converts °K to °C
func kelvinToCelsius(k float64) float64 {
	k -= 273.15
	return k
}

// addAll 多数相加
func addAll(a int, numbers ...int) int {
	sum := a
	for _, v := range numbers {
		sum += v
	}
	return sum
}

func foo1(a string, b int) int {
	fmt.Println("a = ", a)
	fmt.Println("b = ", b)
	c := 100
	return c
}

// 返回多个返回值匿名的
func foo2(a string, b int) (int, int) {
	fmt.Println("a = ", a)
	fmt.Println("b = ", b)
	c := 100
	return c, 77
}

// 返回多个返回值有形参的
func foo3(a string, b int) (r1 int, r2 int) {
	r1 = 100
	r2 = 200
	return
}

func foo4(a string, b int) (r1, r2 int) {
	r1 = 100
	r2 = 200
	return
}
