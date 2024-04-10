package com.example.common.exception.user;

import java.io.Serial;

/**
 * 用户名不能为空i
 */
public class UserUsernameIsBlankException extends UserException {
    @Serial
    private static final long serialVersionUID = -1242888357093901440L;

    public UserUsernameIsBlankException() {
        super(null, "user.username.not.match");
    }
}
