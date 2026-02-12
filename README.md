# Observability Lab

## Overview
This project demonstrates real observability in production using Java 21, Spring Boot 3.x, Micrometer, Prometheus, and Grafana. It includes endpoints to simulate various scenarios and metrics to analyze application behavior under load.

## Endpoints
- `/api/fast`: Returns immediately (baseline).
- `/api/io`: Simulates external call latency (100–500ms).
- `/api/cpu`: Performs a CPU-bound operation.
- `/api/unstable`: Randomly fails with a 500 error (~20% of the time).

## Metrics
### Standard Metrics
- HTTP request metrics
- JVM metrics (GC, threads, memory, CPU)

### Custom Metrics
- Counter for specific errors
- Timer for critical blocks
- Gauge for internal structure size
- Timer for simulated external call latency

## Infrastructure
### Docker Compose
- **App**: Spring Boot application exposing metrics at `/actuator/prometheus`.
- **Prometheus**: Collects metrics from the app.
- **Grafana**: Visualizes metrics with pre-configured dashboards.

### Prometheus Configuration
Prometheus scrapes metrics from the application at `http://app:8080/actuator/prometheus`.

### Grafana Dashboard
Includes panels for:
- Latency (p50, p95, p99)
- Requests per second
- Error rate
- CPU usage
- Heap usage
- Threads

## Load Testing
Use the provided `k6-load-test.js` script to simulate load:
```bash
k6 run k6-load-test.js
```

## Observations
- Compare behavior before/after load.
- Analyze latency percentiles (p95, p99) vs average.
- Observe the impact of errors on aggregated latency.
- Identify bottlenecks under 200+ req/s.

## Setup
1. Build and run the application:
   ```bash
   mvn clean package
   docker-compose up --build
   ```
2. Access Prometheus at `http://localhost:9090`.
3. Access Grafana at `http://localhost:3000` (default credentials: `admin`/`admin`).
4. Import the provided Grafana dashboard JSON.

## Technical Notes
- Focus on engineering clarity.
- Avoid boilerplate and unnecessary complexity.
