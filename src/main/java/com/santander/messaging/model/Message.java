package com.santander.messaging.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.DigestUtils;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Message {
    private int sequence;
    private String payload;

    private static final String template = "Message ( sequence=%05d, payloadHash=%s)";

    @Override
    public String toString() {
        return String.format(template, sequence, DigestUtils.md5DigestAsHex(payload.getBytes()));
    }
}
