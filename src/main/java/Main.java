import java.time.LocalDateTime;

public class Main {


    public static void main(String[] args) throws Exception {

        UserRepository userRepository = new UserRepository();

        User user = new User();
        user.setName("Java 2");
        user.setUserName("Username 2");
        user.setAge(29);
        user.setPassword("asasdasdasdadad2");
        user.setInsertedAtUtc(LocalDateTime.now());
        user.setSurname("Surname 2");

        userRepository.save(user);

    }
}
