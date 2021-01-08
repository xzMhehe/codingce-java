public class TestThree {
    public static void main(String[] args) {
        try{
            Runtime run = Runtime.getRuntime();
            run.exec("C:\\Program Files (x86)\\Microsoft Office\\root\\Office16\\WINWORD.EXE f:\\test.docx");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
