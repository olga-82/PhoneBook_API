package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
public class AuthRequestDTO {
//   "username": "string",
//   "password": "\"'M\"(WrTpNYOjA4"

    String username;
    String password;

}
