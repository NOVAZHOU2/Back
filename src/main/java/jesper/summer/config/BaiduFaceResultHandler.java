package jesper.summer.config;

import com.baidu.aip.util.Base64Util;
import jesper.summer.exception.BaiduApiException;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
@Slf4j
public class BaiduFaceResultHandler {

    // 错误码映射表
    private static final Map<Integer, String> ERROR_CODE_MAP = new HashMap<>();
    private static final Map<String, String> ERROR_MSG_MAP = new HashMap<>();

    static {
        // 初始化错误码映射
        ERROR_CODE_MAP.put(1, "服务器内部错误，请再次请求， 如果持续出现此类错误，请通过QQ群（860337848）或提交工单联系技术支持团队。");
        ERROR_CODE_MAP.put(2, "服务暂不可用，请再次请求， 如果持续出现此类错误，请通过QQ群（860337848）或提交工单联系技术支持团队。");
        ERROR_CODE_MAP.put(3, "调用的API不存在，请检查请求URL后重新尝试，一般为URL中有非英文字符，如“-”，可手动输入重试");
        ERROR_CODE_MAP.put(4, "集群超限额，请再次请求， 如果持续出现此类错误，请通过QQ群（860337848）或提交工单联系技术支持团队。");
        ERROR_CODE_MAP.put(6, "无权限访问该用户数据，创建应用时未勾选相关接口");
        ERROR_CODE_MAP.put(13, "获取token失败");
        ERROR_CODE_MAP.put(14, "IAM 鉴权失败");
        ERROR_CODE_MAP.put(15, "应用不存在或者创建失败");
        ERROR_CODE_MAP.put(17, "每天请求量超限额，可通过QQ群（860337848）联系群管、提交工单提升限额");
        ERROR_CODE_MAP.put(18, "QPS超限额，可通过QQ群（860337848）联系群管、提交工单提升限额");
        ERROR_CODE_MAP.put(19, "请求总量超限额，可通过QQ群（860337848）联系群管、提交工单提升限额");
        ERROR_CODE_MAP.put(100, "无效的access_token参数，请检查后重新尝试");
        ERROR_CODE_MAP.put(110, "access_token无效");
        ERROR_CODE_MAP.put(111, "access token过期");

        // 222000-222099 参数格式错误
        ERROR_CODE_MAP.put(222001, "必要参数未传入");
        ERROR_CODE_MAP.put(222002, "param[start]格式错误");
        ERROR_CODE_MAP.put(222003, "param[length]格式错误");
        ERROR_CODE_MAP.put(222004, "param[op_app_id_list]格式错误");
        ERROR_CODE_MAP.put(222005, "param[group_id_list]格式错误");
        ERROR_CODE_MAP.put(222006, "group_id格式错误");
        ERROR_CODE_MAP.put(222007, "uid格式错误");
        ERROR_CODE_MAP.put(222008, "face_id格式错误");
        ERROR_CODE_MAP.put(222009, "quality_conf格式错误");
        ERROR_CODE_MAP.put(222010, "user_info格式错误");
        ERROR_CODE_MAP.put(222011, "param[uid_list]格式错误");
        ERROR_CODE_MAP.put(222012, "param[op_app_id]格式错误");
        ERROR_CODE_MAP.put(222013, "param[image]格式错误");
        ERROR_CODE_MAP.put(222014, "param[app_id]格式错误");
        ERROR_CODE_MAP.put(222015, "param[image_type]格式错误");
        ERROR_CODE_MAP.put(222016, "param[max_face_num]格式错误");
        ERROR_CODE_MAP.put(222017, "param[face_field]格式错误");
        ERROR_CODE_MAP.put(222018, "param[user_id]格式错误");
        ERROR_CODE_MAP.put(222019, "param[quality_control]格式错误");
        ERROR_CODE_MAP.put(222020, "param[liveness_control]格式错误");
        ERROR_CODE_MAP.put(222021, "param[max_user_num]格式错误");
        ERROR_CODE_MAP.put(222022, "param[id_card_number]格式错误");
        ERROR_CODE_MAP.put(222023, "param[name]格式错误");
        ERROR_CODE_MAP.put(222024, "param[face_type]格式错误");
        ERROR_CODE_MAP.put(222025, "param[face_token]格式错误");
        ERROR_CODE_MAP.put(222026, "param[max_star_num]格式错误");

        // 222200-222299 人脸检测/识别错误
        ERROR_CODE_MAP.put(222201, "服务端请求失败");
        ERROR_CODE_MAP.put(222202, "图片中没有人脸");
        ERROR_CODE_MAP.put(222203, "无法解析人脸");
        ERROR_CODE_MAP.put(222204, "从图片的url下载图片失败");
        ERROR_CODE_MAP.put(222205, "服务端请求失败");
        ERROR_CODE_MAP.put(222206, "rtse服务返回失败");
        ERROR_CODE_MAP.put(222207, "未找到匹配的用户");
        ERROR_CODE_MAP.put(222208, "图片的数量错误");
        ERROR_CODE_MAP.put(222209, "face token不存在");

        // 222300-222399 人脸库管理错误
        ERROR_CODE_MAP.put(222300, "人脸图片添加失败");
        ERROR_CODE_MAP.put(222301, "获取人脸图片失败");
        ERROR_CODE_MAP.put(222302, "系统错误");
        ERROR_CODE_MAP.put(222303, "获取人脸图片失败");
        ERROR_CODE_MAP.put(222350, "公安网图片不存在或质量过低");
        ERROR_CODE_MAP.put(222351, "身份证号与姓名不匹配或该身份证号不存在");
        ERROR_CODE_MAP.put(222352, "身份证名字格式错误");
        ERROR_CODE_MAP.put(222353, "身份证号码格式错误");
        ERROR_CODE_MAP.put(222354, "公安库里不存在此身份证号");
        ERROR_CODE_MAP.put(222355, "身份证号码正确，公安库里没有对应的照片");
        ERROR_CODE_MAP.put(222360, "身份证号码或名字非法（公安网校验不通过）");
        ERROR_CODE_MAP.put(222361, "系统繁忙");

        // 223100-223199 用户组/用户管理错误
        ERROR_CODE_MAP.put(223100, "操作的用户组不存在");
        ERROR_CODE_MAP.put(223101, "该用户组已存在");
        ERROR_CODE_MAP.put(223102, "该用户已存在");
        ERROR_CODE_MAP.put(223103, "找不到该用户");
        ERROR_CODE_MAP.put(223104, "group_list包含组数量过多");
        ERROR_CODE_MAP.put(223105, "该人脸已存在");
        ERROR_CODE_MAP.put(223106, "该人脸不存在");
        ERROR_CODE_MAP.put(223110, "uid_list包含数量过多");
        ERROR_CODE_MAP.put(223111, "目标用户组不存在");
        ERROR_CODE_MAP.put(223112, "quality_conf格式不正确");
        ERROR_CODE_MAP.put(223113, "人脸有被遮挡");
        ERROR_CODE_MAP.put(223114, "人脸模糊");
        ERROR_CODE_MAP.put(223115, "人脸光照不好");
        ERROR_CODE_MAP.put(223116, "人脸不完整");
        ERROR_CODE_MAP.put(223117, "app_list包含app数量过多");
        ERROR_CODE_MAP.put(223118, "质量控制项错误");
        ERROR_CODE_MAP.put(223119, "活体控制项错误");
        ERROR_CODE_MAP.put(223120, "活体检测未通过");
        ERROR_CODE_MAP.put(223121, "质量检测未通过 左眼遮挡程度过高");
        ERROR_CODE_MAP.put(223122, "质量检测未通过 右眼遮挡程度过高");
        ERROR_CODE_MAP.put(223123, "质量检测未通过 左脸遮挡程度过高");
        ERROR_CODE_MAP.put(223124, "质量检测未通过 右脸遮挡程度过高");
        ERROR_CODE_MAP.put(223125, "质量检测未通过 下巴遮挡程度过高");
        ERROR_CODE_MAP.put(223126, "质量检测未通过 鼻子遮挡程度过高");
        ERROR_CODE_MAP.put(223127, "质量检测未通过 嘴巴遮挡程度过高");

        // 222900-222999 系统繁忙错误
        for (int i = 222901; i <= 222916; i++) {
            ERROR_CODE_MAP.put(i, "系统繁忙");
        }

        ERROR_MSG_MAP.put("SDK100", "图片大小超限");
        ERROR_MSG_MAP.put("SDK101", "图片边长不符合要求");
        ERROR_MSG_MAP.put("SDK102", "读取图片文件错误");
        ERROR_MSG_MAP.put("SDK108", "连接超时");
        ERROR_MSG_MAP.put("SDK109", "不支持的图片格式");

    }

