package yeyue.ruoyi.study.module.test.impl.demo1.entity.convert;

import yeyue.ruoyi.study.module.test.api.demo1.domain.StudentDomain;
import yeyue.ruoyi.study.module.test.api.demo1.enums.GenderEnum;
import yeyue.ruoyi.study.module.test.impl.demo1.entity.StudentEntity;

import java.util.*;

/**
 * @author yeyue
 * @date 2022-04-10 18:44:25
 */
public class StudentConvert {


    private StudentConvert() {
        // 无需实现
    }

    public static List<StudentDomain> toStudentDomainList(List<StudentEntity> studentEntityList) {
        if (studentEntityList == null) {
            return Collections.emptyList();
        }
        List<StudentDomain> studentDomainList = new ArrayList<>();
        for (StudentEntity studentEntity : studentEntityList) {
            studentDomainList.add(toStudentDomain(studentEntity));
        }
        return studentDomainList;
    }

    public static StudentDomain toStudentDomain(StudentEntity studentEntity) {
        if (studentEntity == null) {
            return null;
        }
        StudentDomain studentDomain = new StudentDomain();
        studentDomain.setId(studentEntity.getId());
        studentDomain.setName(studentEntity.getName());
        studentDomain.setIdCard(studentEntity.getIdCard());
        studentDomain.setMobile(studentEntity.getMobile());
        studentDomain.setBirthday(studentEntity.getBirthday());
        studentDomain.setGraduate(studentEntity.getGraduate());
        studentDomain.setGrade(studentEntity.getGrade());
        studentDomain.setAwardNumber(studentEntity.getAwardNumber());
        studentDomain.setDescription(studentEntity.getDescription());
        studentDomain.setCreateTime(studentEntity.getCreateTime());
        studentDomain.setUpdateTime(studentEntity.getUpdateTime());
        studentDomain.setGender(studentEntity.getGender().name());
// Not mapped TO fields:
// Not mapped FROM fields:
// creator
// updater
// deleted
        return studentDomain;
    }

    public static List<StudentEntity> toStudentEntityList(List<StudentDomain> studentDomainList) {
        if (studentDomainList == null) {
            return Collections.emptyList();
        }
        List<StudentEntity> studentEntityList = new ArrayList<>();
        for (StudentDomain studentDomain : studentDomainList) {
            studentEntityList.add(toStudentEntity(studentDomain));
        }
        return studentEntityList;
    }

    public static StudentEntity toStudentEntity(StudentDomain studentDomain) {
        if (studentDomain == null) {
            return null;
        }
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(studentDomain.getId());
        studentEntity.setName(studentDomain.getName());
        studentEntity.setIdCard(studentDomain.getIdCard());
        studentEntity.setMobile(studentDomain.getMobile());
        studentEntity.setBirthday(studentDomain.getBirthday());
        studentEntity.setGraduate(studentDomain.getGraduate());
        studentEntity.setGrade(studentDomain.getGrade());
        studentEntity.setAwardNumber(studentDomain.getAwardNumber());
        studentEntity.setDescription(studentDomain.getDescription());
        studentEntity.setGender(GenderEnum.valueOf(studentDomain.getGender()));
// Not mapped TO fields:
// creator
// updater
// deleted
// Not mapped FROM fields:
        return studentEntity;
    }
}
