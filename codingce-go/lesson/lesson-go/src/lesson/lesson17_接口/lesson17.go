package main

import (
	"fmt"
	"strings"
	"time"
)

// 定义接口
var t interface {
	talk() string
}

// 类型martian 满足了接口t
type martian struct{}

func (m martian) talk() string {
	return "实现接口的方法"
}

// 类型laser 满足了接口t
type laser int

func (l laser) talk() string {
	return strings.Repeat("pwe", 3)
}

// 为了复用，一般会将接口声明为类型。按照惯例，接口类型的名称常常以-er作为后缀99090
type talker interface {
	talk() string
}

//入参为任何满足talker接口的值
func shouter(t talker) {
	louder := strings.ToUpper(t.talk())
	fmt.Println(louder)
}

// 入参为任何满足talker接口的值
func shout(t talker) {
	louder := strings.ToUpper(t.talk())
	fmt.Println(louder)
}

type starship struct {
	laser
}

func main() {
	fmt.Println("接口")

	t = martian{}
	fmt.Println(t.talk())

	t = laser(3)
	fmt.Println(t.talk())

	shout(martian{})
	shout(laser(2))

	shouter(martian{})
	shouter(laser(9))

	// 满足接口的类型嵌入另一个struct中
	s := starship{laser(2)}
	fmt.Println(s.talk())

	shouter(s)

	// 探索接口
	// 顺路探究下时间类型
	t := time.Now()
	fmt.Println(t)

	// 格式输出
	fmt.Println(t.Format("2006-01-02 15:04:05"))
	fmt.Println(time.Now().Unix())

	fmt.Println(t.Year())
	fmt.Println(t.Month())
	fmt.Println(t.YearDay())
	fmt.Println(t.Date())
	fmt.Println(t.Day())

	today := time.Date(2020, 11, 23, 22, 59, 10, 0, time.UTC)
	fmt.Printf("%.1f 玉兔号飞船着陆\n", xstardate(today)) //output: 1328.9 探险号飞船着陆

	// 既可以转换“地球时间”
	today1 := time.Date(2020, 11, 23, 22, 59, 10, 0, time.UTC)
	fmt.Printf("%.1f Curiosity has landed\n", xstardate1(today1)) //1328.9 Curiosity has landed

	//也可以转换火星时间
	m := marsTime(1452)
	fmt.Printf("%.1f Curiosity has landed\n", xstardate1(m)) //1116.0 Curiosity has landed

}

// 将“地球时间”转成“x星时间”
func xstardate(t time.Time) float64 {
	doy := float64(t.YearDay())
	h := float64(t.Hour())
	return 1000 + doy + h
}

type xstadater interface {
	YearDay() int
	Hour() int
}

func xstardate1(t xstadater) float64 {
	doy := float64(t.YearDay())
	h := float64(t.Hour()) / 24.0
	return 1000 + doy + h
}

type marsTime int

func (s marsTime) YearDay() int {
	return int(s % 668)
}
func (s marsTime) Hour() int {
	return 0
}
