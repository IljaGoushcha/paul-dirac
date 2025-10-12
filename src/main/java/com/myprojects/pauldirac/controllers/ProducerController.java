package com.myprojects.pauldirac.controllers;

import com.myprojects.pauldirac.dto.MessageRequest;
import com.myprojects.pauldirac.service.KafkaMessageProducer;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/kafka")
public class ProducerController {

    private final KafkaMessageProducer producer;

    public ProducerController(KafkaMessageProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/send")
    public ResponseEntity<String> send(@Valid @RequestBody MessageRequest req) {
        log.info("***** TEST 1 *****");
        producer.send(req.topic(), req.key(), req.message());
        return ResponseEntity.accepted().body("queued");
    }
}
