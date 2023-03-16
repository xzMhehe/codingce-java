package main

import "fmt"

func main() {
	myMap := make(map[string]string)

	myMap["测试1"] = "测试1"
	myMap["测试2"] = "测试2"
	myMap["测试3"] = "测试3"
	myMap["测试4"] = "测试4"
	myMap["测试5"] = "测试5"
	myMap["测试6"] = "测试6"
	myMap["测试7"] = "测试7"
	myMap["测试7"] = "测试相同的键值对"

	for key, value := range myMap {
		fmt.Println("key", key, "value", value)
	}

}
