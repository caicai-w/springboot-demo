package com.ceiling.springlauch.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AjaxResponse {
    private boolean isok;
    private int code;
    private String message;
    private Object data;

    public static AjaxResponse success(){
        AjaxResponse response = new AjaxResponse();
        response.setIsok(true);
        response.setCode(200);
        response.setMessage("success");
        return response;
    }

    public static AjaxResponse success(Object data){
        AjaxResponse result = new AjaxResponse();
        result.setIsok(true);
        result.setCode(200);
        result.setMessage("success");
        result.setData(data);
        return result;
    }
}
