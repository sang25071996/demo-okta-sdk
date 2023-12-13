package com.example.model;

import com.okta.sdk.resource.model.User;
import com.okta.sdk.resource.model.UserProfile;
import java.time.OffsetDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Users {
    private String userId;
    private String email;
    private String firstName;
    private String lastName;
    private String mobilePhone;
    private String city;
    private OffsetDateTime created;

    public static Users convert(User user, UserProfile userProfile) {
        return Users.builder()
            .userId(user.getId())
            .email(userProfile.getEmail())
            .firstName(userProfile.getFirstName())
            .lastName(userProfile.getLastName())
            .mobilePhone(userProfile.getMobilePhone())
            .city(userProfile.getCity())
            .created(user.getCreated()).build();
    }
}
