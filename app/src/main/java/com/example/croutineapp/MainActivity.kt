package com.example.croutineapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "coroutine_log"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "测试开始")

        /*runBlocking {
            Log.d(TAG, "协程开始, 线程：${Thread.currentThread().name}")
            delay(2000)
            Log.d(TAG, "协程结束: 线程：${Thread.currentThread().name}")
        }*/

        /*GlobalScope.launch {
            Log.d(TAG, "协程开始, 线程：${Thread.currentThread().name}")
            delay(2000)
            Log.d(TAG, "协程结束: 线程：${Thread.currentThread().name}")
        }*/

        /*GlobalScope.launch {
            Log.d(TAG, "协程开始, 线程：${Thread.currentThread().name}")
            val job = launch {
                Log.d(TAG, "job开始执行：线程：${Thread.currentThread().name}")
                repeat(100) {
                    Log.d(TAG, "repeat: $it")
                    delay(500)
                }
            }
            delay(3000)
            job.cancel()
//            job.join()
            Log.d(TAG, "协程结束: 线程：${Thread.currentThread().name}")
        }*/

        /*GlobalScope.launch {
            Log.d(TAG, "协程开始, 线程：${Thread.currentThread().name}")
            val async1 = async(start = CoroutineStart.LAZY) { asyncFun1() }
            val async2 = async(start = CoroutineStart.LAZY) { asyncFun2() }
//            async1.start()
//            async2.start()
            Log.d(TAG, "协程结束: 线程：${Thread.currentThread().name}, 结果：${async1.await() + async2.await()}")
        }*/

        /*GlobalScope.launch(Dispatchers.Main) {
            Log.d(TAG, "协程开始：线程：${Thread.currentThread().name}")
            val result = withContext(Dispatchers.IO) {
                Log.d(TAG, "withContext开始执行：线程：${Thread.currentThread().name}")
                delay(1000)
                return@withContext "aaa"
            }
            Log.d(TAG, "协程结束：withContext结果：$result, 线程：${Thread.currentThread().name}")
        }*/

        GlobalScope.launch {
            val result = withTimeoutOrNull(3000) {
                Log.d(TAG, "协程开始：线程：${Thread.currentThread().name}")
                repeat(30) {
                    Log.d(TAG, "repeat: $it")
                    delay(500)
                }
                return@withTimeoutOrNull "耗时操作完成"
            }
            Log.d(TAG, "协程结束：结果：$result, 线程：${Thread.currentThread().name}")
        }

        Log.d(TAG, "测试结束")
    }

    private suspend fun asyncFun1(): Int {
        Log.d(TAG, "asyncFun1: 开始执行")
        delay(500)
        return 2
    }

    private suspend fun asyncFun2(): Int {
        Log.d(TAG, "asyncFun2: 开始执行")
        delay(1000)
        return 6
    }
}