package com.funtl.myshop.commons.dto;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 通用响应结构工厂
 *
 * @author YangBo
 * @date 2019/02/23
 */
public class BaseResultFactory<T extends AbstractBaseDomain> {

    private static final String LOGGER_LEVEL_DEBUG = "DEBUG";

    private static BaseResultFactory baseResultFactory;

    private BaseResultFactory() {

    }

    // 单例模式保证静态的全局唯一工厂
    public static BaseResultFactory getInstance() {
        if (baseResultFactory == null) {
            synchronized (BaseResultFactory.class) {
                if (baseResultFactory == null) {
                    baseResultFactory = new BaseResultFactory();
                }
            }
        }
        return baseResultFactory;
    }

    /**
     * 构建单笔数据结果集
     *
     * @param self
     * @return
     */
    public AbstractBaseResult build(String self, T attributes) {
        return new SuccessResult(self, attributes);
    }

    /**
     * 构建多笔数据的结果集
     *
     * @param self
     * @param next
     * @param last
     * @return
     */
    public AbstractBaseResult build(String self, int next, int last, List<T> attributs) {
        return new SuccessResult(self, next, last, attributs);
    }

    /**
     * 构建请求错误的响应请求
     *
     * @param code
     * @param title
     * @param detail
     * @param level  日志级别 （为debug时显示错误详情）
     * @return
     */
    public static AbstractBaseResult build(int code, String title, String detail, String level) {
        if (LOGGER_LEVEL_DEBUG.equals(level)) {
            return new ErrorResult(code, title, detail);
        } else {
            return new ErrorResult(code, title, null);
        }
    }

}
