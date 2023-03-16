package main

import (
	"fmt"
)

type person struct {
	name string
	age  int
}

// person
func birthday(p person) {
	p.age++
}

//入参改为 person 指正类型
func birthday1(p *person) {
	p.age++
}

// 指针接收者
func (p *person) birthday3() {
	p.age++
}

func main() {
	jack := person{name: "jack", age: 18}

	// 这里传送jack的副本
	birthday(jack)
	// 原jack字段值不会改变
	fmt.Printf("%+v", jack)

	// 通过指针ch传递函数
	jack1 := person{
		name: "Jack",
		age:  12,
	}

	birthday1(&jack1)
	fmt.Println(jack.age) //13

	// 指针接收者
	tom := person{
		name: "Tom",
		age:  20,
	}
	tom.birthday3()
	fmt.Println(jack.age) //21

	// 内部指针
	yasuo := character{
		name: "Yasuo",
	}

	levelUp(&yasuo.stats)

	fmt.Printf("%+v", yasuo)

	// 修改数组
	var board [8][8]rune
	reset(&board)
	fmt.Printf("%c", board[0][0])

}

type stats struct {
	level             int
	endurance, health int
}

func levelUp(s *stats) {
	s.level++
	s.endurance = 42 + (14 * s.level)
	s.health = 5 * s.endurance
}

type character struct {
	name string
	stats
}

// 修改数组
func reset(board *[8][8]rune) {
	board[0][0] = 'r'
}

// 隐式指针
//type slice struct {
//	array unsafe.Pointer
//	len int
//	cap int
//}
