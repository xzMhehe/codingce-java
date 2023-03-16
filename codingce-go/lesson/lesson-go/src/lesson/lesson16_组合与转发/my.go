package main

import "fmt"

//员工
type employee struct {
	id         int64
	name       string
	department department
	account    account
}

//部门
type department struct {
	name string
	code string
}

//账户
type account struct {
	logID string
	level int64
}

//为account类型绑定方法
func (acc account) salary() int64 {
	return acc.level * 2500
}

type employee1 struct {
	id   int64
	name string
	department
	account
}

//部门
type department1 struct {
	name string
	code string
}

//账户
type account1 struct {
	logID string
	level int64
}

//为account类型绑定方法
func (e employee) salary1() int64 {
	return e.account.salary()
}

func main() {

	// 组合：合并结构
	dept := department{"Production", "DP"}
	acc := account{logID: "12345", level: 2}

	jack := employee{
		id:         1001,
		name:       "jack",
		department: dept,
		account:    acc,
	}

	fmt.Printf("%+v\n", jack)
	// output: {id:1001 name:jack department:{name:Production code:DP} account:{logID:12345 level:2}}
	fmt.Printf("jack's department is %v\n", jack.department.name)
	// output: jack's department is Production

	// 方法的转发
	fmt.Printf("jack's salary is %v now\n", jack.account.salary()) //jack's salary is 5000 now

	// Go语言中可以通过struct嵌入来实现方法的转发。
	dept1 := department{"Production", "DP"}
	acc1 := account{logID: "12345", level: 2}
	jack1 := employee{
		id:         1001,
		name:       "jack",
		department: dept1,
		account:    acc1,
	}

	//调用方法
	fmt.Printf("jack's salary is %v now\n", jack1.salary1()) //jack's salary is 5000 now
}
