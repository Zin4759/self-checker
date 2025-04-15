import java.security.KeyStore.TrustedCertificateEntry

fun checkMemory(threshold: Long){
    val runtime = Runtime.getRuntime()
    val totalmemory = runtime.totalMemory()
    val freeMemory = runtime.freeMemory()
    val usedMemory = totalmemory - freeMemory

    println("현재 사용 중인 메모리 : ${usedMemory / (1024 * 1024)} MB")
    if (usedMemory > threshold) {
        println("메모리 임계치 (${threshold / (1024 * 1024)} MB)를 초과하여 프로그램을 종료합니다.")
        System.exit(1)
    }
}


fun main() {
    // 임계치를 150mb로 설정 (150 * 1024 * 1024 바이트)
    val memoryThreshold = 150L * 1024 * 1024

    // 별도 쓰레드에서 1초마다 메모리 사용량을 체크
    val monitorThread = Thread{
        while (true) {
            checkMemory(memoryThreshold)
            Thread.sleep(1000)
        }
    }
    monitorThread.isDaemon = true
    monitorThread.start()

    // 아래 코드는 메모리 사용량을 인위적으로 증가시키기 위한 예제입니다.
    val memoryHog = mutableListOf<ByteArray>()
    while (true) {
        // 1mb 크기의 배열을 주기적으로 추가하여 메모리 누수를 유발
        memoryHog.add(ByteArray(1024 * 1024))
        Thread.sleep(100) // 100 밀리초마다 할
    }

    /*

var suck = 0
while (true) {
    suck = suck + 1
}      */

}
