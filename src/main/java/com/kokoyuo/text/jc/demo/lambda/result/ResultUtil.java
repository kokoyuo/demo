package com.kokoyuo.text.jc.demo.lambda.result;


import java.util.ArrayList;

/**
 * 返回结果处理工具
 * @author  liusongbai
 * @date 2017-05-17
 */
public class ResultUtil {

    /**
     * 通用的成功，-1代表可能是无效请求（并不关注返回的结果）
     */
    public static MallItemResult alwaysFailureConstant = createFailedResult(ResultCode.ALWAYS_FAILURE);
    public static MallItemResult alwaysSuccessConstant = createSuccessResult();

    /**
     * 创建失败时的返回对象
     * 
     * @param resultCode 结果代码
     * @return 结果信息
     */
    public static <T> MallItemResult<T> createFailedResult(ResultCode resultCode) {
        MallItemResult<T> result = new MallItemResult<>();
        result.setData(null);
        setResult(result, resultCode, false);
        return result;
    }

    /**
     * 创建失败时的返回对象
     * 
     * @param resultCode 结果代码
     * @param msg 错误提示
     * @return 结果信息
     */
    public static <T> MallItemResult<T> createFailedResult(ResultCode resultCode, String msg) {
    	MallItemResult<T> result = new MallItemResult<>();
        result.setData(null);
        result.setSuccess(false);
        result.setCode(resultCode.getCode());
        result.setMsg(msg);
        return result;
    }

    /**
     * 创建成功时的返回对象
     * 
     * @param t 成功后返回对象
     * @param resultCode
     * @return
     */
    public static <T> MallItemResult<T> createSuccessResult(T t, ResultCode resultCode) {
        MallItemResult<T> result = new MallItemResult<>();
        result.setData(t);
        setResult(result, resultCode, true);
        return result;
    }

    /**
     * 创建成功时的返回对象
     * 
     * @return 返回成功对象
     */
    public static <T> MallItemResult<T> createSuccessResult() {
        return createSuccessResult(null, ResultCode.SUCCESS);
    }

    /**
     * 创建成功时的返回对象
     * 
     * @param t
     * @return
     */
    public static <T> MallItemResult<T> createSuccessResult(T t) {
        return createSuccessResult(t, ResultCode.SUCCESS);
    }

    /**
     *
     * @param result 结果对象
     * @param resultCode 返回码
     * @param isSuccess 是否成功
     * @param <T> 成功返回对象
     */
    private static <T> void setResult(MallItemResult<T> result, ResultCode resultCode, boolean isSuccess) {
        result.setSuccess(isSuccess);
        result.setCode(resultCode.getCode());
        result.setMsg(resultCode.getMsg());
    }

    /**
     * 失败&创建支持分页的返回对象
     *
     * @param resultCode 结果代码
     * @return 返回分页结果
     */
    public static <T> PageResult<T> createFailedPageResult(ResultCode resultCode) {
        PageResult<T> result = new PageResult<>();
        result.setData(new ArrayList<T>(0));
        setResult(result, resultCode, false);
        return result;
    }

    /**
     * 创建成功的返回对象
     * 
     * @param t
     * @return
     */
    /*public static <T> PageResult<T> createSuccessPageResult(List<T> t, BaseQueryOption queryBaseDO) {
        PageResult<T> result = new PageResult<>();
        result.setData(t);
        result.setSuccess(true);
        result.setPageNum(queryBaseDO.getCurrentPage());
        result.setTotal(queryBaseDO.getTotal());
        result.setPages(queryBaseDO.getTotalPage());
        result.setPageSize(queryBaseDO.getPageSize());
        return result;
    }*/
}
