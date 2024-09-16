package com.ris.swimticketing.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseResponse<T> {
    private boolean success;
    private String message;
    private Long time;
    private T data;

    public static<T> BaseResponse<T> success(T data, String message) {
        return BaseResponse.<T>builder()
                .success(true)
                .message(message)
                .time(System.currentTimeMillis())
                .data(data)
                .build();
    }

    public static<T> BaseResponse<T> error(T data, String message) {
        return BaseResponse.<T>builder()
                .success(false)
                .message(message)
                .time(System.currentTimeMillis())
                .data(data)
                .build();
    }
}
