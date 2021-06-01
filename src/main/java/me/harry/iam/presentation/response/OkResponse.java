package me.harry.iam.presentation.response;

import lombok.Data;

@Data
public class OkResponse {
    private Long status;
    private String message;

    public OkResponse(Long status, String message) {
        this.status = status;
        this.message = message;
    }
}
