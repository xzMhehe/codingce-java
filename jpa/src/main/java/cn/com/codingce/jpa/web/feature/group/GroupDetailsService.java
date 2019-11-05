package cn.com.codingce.jpa.web.feature.group;


import cn.com.coding4fun.hibernate.entity.GroupDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GroupDetailsService {

    @Autowired
    private GroupDetailsRepository repository;

    @Autowired
    private GroupDetailsUserRepository groupDetailsUserRepository;

    /**
     * 分页显示教师创建的分组
     * @param title 分组名称
     * @param uid 用户主键
     * @param pageable 分页
     * @return
     */
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public Page<GroupDetails> findAllByPage(String uid, String title, LocalDateTime startTime, LocalDateTime endTime, Pageable pageable) {
        return repository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            predicateList.add(criteriaBuilder.equal(root.get("userPid"), uid));
            if (startTime != null || endTime != null) {
                predicateList.add(criteriaBuilder.between(root.get("createTime"), startTime, endTime));
            }
            if (title != null && !title.isEmpty()) {
                predicateList.add(criteriaBuilder.like(root.get("groupName"), "%" + title + "%"));
            }
            if (predicateList.size() > 0) {
                Predicate[] predicates = new Predicate[predicateList.size()];
                for (int i = 0; i < predicates.length; i++) {
                    predicates[i] = predicateList.get(i);
                }
                query.where(predicates);
            }
            query.orderBy(criteriaBuilder.desc(root.get("createTime")));
            return query.getRestriction();
        }, pageable);
    }

    /**
     * 查询创建人创建每个分组的人数Map集合 String分组主键
     * @param usrPid 创建人pid
     * @return
     */
    @Transactional(readOnly = true)
    public Map<String,Long> userNum(String usrPid) {
        Map<String, Long> map = repository.userNum(usrPid)
                .collect(Collectors.toMap(arr->(String) arr[0], arr -> (long) arr[1]));
        return map;
    }

    /**
     * 查询该分组人数
     * @param pid
     * @return
     */
    @Transactional(readOnly = true)
    public long countByGroupDetailsPid(String pid) {
        return groupDetailsUserRepository.countByGroupDetailsPid(pid);
    }

    /**
     * 判断分组名称在机构下是否存在
     * @param groupName 分组名称
     * @param orgPid 分组所在的机构主键
     * @return
     */
    @Transactional(readOnly = true)
    public Long countByGroupNameAndOrgPid(String groupName, String orgPid) {
        return repository.countByGroupNameAndOrgPid(groupName, orgPid);
    }

    /**
     * 添加或修改分组信息
     * @param groupDetails 分组信息的实体类
     */
    @Transactional(rollbackFor = Exception.class)
    public void save(GroupDetails groupDetails) {
        repository.save(groupDetails);
    }

    /**
     * 删除分组信息
     * @param pid 分组信息主键
     */
    @Transactional(rollbackFor = Exception.class)
    public void delete(String pid) {
        repository.deleteById(pid);
    }


    /**
     * 通过主键获取分组信息实体类
     * @param id 分组的主键
     * @return 分组信息实体类
     */
    @Transactional(readOnly = true)
    public Optional<GroupDetails> findById(String id) {
        return repository.findById(id);
    }

}
