package cn.com.codingce.codespring.entity;

/**
 * @author 2460798168@qq.com
 * @date 2019/12/25 12:54
 */
public class InjectTextEditor {

    private InjectSpellChecker injectSpellChecker;

    public void setInjectSpellChecker(InjectSpellChecker injectSpellChecker) {
        System.out.println("Inside setSpellChecker." );
        this.injectSpellChecker = injectSpellChecker;
    }

    public InjectSpellChecker getInjectSpellChecker() {
        return injectSpellChecker;
    }

    public void injectSpellChecker() {
        injectSpellChecker.checkSpelling();
    }

}
