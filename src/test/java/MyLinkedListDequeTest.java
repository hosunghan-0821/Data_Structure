import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import practice.MyLinkedListDeque;

public class MyLinkedListDequeTest {

    @Test
    @DisplayName("Deque Test")
    public void test(){
        MyLinkedListDeque<Integer> myLinkedListDeque = new MyLinkedListDeque<>();

        myLinkedListDeque.offerFirst(1);
        myLinkedListDeque.offerLast(2);

        Assertions.assertThat(myLinkedListDeque.peekFirst()).isEqualTo(1);
        Assertions.assertThat(myLinkedListDeque.peekLast()).isEqualTo(2);


        myLinkedListDeque = new MyLinkedListDeque<>();
        myLinkedListDeque.offerFirst(1);
        myLinkedListDeque.pollFirst();

        Assertions.assertThat(myLinkedListDeque.peekFirst()).isEqualTo(null);

        myLinkedListDeque.offerLast(1);
        myLinkedListDeque.offerLast(2);
        myLinkedListDeque.offerLast(3);

        myLinkedListDeque.pollFirst();
        Assertions.assertThat(myLinkedListDeque.peekFirst()).isEqualTo(2);


    }
}
