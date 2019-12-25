package cn.com.codingce.codespring.entity;

/**
 * 只能用构造函数注入来实现依赖注入。
 *
 * @author 2460798168@qq.com
 * @date 2019/12/25 10:59
 */
public class TextEditor {

    private SpellChecker spellChecker;

    public TextEditor(SpellChecker spellChecker) {
        System.out.println("Inside TextEditor constructor(一个参数构造函数)." );
        this.spellChecker = spellChecker;
    }

    public void spellCheck() {
        spellChecker.checkSpelling();
    }

}
