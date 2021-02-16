package com.one4ll.xplayer.database

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.combine

//suspend fun main() {
//    val flow1 = flow {
//        var count = 0
//        while (count < 10) {
//            delay(1000)
//            emit(count)
//            count++
//        }
//    }
//    val flow2 = flow {
//        var count = 'c'
//        while (count < 'h') {
//            delay(2000)
//            emit(count)
//            count++
//        }
//    }
////    flow1.combine(flow2) { a, b ->
////        println("$a $b")
////        a to b
////    }.mapLatest {
////        println(it)
////    }.collect {}
////    flow1.combine(flow2){a,b ->
////        println("$a $b")
////        println("combine")
////        a to b
////    }.mapLatest{
////        println("map latest $it")
////    }.collect{}
//    flow1.combine(flow2) { a, b ->
//        println("$a $b")
//        a to b
//    }.mapLatest {
//        println(it)
//    }.collect {}
//    flow1.mapLatest {
//        val foo = it
//        print("getting map latest $foo")
//    }.collect{}
//}
fun main() = runBlocking {
//    supervisorScope {
//        launch {
//            try {
//                withTimeout(6000) {
//                    delay(5000) // triggers the catch
//                    println("system current time milli seconds ${System.currentTimeMillis()}")
//                    while (System.currentTimeMillis() < 5000) {
//                        println("prints in while loop")
//                    } // doesn't trigger the catch
//                }
//            } catch (te: TimeoutCancellationException) {
//                println("Exception occur ${te.message}")
//            }
//        }
//        println("Here")
//    }
//    println("Here out side")
    val earth = Earth("", 0)
    val copyEarth = earth.copy(name = "")
}

data class Earth(val name: String, val age: Int) {
    val foo = ""
}