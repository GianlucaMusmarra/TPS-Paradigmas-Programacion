import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import static org.junit.jupiter.api.Assertions.*;
public class QueueTest {

  public static final String SOMETHING = "Something";
  public static final String FIRST = "First";
  public static final String SECOND = "Second";

  @Test
  public void test01QueueShouldBeEmptyWhenCreated() {
    assertTrue( new Queue().isEmpty() );
  }

  @Test public void test02AddElementsToTheQueue() {
    assertFalse( new Queue().add(SOMETHING).isEmpty() );
  }

  @Test public void test03AddedElementsIsAtHead() {
    assertEquals(SOMETHING, new Queue().add(SOMETHING).head() );
  }

  @Test public void test04TakeRemovesElementsFromTheQueue() {
    Queue queue = new Queue().add(SOMETHING);
    queue.take();
    
    assertTrue( queue.isEmpty() );
  }

  @Test public void test05TakeReturnsLastAddedObject() {
    Queue queue = new Queue();
    queue.add( SOMETHING );
    assertEquals( SOMETHING, queue.take() );
  }

  @Test public void test06QueueBehavesFIFO() {
    Queue queue = new Queue();


    queue.add( FIRST );
    queue.add( SECOND );


    assertEquals( queue.take(), FIRST);
    assertEquals( queue.take(), SECOND);
    assertTrue( queue.isEmpty() );
  }


  @Test public void test07HeadReturnsFIRST() {
    Queue queue = new Queue();

    queue.add( FIRST );
    queue.add(SECOND);

    assertEquals( queue.head(), FIRST );
  }

  @Test public void test08HeadDoesNotRemoveObjectFromQueue() {
    Queue queue = new Queue();
    queue.add(SOMETHING);
    assertEquals( 1, queue.size() );
    queue.head();
    assertEquals( 1, queue.size() );
  }

  @Test public void test09SizeRepresentsObjectInTheQueue() {
    assertEquals( 2, new Queue().add(FIRST).add(SECOND).size() );
  }

  @Test public void test10CanNotTakeWhenThereAreNoObjectsInTheQueue() {
    assertThrowsLike(() -> new Queue().take(), Queue.QUEUE_IS_EMPTY);
  }

  @Test public void test09CanNotTakeWhenThereAreNoObjectsInTheQueueAndTheQueueHadObjects() {
    Queue queue = new Queue();
    queue.add(SOMETHING);
    queue.take();

    assertThrowsLike(() -> queue.take(), Queue.QUEUE_IS_EMPTY);

  }

  @Test public void test10CanNotHeadWhenThereAreNoObjectsInTheQueue() {
    Queue queue = new Queue();

    assertThrowsLike(() -> queue.head(), Queue.QUEUE_IS_EMPTY);
  }

  private void assertThrowsLike(Executable executable, String message ) {
    assertEquals( message,
            assertThrows( Error.class, executable).getMessage() ); }
}

