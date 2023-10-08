import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import static org.junit.jupiter.api.Assertions.*;
public class QueueTest {

  private Queue queue = new Queue();

  @BeforeEach
    public void init() {this.queue = new Queue();}

  @Test public void test01QueueShouldBeEmptyWhenCreated() {
    assertTrue( queue.isEmpty() );
  }

  @Test public void test02AddElementsToTheQueue() {
    queue.add("Something");

    assertFalse( queue.isEmpty() );
  }

  @Test public void test03AddedElementsIsAtHead() {
    queue.add("Something");

    assertEquals("Something", queue.head());
  }

  @Test public void test04TakeRemovesElementsFromTheQueue() {
    queue.add("Something");
    queue.take();

    assertTrue( queue.isEmpty() );
  }


  @Test public void test05TakeReturnsLastAddedObject() {
    queue.add("Something");

    assertEquals("Something", queue.take() );
  }

  @Test public void test06QueueBehavesFIFO() {
    AddsTwoElementsToQueue();

    assertEquals( queue.take(), "First");
    assertEquals( queue.take(), "Second");

    assertTrue( queue.isEmpty() );
  }

  @Test public void test07HeadReturnsFIRST() {
    AddsTwoElementsToQueue();

    assertEquals( queue.head(), "First");
  }

  @Test public void test08HeadDoesNotRemoveObjectFromQueue() {
    queue.add("Something");

    assertEquals( 1, queue.size() );

    queue.head();

    assertEquals( 1, queue.size() );
  }

  @Test public void test09SizeRepresentsObjectInTheQueue() {
    AddsTwoElementsToQueue();

    assertEquals( 2,  queue.size());
  }
  @Test public void test10CanNotTakeWhenThereAreNoObjectsInTheQueue() {assertThrowsLike(() -> queue.take(), Queue.QUEUE_IS_EMPTY);}

  @Test public void test09CanNotTakeWhenThereAreNoObjectsInTheQueueAndTheQueueHadObjects() {
    queue.add("Something");
    queue.take();

    assertThrowsLike(() -> queue.take(), Queue.QUEUE_IS_EMPTY);
  }
  @Test public void test10CanNotHeadWhenThereAreNoObjectsInTheQueue() {assertThrowsLike(() -> queue.head(), Queue.QUEUE_IS_EMPTY);}

  private void assertThrowsLike(Executable executable, String message ) {
    assertEquals( message,  assertThrows(Error.class, executable).getMessage() );
  }
  private void AddsTwoElementsToQueue() {
    queue.add("First");
    queue.add("Second");
  }
}

