package main

import (
	"fmt"
)

func main() {
	fmt.Println("数组Array")

	// Array of planets
	var planets [8]string
	planets[0] = "测试"
	planets[1] = "测试"
	planets[2] = "测试"
	planets[3] = "测试"
	planets[4] = "测试"

	earth := planets[2]
	fmt.Println(earth)

	// 获取数组的长度
	fmt.Println(len(planets))

	// 复合字面值初始化数组
	dwarfs := [5]string{"测试1", "测试2", "测试3", "测试4", "测试5"}

	fmt.Println(dwarfs)

	// 试试其他奇怪的写法
	dwarfs2 := [4]string{"1", "2", "3"}
	fmt.Println(dwarfs2[3]) // 输出空

	// 或者
	planetCollection := [...]string{
		"ce1",
		"ce2",
		"ce3",
		"ce4",
		"ce5",
		"ce6",
		"ce7",
	}

	fmt.Println(len(planetCollection))
	fmt.Println(planetCollection)

	// 使用索引来为数组赋值
	array := [5]int{1: 10, 3: 30}

	fmt.Printf("%v\n", array)

	// for循环遍历数组
	for i := 0; i < len(planetCollection); i++ {
		planet := planetCollection[i]
		fmt.Println(planet)
	}

	// range 遍历数组
	for i, dwarf := range dwarfs {
		fmt.Println(i, dwarf)
	}

	planetsMarkII := planets // 会将planets的完整副本赋值给planetMarkII
	planets[2] = "whoops"
	// 改变了原数组planets，数组planetsMarkII是副本，所以不受影响
	fmt.Println(planets[2])       // whoops
	fmt.Println(planetsMarkII[2]) // Earth

	var board [8][8]string

	board[0][0] = "1"
	board[0][7] = "r"

	for column := range board {
		fmt.Println(board[0][column])
		board[2][column] = "p"
	}

	fmt.Println(board)
}
