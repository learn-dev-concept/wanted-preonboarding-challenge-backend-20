package challenge.preonboarding.wanted.global.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenDto {

    @JsonProperty(value = "ACCESS_TOKEN")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String accessToken;

    @JsonProperty(value = "REFRESH_TOKEN")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String refreshToken;
}
