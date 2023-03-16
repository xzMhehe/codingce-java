package counters

// 声明了未公开的类型
type alertCounter int

func New(value int) alertCounter {
	return alertCounter(value)
}
