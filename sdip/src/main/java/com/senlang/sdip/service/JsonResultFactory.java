/**
 *
 */
package com.senlang.sdip.service;

import java.util.HashMap;

import com.senlang.sdip.model.CommonDataResult;
import com.senlang.sdip.model.CommonResult;

/**
 * @author Mc.D
 */
public class JsonResultFactory {
    private static final HashMap<Integer, String> h = new HashMap<>();

    static {
        h.put(0, "操作成功");
        h.put(-1, "未知错误");
        h.put(1, "蓝信返回错误，请刷新页面重新尝试");
        h.put(2, "获取蓝信用户信息失败");
        h.put(3, "文件操作失败");
        // h.put(0, "操作成功");
        // h.put(0, "操作成功");
        h.put(400, "需要登录");

        h.put(401, "验证失败，请重新登录");
        h.put(404, "页面未找到");
        h.put(603, "权限不足");
        h.put(500, "接口服务器异常，请稍后再试");
        h.put(501, "数据库操作异常，请联系管理员");

        h.put(505, "传入参数错误");
        h.put(506, "模型未找到");

        h.put(900, "已超预算");

        h.put(901, "状态验证失败");

        h.put(3001, "库房名称重复");
        h.put(3002, "已设置价值编号，不能更改");
        h.put(3003, "库房类型错误");
        h.put(3004, "固定资产编号为空");
        h.put(3005, "固定资产编号重复");
        h.put(3006, "价值编号重复");
        h.put(3007, "物品未找到");
        h.put(3008, "库房未找到");
        h.put(3009, "入库单编码为空");
        h.put(3010, "入库单编码重复");
        h.put(3010, "入库单未找到");
        /**
         * bgyp
         */
        h.put(1444, "部门存在未完成订单，暂不可申请");
        h.put(1445, "商品暂时无法购买");
        /**
         * bgdh
         * 0：未申请（初始值）
         * 1：申请中
         * 2：已申请
         * 3：撤销中
         * 4：已撤销
         * 5: 移机中
         * 6: 功能变更中
         */
        h.put(6120, "装机申请,电话号码只能由电话安装部门填写");
        h.put(6130, "撤销申请只能由本人操作");
        h.put(6140, "撤销申请只能撤销未进行审批的申请");
        h.put(6101, "装机申请正在申请中,不能重复操作");
        h.put(6102, "已装机成功,不能一人多机");
        h.put(6103, "撤销装机审批中,请等待");
        h.put(6104, "当前状态不能申请");
        h.put(6105, "当前状态不能申请");
        h.put(6106, "当前状态不能申请");
        h.put(6107, "当前状态不能申请");
        h.put(6110, "已撤回的申请不能审批");
        /**
         * tcc
         */
        h.put(6611, "部门已存在年底续期申请");

        /**
         * tjgl
         */
        h.put(6599, "检测到重复的身份证号，保存失败！");
        h.put(6598, "检测到重复的年份，保存失败！");
        h.put(6597, "默认数据禁止删除！");
        h.put(6597, "默认年份禁止修改！");
    }

    /**
     * 根据给定的错误码返回标准返回格式
     *
     * @param errorcode
     * @return
     * @see #getCommonResult(int)
     * @see #getCommonResult(int, Object)
     * @see #getDataResult(int)
     * @see #getDataResult(int, Object)
     */
    @Deprecated
    public static HashMap<String, Object> getResult(int errorcode) {
        HashMap<String, Object> ret = new HashMap<>();
        ret.put("errorcode", errorcode);
        ret.put("errmsg", h.containsKey(errorcode) ? h.get(errorcode) : "未知错误");
        return ret;
    }

    /**
     * 返回指定错误消息的标准返回格式，错误码固定为-1
     *
     * @param errmsg
     * @return
     * @see #getCommonResult(String)
     * @see #getCommonResult(String, Object)
     * @see #getDataResult(String)
     * @see #getDataResult(String, Object)
     */
    @Deprecated
    public static HashMap<String, Object> getResult(String errmsg) {
        HashMap<String, Object> ret = new HashMap<>();
        ret.put("errorcode", -1);
        ret.put("errmsg", errmsg);
        return ret;
    }

    /**
     * 返回操作成功的标准返回格式，错误码固定为0，错误消息为“操作成功”
     *
     * @return
     * @see #getOkCommonResult()
     * @see #getOkCommonResult(Object)
     * @see #getOkDataResult()
     * @see #getOkDataResult(Object)
     */
    @Deprecated
    public static HashMap<String, Object> getOkResult() {
        return getResult(0);
    }

    /**
     * 根据给定的错误码和数据返回标准返回格式
     *
     * @param errorcode
     * @param data
     * @return
     */
    @Deprecated
    public static HashMap<String, Object> getResult(int errorcode, Object data) {
        HashMap<String, Object> ret = new HashMap<>();
        ret.put("errorcode", errorcode);
        ret.put("errmsg", h.containsKey(errorcode) ? h.get(errorcode) : "未知错误");
        ret.put("data", data);
        return ret;
    }

