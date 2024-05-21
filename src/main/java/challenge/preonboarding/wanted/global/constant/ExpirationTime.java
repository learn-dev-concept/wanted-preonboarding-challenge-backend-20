package challenge.preonboarding.wanted.global.constant;

public abstract class ExpirationTime {
    // JWT 토큰
    public static final Long ACCESS_TOKEN_EXPIRATION_TIME = 1000 * 60 * 60L;                // 밀리 초 단위 (60분)
    public static final Long REFRESH_TOKEN_EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 3L;      // 밀리 초 단위 (3일)
}
