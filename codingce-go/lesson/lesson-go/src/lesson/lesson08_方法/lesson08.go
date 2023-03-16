package main

import "fmt"

func main() {

	type codingce float64

	var temperature codingce = 64
	fmt.Println(temperature)

	const degress = 20
	var temperature2 codingce = degress

	temperature2 += 2
	fmt.Println(temperature2)

	var warmUp float64 = 29
	//temperature2 += warmUp 报错

	fmt.Println(warmUp)

	type fahrenheit float64

	var ceshi1 fahrenheit = 64

	var ceshi2 codingce = 64

	//if ceshi1 == ceshi2 {
	// 报错
	//}

	//ceshi1 += ceshi2 报错

	fmt.Println(ceshi1, ceshi2)

	// 引入新类型函数
	var k codingce1 = 266.1
	c := kelvinToCelsius(k)

	fmt.Println(c)

	var m codingce1 = 294.0
	x := k.codingce2()
	fmt.Println(m, "°K is", x, "°C") //294 °K is 20.850000000000023 °C

}

type codingce1 float64
type codingce2 float64

func kelvinToCelsius(k codingce1) codingce2 {
	return codingce2(k - 100) // 需要使用类型转换
}

//为kelvin类型关联一个方法celsius
func (k codingce1) codingce2() codingce2 {
	return codingce2(k - 273.15)
}
