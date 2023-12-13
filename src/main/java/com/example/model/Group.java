package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Group {
    private String groupId;
    private String name;
    private String description;

    public static Group convert(com.okta.sdk.resource.model.Group group) {
        return Group.builder()
            .groupId(group.getId())
            .name(group.getProfile().getName())
            .description(group.getProfile().getDescription()).build();
    }
}
