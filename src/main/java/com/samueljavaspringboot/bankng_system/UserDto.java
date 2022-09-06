package com.samueljavaspringboot.bankng_system;

import lombok.Data;

import java.io.Serializable;

public record UserDto(String username, String password) implements Serializable {
}
