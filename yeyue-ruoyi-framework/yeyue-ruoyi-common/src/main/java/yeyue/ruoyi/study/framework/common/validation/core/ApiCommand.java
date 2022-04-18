package yeyue.ruoyi.study.framework.common.validation.core;

import javax.validation.groups.Default;

/**
 * 通用Api操作
 *
 * @author yeyue
 * @date 2022-04-18 14:18:37
 */
public interface ApiCommand {

    interface Create extends Default {
    }

    interface Update extends Default {
    }

    interface Get extends Default {
    }

    interface Delete extends Default {
    }

    interface List extends Default {
    }

    interface Other extends Default {
    }
}
