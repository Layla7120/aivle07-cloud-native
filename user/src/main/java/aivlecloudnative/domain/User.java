package aivlecloudnative.domain;

import aivlecloudnative.UserApplication;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Entity
@Table(name = "User_table")
@Data
//<<< DDD / Aggregate Root
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String userName;

    private Boolean hasActiveSubscription;

    private String message;

    private Long subscriptionDueDate;

    @ElementCollection
    private List<Long> myBookHistory;

    private Boolean isKt;

    public static UserRepository repository() {
        return UserApplication.applicationContext.getBean(
            UserRepository.class
        );
    }

    //<<< Clean Arch / Port Method
    public void signUp(SignUpCommand signUpCommand) {
        this.userName = signUpCommand.getUserName();
        this.email = signUpCommand.getEmail();
        this.isKt = signUpCommand.getIsKt();
    }

    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public void addBookToHistory(Long bookId) {
        if (this.myBookHistory == null) {
            this.myBookHistory = new ArrayList<>();
        }

        if (!this.myBookHistory.contains(bookId)) {
            this.myBookHistory.add(bookId);
        }
    }

    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public static void updateBookRead(BookViewed bookViewed) {
        //implement business logic here:

        /** Example 1:  new item 
        User user = new User();
        repository().save(user);

        */

        /** Example 2:  finding and process
        

        repository().findById(bookViewed.get???()).ifPresent(user->{
            
            user // do something
            repository().save(user);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
