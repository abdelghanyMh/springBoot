package com.example.demo.user;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class UserRestClient {
    private final RestClient restClient;

    public UserRestClient(RestClient.Builder builder) {
        this.restClient = builder.baseUrl("https://jsonplaceholder.typicode.com").build();
    }

    public User findUserById(Integer id) {
        return restClient.get().uri("/users/{id}", id).retrieve().body(User.class);
    }

    /*
    The `ParameterizedTypeReference` is a helper class in Spring that allows you to work with generic types when making REST API calls. It is particularly useful when you need to deserialize a response into a parameterized type, such as a `List<User>`.

In your code, the `ParameterizedTypeReference<List<User>>` is used to specify that the response from the `/users` endpoint should be deserialized into a `List<User>`.

Here's a detailed explanation of the selected line:

```java
return restClient.get().uri("/users").retrieve().body(new ParameterizedTypeReference<List<User>>() {});
```

1. `restClient.get()`: This initiates a GET request using the `RestClient`.
2. `.uri("/users")`: This sets the URI for the request to `/users`.
3. `.retrieve()`: This sends the request and retrieves the response.
4. `.body(new ParameterizedTypeReference<List<User>>() {})`: This specifies that the body of the response should be deserialized into a `List<User>`.

The `ParameterizedTypeReference` is necessary here because Java's type erasure removes generic type information at runtime. By using `ParameterizedTypeReference`, you can retain this type information and ensure that the response is correctly deserialized into the desired type.

Here's a simplified example to illustrate its usage:

```java
List<User> users = restClient.get().uri("/users").retrieve().body(new ParameterizedTypeReference<List<User>>() {});
```

In this example, the response from the `/users` endpoint is deserialized into a `List<User>` using the `ParameterizedTypeReference`.
  */
    public List<User> findAll() {
        return restClient.get().uri("/users").retrieve().body(new ParameterizedTypeReference<List<User>>() {
        });
    }

}
