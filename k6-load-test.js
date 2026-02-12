import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
  stages: [
    { duration: '30s', target: 50 }, // Ramp-up to 50 users
    { duration: '1m', target: 200 }, // Stay at 200 users
    { duration: '30s', target: 0 }, // Ramp-down to 0 users
  ],
};

export default function () {
  const res = http.get('http://localhost:8080/api/fast');
  check(res, {
    'status is 200': (r) => r.status === 200,
  });
  sleep(1);
}
