package me.harry.iam.presentation.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OkResponse {
    private final Long status;
    private final String message;

    public OkResponse() {
        this.status = 200L;
        this.message = "";
    }

    public OkResponse(Long status, String message) {
        this.status = status;
        this.message = message;
    }
}
