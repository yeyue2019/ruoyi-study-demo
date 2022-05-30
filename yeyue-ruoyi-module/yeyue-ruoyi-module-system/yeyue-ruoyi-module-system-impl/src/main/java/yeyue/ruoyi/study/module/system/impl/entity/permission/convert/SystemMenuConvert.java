package yeyue.ruoyi.study.module.system.impl.entity.permission.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import yeyue.ruoyi.study.framework.common.util.collection.CollectionUtils;
import yeyue.ruoyi.study.module.system.api.domain.permission.SystemMenuDomain;
import yeyue.ruoyi.study.module.system.api.enums.permission.MenuIdEnum;
import yeyue.ruoyi.study.module.system.api.service.permission.dto.SystemMenuCreateReqDTO;
import yeyue.ruoyi.study.module.system.api.service.permission.dto.SystemMenuUpdateReqDTO;
import yeyue.ruoyi.study.module.system.impl.controller.auth.vo.SystemAuthMenuRespVO;
import yeyue.ruoyi.study.module.system.impl.entity.permission.SystemMenuEntity;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yeyue
 * @date 2022-05-23 15:13:08
 */
@Mapper
public interface SystemMenuConvert {
    SystemMenuConvert INSTANCE = Mappers.getMapper(SystemMenuConvert.class);

    SystemMenuEntity toEntity(SystemMenuDomain domain);

    SystemMenuDomain toDomain(SystemMenuEntity entity);

    SystemMenuEntity toEntity(SystemMenuCreateReqDTO reqDTO);

    SystemMenuEntity toEntity(SystemMenuUpdateReqDTO reqDTO);

    SystemAuthMenuRespVO toVO(SystemMenuDomain domain);

    default List<SystemAuthMenuRespVO> buildMenuTree(Set<SystemMenuDomain> menuList) {

        Map<Long, List<SystemAuthMenuRespVO>> tree = menuList.stream().map(INSTANCE::toVO).collect(Collectors.groupingBy(SystemAuthMenuRespVO::getParentId));
        List<SystemAuthMenuRespVO> result = new ArrayList<>();
        getMenuByParentId(result, MenuIdEnum.ROOT.getId(), Integer.MAX_VALUE, tree);
        return result;
    }

    default void getMenuByParentId(List<SystemAuthMenuRespVO> result, Long parentId, int recursiveCount,
                                   Map<Long, List<SystemAuthMenuRespVO>> tree) {
        // 递归次数为 0，结束！
        if (recursiveCount == 0) {
            return;
        }
        // 获得子菜单
        Collection<SystemAuthMenuRespVO> depts = tree.get(parentId);
        if (CollectionUtils.isEmpty(depts)) {
            return;
        }
        result.addAll(depts);
        result.sort(Comparator.comparing(SystemAuthMenuRespVO::getSort));
        // 继续递归
        depts.forEach(dept -> {
            dept.setChildren(new ArrayList<>());
            getMenuByParentId(dept.getChildren(), dept.getId(), recursiveCount - 1, tree);
        });
    }
}
