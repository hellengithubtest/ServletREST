package com.servlet.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Statistics {
    private Long personcount;
    private Long carcount;
    private Long uniquevendorcount;
}
