import java.time.LocalDateTime;

public class Main {


    public static void main(String[] args) throws Exception {

        UserRepository userRepository = new UserRepository();

        User user = new User();
        user.setName("Java 2");
        user.setUserName("Username 2");


        userRepository.save(user);

    }
}
