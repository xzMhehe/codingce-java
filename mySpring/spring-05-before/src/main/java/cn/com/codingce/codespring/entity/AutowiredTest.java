package cn.com.codingce.codespring.entity;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Spring @Autowired 注释
 *
 * @author 2460798168@qq.com
 * @date 2019/12/26 11:53
 */
public class AutowiredTest {

    private SpellCheckerAwd spellChecker;

    @Autowired
    public void setSpellChecker(SpellCheckerAwd spellChecker) {
        this.spellChecker = spellChecker;
    }

    public AutowiredTest() {
        System.out.println("AutowiredTest的无参构造方法" );
    }

    public SpellCheckerAwd getSpellChecker() {
        System.out.println("getSpellChecker" );
        return spellChecker;
    }

    public void funAutowiredTest() {
        spellChecker.funSpellCheckerAwd();
    }

}