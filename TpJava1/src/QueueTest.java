import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import static org.junit.jupiter.api.Assertions.*;
public class QueueTest {

  public static final String SOMETHING = "Something";
  public static final String FIRST = "First";
  public static final String SECOND = "Second";
  private Queue queue = new Queue();

  @BeforeEach
    public void init() {this.queue = new Queue();}
  @Test
  public void test01QueueShouldBeEmptyWhenCreated() {
    assertTrue( queue.isEmpty() );
  }

  @Test public void test02AddElementsToTheQueue() {
    assertFalse( queue.add(SOMETHING).isEmpty() );
  }
  @Test public void test03AddedElementsIsAtHead() {assertEquals(SOMETHING, queue.add(SOMETHING).head() );}
  @Test public void test04TakeRemovesElementsFromTheQueue() {
    queue.add(SOMETHING);
    queue.take();
    
    assertTrue( queue.isEmpty() );
  }

  @Test public void test05TakeReturnsLastAddedObject() {
    queue.add( SOMETHING );

    assertEquals( SOMETHING, queue.take() );
  }

  @Test public void test06QueueBehavesFIFO() {
    AddsTwoElementsToQueue();

    assertEquals( queue.take(), FIRST);
    assertEquals( queue.take(), SECOND);
    assertTrue( queue.isEmpty() );
  }




  @Test public void test07HeadReturnsFIRST() {
    AddsTwoElementsToQueue();

    assertEquals( queue.head(), FIRST );
  }

  @Test public void test08HeadDoesNotRemoveObjectFromQueue() {
    queue.add(SOMETHING);

    assertEquals( 1, queue.size() );
    queue.head();
    assertEquals( 1, queue.size() );
  }

  @Test public void test09SizeRepresentsObjectInTheQueue() {assertEquals( 2, queue.add(FIRST).add(SECOND).size() );}
  @Test public void test10CanNotTakeWhenThereAreNoObjectsInTheQueue() {assertThrowsLike(() -> queue.take(), Queue.QUEUE_IS_EMPTY);}

  @Test public void test09CanNotTakeWhenThereAreNoObjectsInTheQueueAndTheQueueHadObjects() {
    queue.add(SOMETHING);
    queue.take();

    assertThrowsLike(() -> queue.take(), Queue.QUEUE_IS_EMPTY);
  }
  @Test public void test10CanNotHeadWhenThereAreNoObjectsInTheQueue() {assertThrowsLike(() -> queue.head(), Queue.QUEUE_IS_EMPTY);}

  private void assertThrowsLike(Executable executable, String message ) {
    assertEquals( message,  assertThrows( Error.class, executable).getMessage() );
  }
  private void AddsTwoElementsToQueue() {
    queue.add( FIRST );
    queue.add( SECOND );
  }
}

