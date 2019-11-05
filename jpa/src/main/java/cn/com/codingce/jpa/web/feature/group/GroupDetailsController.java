package cn.com.codingce.jpa.web.feature.group;

import cn.com.coding4fun.commons.WebStringUtils;
import cn.com.coding4fun.hibernate.entity.GroupDetails;
import cn.com.coding4fun.validation.PrimaryKey;
import cn.com.codingce.jpa.common.Translator;
import cn.com.codingce.jpa.web.base.AbstractAuthorizationDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;
import java.util.Optional;

/**
 * 分组详情控制器
 * @Date 2019/10/23 13:29
 */
@RestController
@Validated
public class GroupDetailsController extends AbstractAuthorizationDetails {

    @Autowired
    private GroupDetailsService service;

    /**
     * 分页显示教师创建的分组
     * @param page
     * @param title 分组名称,默认值为""
     * @param startTime 起始时间
     * @param endTime 结束时间
     * @return
     */
    @GetMapping({"/group/details"})
    public ResponseEntity findAllByPage(@PageableDefault Pageable page,
                                        @RequestParam(required = false) String title,
                                        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startTime,
                                        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endTime) {
        if (startTime != null && endTime != null && startTime.isAfter(endTime)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Translator.toLocale("group.time.Failed.message"));
        }
        LocalDateTime startLocalDateTime = null;
        LocalDateTime endLocalDateTime = null;
        if (startTime != null || endTime != null) {
            startLocalDateTime = LocalDateTime.of(startTime, LocalTime.of(0, 0));
            endLocalDateTime = LocalDateTime.of(endTime, LocalTime.of(23, 59));
        }
        Map<String, Long> countUsrs = service.userNum(getOperatorId());
        Page<GroupDetails> data = service.findAllByPage(getOperatorId(), title, startLocalDateTime, endLocalDateTime, page);
        Page<GroupDetailsDTO> dataDto = data.map(groupDetails -> new GroupDetailsDTO(
                groupDetails.getPid(),
                groupDetails.getOrgPid(),
                groupDetails.getUserPid(),
                groupDetails.getGroupName(),
                groupDetails.getCreateTime(),
                groupDetails.getGroupDesc(),
                countUsrs.get(groupDetails.getPid())
        ));
        return ResponseEntity.ok(dataDto);
    }

    /**
     * 教师创建分组
     * @param title 分组名称
     * @param desc  分组描述
     * @return HttpStatus.CREATED
     */
    @PostMapping("/group/details")
    public ResponseEntity save(
            @RequestParam @NotEmpty(message = "group.name.NotExists.message") String title,
            @RequestParam(required = false) String desc) {
        if (service.countByGroupNameAndOrgPid(title, getOrgIdOfOperator()).compareTo(0L) != 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Translator.toLocale("group.name.Exists.message"));
        }
        GroupDetails groupDetails = new GroupDetails();
        groupDetails.setOrgPid(getOrgIdOfOperator());
        groupDetails.setUserPid(getOperatorId());
        groupDetails.setGroupName(title);
        groupDetails.setGroupDesc(desc);
        service.save(groupDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(Translator.toLocale("system.operator.success.message"));
    }

    /**
     * 修改分组信息
     * @param id    分组主键
     * @param title 分组名称
     * @param desc  分组描述
     * @return HttpStatus.ACCEPTED
     */
    @PutMapping("/group/details")
    public ResponseEntity update(
            @RequestParam @PrimaryKey(message = "group.pid.NotExists.message") String id,
            @RequestParam @NotEmpty(message = "group.name.NotExists.message") String title,
            @RequestParam(required = false) String desc) {
        Optional<GroupDetails> groupDetailsOptional = service.findById(id);
        GroupDetails groupDetails = groupDetailsOptional.orElseThrow(
                () -> new BusinessException(HttpStatus.BAD_REQUEST, Translator.toLocale("group.NotExists.message")));
        if (!groupDetails.getGroupName().equals(title)
                && service.countByGroupNameAndOrgPid(title, getOrgIdOfOperator()).compareTo(0L) != 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Translator.toLocale("group.name.Exists.message"));
        }
        groupDetails.setGroupName(title);
        if (WebStringUtils.isValid(desc)) {
            groupDetails.setGroupDesc(desc);
        }
        service.save(groupDetails);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(Translator.toLocale("system.operator.success.message"));
    }

    /**
     * 删除分组
     * @param id 分组信息主键
     * @return HttpStatus.ACCEPTED
     */
    @DeleteMapping("/group/details")
    public ResponseEntity delete(@RequestParam @PrimaryKey(message = "group.pid.NotExists.message") String id) {
        Optional<GroupDetails> groupDetailsOptional = service.findById(id);
        groupDetailsOptional.orElseThrow(
                () -> new BusinessException(HttpStatus.BAD_REQUEST, Translator.toLocale("group.NotExists.message")));
        if (service.countByGroupDetailsPid(id) > 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("group.user.Exists.message");
        }
        service.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(Translator.toLocale("system.operator.success.message"));
    }
}
