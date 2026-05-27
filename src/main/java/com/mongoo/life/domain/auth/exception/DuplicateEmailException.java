package com.mongoo.life.domain.auth.exception;

import com.mongoo.life.global.exception.BusinessException;
import com.mongoo.life.global.exception.ErrorCode;

public class DuplicateEmailException extends BusinessException {
    public DuplicateEmailException() {
        super(ErrorCode.DUPLICATE_EMAIL);
    }
}
