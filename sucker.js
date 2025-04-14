// 150mb를 임계치로 설정 (단위: 바이트)

const MEMORY_THRESHOLD = 150 * 1024 * 1024; // 150mb

// 현재 메모리 사용량을 체크하는 함수
function checkMemoryUsage() {
	const memoryUsage = process.memoryUsage();
	// heapUsed는 실제 사용 중인 heap 메모리량 (바이트 단위)
	const heapUsedMB = (memoryUsage.heapUsed / (1024 * 1024)).toFixed(2);
	console.log(`현재 사용 중인 Heap 메모리: ${heapUsedMB} MB`);

	if (memoryUsage.heapUsed > MEMORY_THRESHOLD) {
		console.error(`메모리 임계치(${(MEMORY_THRESHOLD / (1024 * 1024)).toFixed(2)} MB)를 초과하여 프로그램을 종료합니다.`);
		process.exit(1);
	}
}

// 1초마다 메모리 사용량을 체크
setInterval(checkMemoryUsage, 1000);

// 메모리 사용량을 인위적으로 증가시키기 위해 1MB 버퍼를 주기적으로 할당
const memoryHog = [];
setInterval(() => {
	memoryHog.push(Buffer.alloc(1024 * 1024)); // 1MB 크기의 버퍼 추가
}, 100);
