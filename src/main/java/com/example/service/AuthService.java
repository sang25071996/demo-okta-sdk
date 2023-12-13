package com.example.service;

import com.example.model.Group;
import com.example.model.Users;
import com.example.request.AbstractService;
import com.example.request.UserRequest;
import com.okta.sdk.resource.client.ApiClient;
import com.okta.sdk.resource.client.StringUtil;
import com.okta.sdk.resource.common.PagedList;
import com.okta.sdk.resource.group.GroupBuilder;
import com.okta.sdk.resource.model.UpdateUserRequest;
import com.okta.sdk.resource.model.User;
import com.okta.sdk.resource.model.UserProfile;
import com.okta.sdk.resource.user.UserBuilder;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AuthService extends AbstractService {

    private final ApiClient client;

    public AuthService(ApiClient client) {
        super(client);
        this.client = client;
    }

    public Users getUser(String userId) {
        User user = userApi.getUser(userId);
        UserProfile userProfile= user.getProfile();
        return Users.convert(user, userProfile);
    }

    public List<Users> getUsers(int pageSize) {
        List<User> pagedList = userApi.listUsers(null, null, pageSize,
            null, null, null, null);
        List<Users> users = pagedList.stream()
            .map(user -> Users.convert(user, user.getProfile())).toList();
        return users;
    }

    public Users createUser(UserRequest userRequest) {
        User user = UserBuilder.instance()
            .setEmail(userRequest.getEmail())
            .setFirstName(userRequest.getFirstName())
            .setLastName(userRequest.getLastName())
            .setCity(userRequest.getCity())
            .setPassword(userRequest.getPassword().toCharArray())
            .setMobilePhone(userRequest.getMobilePhone())
            .setActive(true)
            .buildAndCreate(userApi);
        return Users.convert(user, user.getProfile());
    }

    public Users updateUser(String userId, UserRequest userRequest) {
        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        UserProfile userProfile = new UserProfile();
        userProfile.setEmail(userRequest.getEmail());
        userProfile.setFirstName(userRequest.getFirstName());
        userProfile.setLastName(userRequest.getLastName());
        userProfile.setCity(userRequest.getCity());
        userProfile.setMobilePhone(userRequest.getMobilePhone());
        updateUserRequest.setProfile(userProfile);
        User user = userApi.updateUser(userId, updateUserRequest, true);
        return Users.convert(user, user.getProfile());
    }

    public Group createGroup(Group groupRequest) {
        com.okta.sdk.resource.model.Group group = GroupBuilder.instance().setName(groupRequest.getName())
            .setDescription(groupRequest.getDescription()).buildAndCreate(groupApi);
        return Group.convert(group);
    }
    public List<Group> getGroups() {
        List<com.okta.sdk.resource.model.Group> groupList = groupApi.listGroups(null, null ,null, 100, null, null, null, null);
        List<Group> groups = groupList.stream().map(group -> Group.convert(group)).toList();
        return groups;
    }
}