    /**
     * 返回指定错误消息和数据的标准返回格式，错误码固定为-1
     *
     * @param errmsg
     * @param data
     * @return
     */
    @Deprecated
    public static HashMap<String, Object> getResult(String errmsg, Object data) {
        HashMap<String, Object> ret = new HashMap<>();
        ret.put("errorcode", -1);
        ret.put("errmsg", errmsg);
        ret.put("data", data);
        return ret;
    }

    /**
     * 返回操作成功的标准返回格式，错误码固定为0，错误消息为“操作成功”，包含数据
     *
     * @param data
     * @return
     */
    @Deprecated
    public static HashMap<String, Object> getOkResult(Object data) {
        return getResult(0, data);
    }

    /**
     * 根据给定的错误码返回标准数据返回格式
     *
     * @param errorcode
     * @return
     */
    public static CommonDataResult<Object> getDataResult(int errorcode) {
        CommonDataResult<Object> cdr = new CommonDataResult<Object>();
        cdr.setErrorcode(errorcode);
        cdr.setErrmsg(h.containsKey(errorcode) ? h.get(errorcode) : "未知错误");
        return cdr;
    }

    /**
     * 返回指定错误消息的标准数据返回格式，错误码固定为-1
     *
     * @param errmsg
     * @return
     */
    public static CommonDataResult<Object> getDataResult(String errmsg) {
        CommonDataResult<Object> cdr = new CommonDataResult<Object>();
        cdr.setErrorcode(-1);
        cdr.setErrmsg(errmsg);
        return cdr;
    }

    /**
     * 返回操作成功的标准数据返回格式，错误码固定为0，错误消息为“操作成功”
     *
     * @return
     */
    public static CommonDataResult<Object> getOkDataResult() {
        return getDataResult(0);
    }

    /**
     * 根据给定的错误码返回标准通用返回格式
     *
     * @param errorcode
     * @return
     */
    public static CommonResult getCommonResult(int errorcode) {
        CommonResult cdr = new CommonDataResult<Object>();
        cdr.setErrorcode(errorcode);
        cdr.setErrmsg(h.containsKey(errorcode) ? h.get(errorcode) : "未知错误");
        return cdr;
    }

    /**
     * 返回指定错误消息的标准通用返回格式，错误码固定为-1
     *
     * @param errmsg
     * @return
     */
    public static CommonResult getCommonResult(String errmsg) {
        CommonResult cdr = new CommonDataResult<Object>();
        cdr.setErrorcode(-1);
        cdr.setErrmsg(errmsg);
        return cdr;
    }

    /**
     * 返回操作成功的标准通用返回格式，错误码固定为0，错误消息为“操作成功”
     *
     * @return
     */
    public static CommonResult getOkCommonResult() {
        return getCommonResult(0);
    }

    /**
     * 根据给定的错误码和数据返回标准数据返回格式
     *
     * @param errorcode
     * @param data
     * @return
     */
    public static <T> CommonDataResult<T> getDataResult(int errorcode, T data) {
        CommonDataResult<T> cdr = new CommonDataResult<T>();
        cdr.setErrorcode(errorcode);
        cdr.setErrmsg(h.containsKey(errorcode) ? h.get(errorcode) : "未知错误");
        cdr.setData(data);
        return cdr;
    }

    /**
     * 返回指定错误消息和数据的标准数据返回格式，错误码固定为-1
     *
     * @param errmsg
     * @param data
     * @return
     */
    public static <T> CommonDataResult<T> getDataResult(String errmsg, T data) {
        CommonDataResult<T> cdr = new CommonDataResult<T>();
        cdr.setErrorcode(-1);
        cdr.setErrmsg(errmsg);
        cdr.setData(data);
        return cdr;
    }

    /**
     * 返回操作成功的标准数据返回格式，错误码固定为0，错误消息为“操作成功”，包含数据
     *
     * @param data
     * @return
     */
    public static <T> CommonDataResult<T> getOkDataResult(T data) {
        return getDataResult(0, data);
    }

    /**
     * 根据给定的错误码和数据返回标准通用返回格式
     *
     * @param errorcode
     * @param data
     * @return
     */
    public static <T> CommonResult getCommonResult(int errorcode, T data) {
        CommonDataResult<T> cdr = new CommonDataResult<T>();
        cdr.setErrorcode(errorcode);
        cdr.setErrmsg(h.containsKey(errorcode) ? h.get(errorcode) : "未知错误");
        cdr.setData(data);
        return cdr;
    }

    /**
     * 返回指定错误消息和数据的标准通用返回格式，错误码固定为-1
     *
     * @param errmsg
     * @param data
     * @return
     */
    public static <T> CommonResult getCommonResult(String errmsg, T data) {
        CommonDataResult<T> cdr = new CommonDataResult<T>();
        cdr.setErrorcode(-1);
        cdr.setErrmsg(errmsg);
        cdr.setData(data);
        return cdr;
    }

    /**
     * 返回操作成功的标准通用返回格式，错误码固定为0，错误消息为“操作成功”，包含数据
     *
     * @param data
     * @return
     */
    public static <T> CommonResult getOkCommonResult(T data) {
        return getCommonResult(0, data);
    }
}
