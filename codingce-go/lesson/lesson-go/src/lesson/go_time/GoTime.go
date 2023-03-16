package main

import (
	"fmt"
	"time"
)

// 计算时间差（天数）
func SubDays(t1, t2 time.Time) (day int) {
	day = int(t1.Sub(t2).Hours() / 24)
	return
}

// 利用Add()计算具体时间点：
func AddTimeDuration() {
	timeDuration, _ := time.ParseDuration("-10m") // 参数有多种选择，自行百度...
	beginTime := time.Now().Add(timeDuration).Format("2006-01-02 15:04:05")
	fmt.Println("前推10分钟：", beginTime)
}

func main() {
	// (1) time.Sub() - 计算两个time类型日期相差天数：
	timeFormat := "2006-01-02 15:04:05"                             // 默认转换日期格式
	beginTime, err := time.Parse(timeFormat, "2021-09-13 13:47:59") // string -> time
	if err != nil {
		fmt.Println("time.Parse error")
	}
	endTime, err := time.Parse(timeFormat, "2022-06-30 13:47:59") // string -> time
	if err != nil {
		fmt.Println("time.Parse error")
	}

	subDays := SubDays(endTime, beginTime) // 参数1：结束日期:	参数2：开始靠前
	fmt.Println("相差天数1 = ", subDays)

	subDays1 := SubDays(time.Now(), beginTime) // time.Now() 当前日期
	fmt.Println("相差天数2 = ", subDays1)

	// (2) time包的ISOWeek()方法 - 根据提供日期计算当前是一年中的第几周，常用在数据库按周分表的场景下：
	year, week := beginTime.ISOWeek() // time类型对象专用方法：ISOWeek()
	fmt.Printf("%d年第%d周\n", year, week)

	AddTimeDuration()
}
