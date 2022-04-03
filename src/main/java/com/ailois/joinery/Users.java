package com.ailois.joinery;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Users {

    private Long id;
    private String store;
    private String name;
    private String email;
    private Long emailVerified;
    private Long isAnonymous;
    private Long ip;
    private String createdAt;
    private String updatedAt;

}
