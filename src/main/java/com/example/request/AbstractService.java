package com.example.request;

import com.okta.sdk.resource.api.GroupApi;
import com.okta.sdk.resource.api.UserApi;
import com.okta.sdk.resource.client.ApiClient;

public class AbstractService {

    protected UserApi userApi;
    protected GroupApi groupApi;
    protected final ApiClient client;

    public AbstractService(ApiClient client) {
        this.client = client;
        userApi = new UserApi(client);
        groupApi = new GroupApi(client);
    }
}
