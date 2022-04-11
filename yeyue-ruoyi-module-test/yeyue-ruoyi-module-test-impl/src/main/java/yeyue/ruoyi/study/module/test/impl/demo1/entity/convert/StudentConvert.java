package yeyue.ruoyi.study.module.test.impl.demo1.entity.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import yeyue.ruoyi.study.module.test.api.demo1.domain.StudentDomain;
import yeyue.ruoyi.study.module.test.impl.demo1.entity.StudentEntity;

/**
 * @author yeyue
 * @date 2022-04-11 14:54:06
 */
@Mapper
public interface StudentConvert {

    StudentConvert INSTANCE = Mappers.getMapper(StudentConvert.class);

    StudentDomain convert(StudentEntity entity);

    StudentEntity convert(StudentDomain domain);
}
