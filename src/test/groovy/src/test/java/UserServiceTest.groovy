import com.example.locationsystem.service.UserService
import com.example.locationsystem.dto.UserDTO
import com.example.locationsystem.model.User
import com.example.locationsystem.repository.UserRepository
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class UserServiceTest extends Specification {

    UserRepository userRepository = Mock()

    @Subject
    UserService userService = new UserService(userRepository)

    def "should create a user if email does not exist"() {
        given: "A new user with a unique email"
        String newEmail = "newuser@example.com"
        UserDTO newUserDto = new UserDTO(email: newEmail, name: "New User")

        when: "The user is created"
        UserDTO createdUser = userService.createUser(newUserDto)

        then: "The repository is checked for the existence of the email"
        1 * userRepository.findByEmail(newEmail) >> Optional.empty()

        and: "The user is saved in the repository"
        1 * userRepository.save(_ as User) >> { User user ->
            user.id = 1L
            return user
        }

        and: "The created user's email matches the new email"
        createdUser.email == newEmail
    }

    @Unroll
    def "should throw an exception if email '#email' already exists"() {
        given: "A user with an existing email in the repository"
        User existingUser = new User(email: email, name: "Existing User")
        userRepository.findByEmail(email) >> Optional.of(existingUser)

        when: "A new user tries to register with the same email"
        userService.createUser(new UserDTO(email: email, name: "Another User"))

        then: "An exception is thrown indicating that the email already exists"
        def e = thrown(IllegalArgumentException)
        e.message == "Email already exists!"

        where:
        email << ["existinguser@example.com", "anotheruser@example.com"]
    }
}