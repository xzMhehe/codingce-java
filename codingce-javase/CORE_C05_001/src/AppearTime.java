/**
 * @author xzMa
 */
public class AppearTime {

    interface IClear {
        void clear();
    }

    class MyClear implements IClear {
        @Override
        public void clear() {
            System.out.println("do clear...");
        }
    }

    public static void main(String[] args) {
        new AppearTime().new MyClear().clear();
    }

}