    public static JSONObject handleResult(JSONObject result) throws BaiduApiException {
        log.info(result.toString());
        if (result == null) {
            throw new BaiduApiException(-1, "API返回结果为空");
        }

        // 检查是否存在错误码
        if (result.has("error_code")) {
            String errorCodeStr = result.optString("error_code");
            String errorMsg = result.optString("error_msg", "未知错误");
            Integer errorCodeInt = null;
            String friendlyMsg = "";

            // 尝试解析为整数错误码
            try {
                errorCodeInt = result.getInt("error_code");
            } catch (JSONException e) {
                // 解析失败说明是纯字符串错误码
            }

            // 优先使用字符串错误码查找预定义信息
            if (ERROR_MSG_MAP.containsKey(errorCodeStr)) {
                friendlyMsg = ERROR_MSG_MAP.get(errorCodeStr);
            }
            // 如果是整数错误码且存在预定义信息
            else if (errorCodeInt != null && ERROR_CODE_MAP.containsKey(errorCodeInt)) {
                friendlyMsg = ERROR_CODE_MAP.get(errorCodeInt);
            }
            // 都不匹配时生成默认错误信息
            else if (errorCodeInt != null && errorCodeInt == 0){
                return result;
            }

            // 抛出异常（确保使用正确的整数错误码）
            throw new BaiduApiException(
                    errorCodeInt != null ? errorCodeInt : -1,  // 非整数错误码用-1表示
                    friendlyMsg
            );
        }

        // 成功返回原始数据
        return result;
    }

}