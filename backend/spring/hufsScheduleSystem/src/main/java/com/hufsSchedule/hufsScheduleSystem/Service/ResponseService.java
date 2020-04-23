package com.hufsSchedule.hufsScheduleSystem.Service;

import com.hufsSchedule.hufsScheduleSystem.ResultForm.CommonResult;
import com.hufsSchedule.hufsScheduleSystem.ResultForm.ListResult;
import com.hufsSchedule.hufsScheduleSystem.ResultForm.SingleResult;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {
    @Getter
    public enum CommonResponse{
        SUCCESS(0, "성공하셨습니다."),
        FAIL( -1, "실패하셔습니다.");

        int code;
        String msg;

        CommonResponse(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }

    public <T> SingleResult<T> getSingleResult(T data) {
        SingleResult<T> result = new SingleResult<>();
        result.setData(data);
        setSuccessResult(result);
        return result;
    }

    public <T> ListResult<T> getListResult(List<T> data) {
        ListResult<T> result = new ListResult<>();
        result.setList(data);
        setSuccessResult(result);
        return result;
    }

    public CommonResult getFailResult() {
        CommonResult result = new CommonResult();
        result.setSuccess(false);
        result.setCode(CommonResponse.FAIL.getCode());
        result.setMsg(CommonResponse.FAIL.getMsg());
        return result;
    }

    public void setSuccessResult(CommonResult result) {
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMsg(CommonResponse.SUCCESS.getMsg());
    }

    public void setFailResult(CommonResult result) {
        result.setSuccess(false);
        result.setCode((CommonResponse.FAIL.getCode()));
        result.setMsg(CommonResponse.FAIL.getMsg());
    }

}
