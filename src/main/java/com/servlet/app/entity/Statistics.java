package com.servlet.app.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Statistics {
    private Long personcount;
    private Long carcount;
    private Long uniquevendorcount;
}
