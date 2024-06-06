### 페이먼츠 서비스 데이터 모델링

```mysql
# 페이먼트 이벤트 테이블
CREATE TABLE payment_events (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    buyer_id BIGINT NOT NULL comment '구매자 ID',
    is_payment_done BOOLEAN NOT NULL DEFAULT FALSE comment '결제 완료 여부',
    payment_key VARCHAR(255) UNIQUE comment '결제 key',
    order_id VARCHAR(255) UNIQUE comment '결제 주문 Id',
    type ENUM('NORMAL') NOT NULL comment '결제 타입',
    order_name VARCHAR(255) NOT NULL comment '결제 주문 이름',
    method ENUM('EASY_PAY') comment '결제 주문 방법',
    psp_raw_data JSON comment '결제 승인 응답 데이터',
    approved_at DATETIME comment '결제 승인 시간',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP comment '결제 이벤트 생성 시간',
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP comment '업데이트 시간'
);

# 페이먼트 주문 테이블
CREATE TABLE payment_orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    payment_event_id BIGINT NOT NULL comment '결제 이벤트 참조 id',
    seller_id BIGINT NOT NULL comment '판매자 id',
    product_id BIGINT NOT NULL comment '상품 id',
    order_id VARCHAR(255) NOT NULL comment '주문 id',
    amount DECIMAL(12, 2) comment '거래금액',
    payment_order_status ENUM('NOT_STARTED', 'EXECUTING', 'SUCCESS', 'FAILURE', 'UNKNOWN') NOT NULL DEFAULT 'NOT_STARTED' comment '결제 주문 상태',
    ledger_updated BOOLEAN NOT NULL DEFAULT FALSE comment '장부 업데이트',
    wallet_updated BOOLEAN NOT NULL DEFAULT FALSE comment '지갑 업데이트',
    failed_count TINYINT NOT NULL DEFAULT 0 comment '결제 실패 횟수',
    threshold TINYINT NOT NULL default 5 comment '결제 최대 실패횟수',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP comment '결제 주문 생성 시간',
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP comment '결제 주문 업데이트 시간',


    FOREIGN KEY (payment_event_id) REFERENCES payment_events(id)
);

# 주문 테이블
CREATE TABLE payment_order_histories (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    payment_order_id BIGINT NOT NULL comment '결제 주문 테이블 참조 id',
    previous_status ENUM('NOT_STARTED', 'EXECUTING', 'SUCCESS', 'FAILURE', 'UNKNOWN') comment '변경 전 결제 주문 상태',
    new_status ENUM('NOT_STARTED', 'EXECUTING', 'SUCCESS', 'FAILURE', 'UNKNOWN'),
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP comment '결제 주문 생성 시간',
    changed_by VARCHAR(255) comment '상태 변화를 발생시킨 주체',
    reason VARCHAR(255) comment '상태 변화의 원인주체',

    FOREIGN KEY (payment_order_id) REFERENCES payment_orders(id)
);

# outboxes 테이블
CREATE TABLE outboxes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    idempotency_key VARCHAR(255) UNIQUE NOT NULL comment '멱등성 키',
    status ENUM('INIT', 'FAILURE', 'SUCCESS') DEFAULT 'INIT' comment '메세지 전송 상태',
    type VARCHAR(40) comment '이벤트 메세지 타입',
    partition_key INT DEFAULT 0 comment '메세지 큐의 파티셔닝 키',
    payload JSON comment '이벤트 메세지 본문',
    metadata JSON comment '메타 데이터',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);
```
