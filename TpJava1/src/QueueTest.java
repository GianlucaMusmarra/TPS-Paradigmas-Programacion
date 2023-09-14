import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

public class QueueTest {

  public static final String EXPECTED_ERROR_WAS_NOT_THROWN = "Expected Error was not thrown.";
  public static final String QUEUE_IS_EMPTY = "Queue is empty";
  public static final String SOMETHING = "Something";

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
    String addedObject = SOMETHING;
    queue.add( addedObject );
    
    assertEquals( addedObject, queue.take() );
  }

  @Test public void test06QueueBehavesFIFO() {
    Queue queue = new Queue();
    String firstAddedObject = "First";
    String secondAddedObject = "Second";

    queue.add( firstAddedObject );
    queue.add( secondAddedObject );

    assertEquals( queue.take(), firstAddedObject );
    assertEquals( queue.take(), secondAddedObject );
    assertTrue( queue.isEmpty() );
  }

  @Test public void test07HeadReturnsFirstAddedObject() {
    Queue queue = new Queue();
    String firstAddedObject = "First";

    queue.add( firstAddedObject );
    queue.add( "Second" );

    assertEquals( queue.head(), firstAddedObject );
  }

  @Test public void test08HeadDoesNotRemoveObjectFromQueue() {
    Queue queue = new Queue();
    queue.add(SOMETHING);
    assertEquals( 1, queue.size() );
    queue.head();
    assertEquals( 1, queue.size() );
  }

  @Test public void test09SizeRepresentsObjectInTheQueue() {
    assertEquals( 2, new Queue().add( "First" ).add( "Second" ).size() );
  }

  @Test public void test10CanNotTakeWhenThereAreNoObjectsInTheQueue() {
    Queue queue = new Queue();
    try {
      queue.take();
      fail(EXPECTED_ERROR_WAS_NOT_THROWN);
    } catch (Error e) {
      assertTrue( e.getMessage().equals(QUEUE_IS_EMPTY) );
    }
  }

  @Test public void test09CanNotTakeWhenThereAreNoObjectsInTheQueueAndTheQueueHadObjects() {
    Queue queue = new Queue();
    queue.add(SOMETHING);
    queue.take();
    try {
      queue.take();
      fail(EXPECTED_ERROR_WAS_NOT_THROWN);
    } catch (Error e) {
      assertTrue( e.getMessage().equals(QUEUE_IS_EMPTY) );
    }
  }

  @Test public void test10CanNotHeadWhenThereAreNoObjectsInTheQueue() {
    Queue queue = new Queue();



    try {
      queue.head();
      fail(EXPECTED_ERROR_WAS_NOT_THROWN);
    } catch (Error e) {
      assertTrue( e.getMessage().equals(QUEUE_IS_EMPTY) );
    }
  }

  public void assetThrowsLike(){





  }
}