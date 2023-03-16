package main

import (
	"fmt"
	"math"
	"sort"
)

func main() {
	//声明map，使用复合字面量的声明方式
	temperature := map[string]int{
		"Mars":  -65,
		"Earth": 15,
	}

	fmt.Println(temperature)

	// 读
	temp := temperature["Earth"]
	fmt.Println("读取map", temp)

	// 写
	temperature["测试"] = 5
	temperature["测试2"] = 6

	fmt.Println(temperature)

	// 若访问不存在的键，则返回值类型0
	fmt.Println(temperature["你好"])

	// temperature["Moon"] = 8888

	if zzzz, ok := temperature["Moon"]; ok {
		fmt.Printf("%v \n", zzzz)
	} else {
		fmt.Println("Where is the zzzz")
	}

	// map是不会被复制的
	planets := map[string]string{
		"Earth": "Sector ZZ9",
		"Mars":  "Sector ZZ9",
	}

	planetsMarkII := planets
	planets["Earth"] = "Whoops"

	fmt.Println(planets)       // map[Earth:Whoops Mars:Sector ZZ9]
	fmt.Println(planetsMarkII) // map[Earth:Whoops Mars:Sector ZZ9]

	// 移除map中的元素（key-value对）
	delete(planets, "Earth")
	delete(planets, "Moon") // 移除不存在的元素，不会引发panic

	fmt.Println(planets)       // map[Mars:Sector ZZ9]
	fmt.Println(planetsMarkII) // map[Mars:Sector ZZ9]

	// 综合案例
	codingce := []float64{
		-28.0, 32.0, -31.0, -29.0, -23.0, -28.0, -33.0,
	}

	// 键：float64，值；[]float64
	groups := make(map[float64][]float64)

	for _, t := range codingce {
		g := math.Trunc(t/10) * 10 // 将温度按10度的跨度进行分组
		groups[g] = append(groups[g], t)
	}

	for g, codingce := range groups {
		fmt.Printf("%v: %v \n", g, codingce)
	}

	/*
		上例输出结果：
		-20 : [-28 -29 -23 -28]
		30 : [32]
		-30 : [-31 -33]
	*/

	// 示例：将切片去重并排序，在Go中没有set，但是可以使用map来实现set的功能
	numbers := []float64{
		51.02, 10.2, -5.2, -10.4, 14.2, 10.2, 5.12, 51.02, -30.0, 4.3,
	}
	set := make(map[float64]bool)

	for _, t := range numbers {
		set[t] = true
	}

	unique := make([]float64, 0, len(set))

	for t := range set {
		unique = append(unique, t)
	}

	//  排序
	sort.Float64s(unique)
	fmt.Println(unique)
}
