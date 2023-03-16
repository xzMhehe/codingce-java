package main

import (
	"fmt"
	"math"
)

// 声明coordinate类型
// 三维点坐标
type coordinate struct {
	x, y, z float64
}

// 为声明的coordinate类型绑定方法distance
// 计算点与点之间的距离
func (s coordinate) distance(t coordinate) float64 {
	return math.Pow(s.x-t.x, 2) + math.Pow(s.y-t.y, 2) + math.Pow(s.z-t.z, 2)
}

func main() {
	p1 := coordinate{x: 10.5, y: 10, z: 20}
	p2 := coordinate{x: 5, y: 20, z: 10}

	fmt.Println("两点空间距离为：", p1.distance(p2))

	p3 := newCoordinate(2.2, 10.3, -4.24)
	fmt.Printf("p3: %+v \n", p3)

	c4 := newCodingce(1, 2.1, 3.0)
	fmt.Println("这是构造方法创建的", c4)
	fmt.Printf("%+v \n", c4)

}

// 构造函数
// 三点坐标
type codingce struct {
	x, y, z float64
}

func newCodingce(x, y, z float64) codingce {
	return codingce{x, y, z}
}

func newCoordinate(x, y, z float64) coordinate {
	return coordinate{x, y, z}
}
