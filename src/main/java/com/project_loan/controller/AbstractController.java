package com.project_loan.controller;

import com.project_loan.dto.ResponseDto;
import com.project_loan.dto.ResultObject;

public abstract class AbstractController {

    protected <T> ResponseDto<T> ok(){
        return ok(null, ResultObject.getSuccess());
    }

    protected <T> ResponseDto<T> ok(T data) {
        return ok(data, ResultObject.getSuccess());
    }

    protected <T> ResponseDto<T> ok(T data, ResultObject result) {
        ResponseDto<T> obj = new ResponseDto<>();
        obj.setResult(result);
        obj.setData(data);

        return obj;
    }
}
