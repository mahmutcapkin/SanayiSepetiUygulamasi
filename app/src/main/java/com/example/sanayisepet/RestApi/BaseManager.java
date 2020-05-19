package com.example.sanayisepet.RestApi;

class BaseManager {
    protected RestApi getRestApi(){
        RestApiClient restApiClient = new RestApiClient(BaseURL.URL);
        return restApiClient.getRestApi();
    }
}
