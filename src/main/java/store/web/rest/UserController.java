package store.web.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import store.domain.User;
import store.domain.enumeration.Role;
import store.service.user.DeleteUser;
import store.service.user.UpdateUser;
import store.service.user.CreateUser;
import store.service.user.GetUser;
import store.web.rest.dto.request.CreateUserRequest;
import store.web.rest.dto.request.UpdateUserRequest;
import store.web.rest.dto.response.UserResponse;

import javax.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;
import static store.web.rest.dto.response.UserResponse.from;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private CreateUser createUser;
    private GetUser getUser;
    private UpdateUser updateUser;
    private DeleteUser deleteUser;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody @Valid CreateUserRequest data) {
        log.info("POST /api/users/create - {}", data.toString());
        createUser.execute(data.toUserInfo());
        return new ResponseEntity<>(CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> getAll() {
        log.info("GET /api/users/all");
        final List<User> customers = getUser.byRole(Role.ROLE_CUSTOMER);
        return ResponseEntity.ok(customers.stream().map(UserResponse::from).collect(Collectors.toList()));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable("id") Long id) {
        log.info("GET /api/users/{}", id);
        final User customer = getUser.byId(id);
        return ResponseEntity.ok(from(customer));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{email}")
    public ResponseEntity<UserResponse> getByEmail(@PathVariable("email") String email) {
        log.info("GET /api/users/{}", email);
        final User customer = getUser.byEmail(email);
        return ResponseEntity.ok(from(customer));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CUSTOMER')")
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Long id,
                                       @RequestBody @Valid UpdateUserRequest data) {
        log.info("PUT /api/users/{}", id);
        final User customer = getUser.byId(id);
        updateUser.execute(customer, data);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        log.info("DELETE /api/users/{}", id);
        final User customer = getUser.byId(id);
        deleteUser.execute(customer);
        return ResponseEntity.noContent().build();
    }

}
