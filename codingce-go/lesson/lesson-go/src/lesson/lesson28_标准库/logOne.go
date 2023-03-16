package main

import "log"

// log包
func init() {
	log.SetPrefix("Trace: ")
	log.SetFlags(log.Ldate | log.Lmicroseconds | log.Llongfile)
}

func main() {
	log.Println("hello")

	// Fatalln 在调用Println 之后会接着调用os.Exit(1)
	log.Fatal("fatal")

	// Panicln 在调用Println 之后会接着调用panic()
	log.Panicln("panic")

}
