package main

import (
	"fmt"
	"sort"
)

func main() {
	fmt.Println("====依据key排序=====")
	z := map[int]string{
		1: "Mike",
		2: "Nike",
		3: "LiNing",
		4: "Erck",
	}

	var keys []int
	for key := range z {
		keys = append(keys, key)
	}

	sort.Ints(keys)

	for _, k := range keys {
		fmt.Println("Key:", k, "Value:", z[k])
	}

	fmt.Println("====依据value排序=====")

	m := map[string]int{
		"something": 10,
		"yo":        20,
		"blah":      20,
	}

	type kv struct {
		Key   string
		Value int
	}

	var ss []kv
	for k, v := range m {
		ss = append(ss, kv{k, v})
	}

	sort.Slice(ss, func(i, j int) bool {
		return ss[i].Value > ss[j].Value // 降序
		// return ss[i].Value < ss[j].Value  // 升序
	})

	for _, kv := range ss {
		fmt.Println("Key:", kv.Key, "Value:", kv.Value)
	}
}
