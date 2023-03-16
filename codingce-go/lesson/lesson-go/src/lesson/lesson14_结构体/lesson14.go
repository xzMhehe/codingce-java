package main

import (
	"encoding/json"
	"fmt"
	"os"
)

var people struct {
	name string
	id   int
}

// 使用type声明可复用的结构
type duck struct {
	name string
	id   int
}

func main() {
	fmt.Println("结构体")

	people.name = "后端码匠"
	people.id = 1

	fmt.Println(people.name, people.id)
	fmt.Println(people)

	// 将struct类型的变量赋值给新的变量也是会复制一份完全相同的值传递过去。
	people2 := people

	people2.name = "我是后端码匠2"
	people2.id = 2

	fmt.Println(people2.name)
	fmt.Println(people2.id)

	var duckone duck // 声明一个duck类型
	duckone.name = "测试声明类型"
	duckone.id = 12
	fmt.Println(duckone)

	ducktwo := duck{name: "挨个赋值", id: 12}

	fmt.Println(ducktwo)

	// 使用fmt.Printf()格式化输出结构体数据时，可以使用 %+v 来显示结构中的字段名。
	fmt.Printf("%v \n", ducktwo)
	fmt.Printf("%+v \n", ducktwo)

	// 切片也是结构体

	duckthree := duck2{Name: "挨个赋值", Id: 12}
	// struct转json
	bytes, err := json.Marshal(duckthree)
	exitOnError(err)
	fmt.Println("转string后输出")
	fmt.Println(string(bytes)) // 转string后输出

	spiritV3 := locationV3{Lat: 12.433, Long: 144.843}
	bytesV3, errV3 := json.Marshal(spiritV3)
	exitOnError(errV3)
	fmt.Println(string(bytesV3)) //output: {"latitude":12.433,"longitude":144.843}

}
func exitOnError(err error) {
	if err != nil {
		fmt.Println(err)
		os.Exit(1)
	}
}

type locationV3 struct {
	Lat  float64 `json:"latitude"`
	Long float64 `json:"longitude"`
}

// 使用type声明可复用的结构
type duck2 struct {
	Name string `json:"name"`
	Id   int    `json:"id"`
}
