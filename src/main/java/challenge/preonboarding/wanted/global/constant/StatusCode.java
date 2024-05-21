package challenge.preonboarding.wanted.global.constant;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
public enum StatusCode {

    // Status - 2xx

        // 200
        SUCCESS(HttpStatus.OK, "SUCCESS", "요청 성공"),

    // Status - 3xx

    // Status - 4xx

        // 400

            // General
            BAD_PARAM(BAD_REQUEST,"BAD_PARAM", "잘못된 데이터입니다."),
            REQ_HEADER(BAD_REQUEST, "REQ_HEADER", "헤더가 누락되었습니다."),
            REQ_PARAM(BAD_REQUEST, "REQ_PARAM", "파라미터가 누락되었습니다."),

        // 401
        NO_AUTHENTICATION(UNAUTHORIZED, "NO_AUTHENTICATION", "인증이 필요합니다."),

        // 403
        NO_PERMISSION(FORBIDDEN, "NO_PERMISSION", "해당 Resource에 접근할 권한이 없습니다."),

        // 404
        NOT_FOUND_RESOURCE(NOT_FOUND, "NOT_FOUND_RESOURCE", "요청에 해당하는 Resource를 찾을 수 없습니다."),

        // 405
        NOT_SUPPORTED_METHOD(METHOD_NOT_ALLOWED, "NOT_SUPPORTED_METHOD", "지원되지 않는 HTTP 메소드입니다."),

        // 406
        NOT_ACCEPTABLE_TYPE(NOT_ACCEPTABLE, "NOT_ACCEPTABLE_TYPE", "지원되지 않는 Accept-Type 입니다."),

        // 415
        NOT_SUPPORTED_MEDIA_TYPE(UNSUPPORTED_MEDIA_TYPE, "NOT_SUPPORTED_MEDIA_TYPE", "지원되지 않는 Content-Type 입니다."),

    // Status - 5xx

        // 500
        SERVER_ERROR(INTERNAL_SERVER_ERROR, "SERVER_ERROR", "예기치 못한 오류가 발생하였습니다."),
    ;
    private final HttpStatus status;
    private final String code;
    private final String msg;

    StatusCode(HttpStatus status, String code, String msg) {
        this.status = status;
        this.code = code;
        this.msg = msg;
    }
}
