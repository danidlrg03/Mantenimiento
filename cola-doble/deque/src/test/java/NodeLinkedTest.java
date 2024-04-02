import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mps.deque.*;

import static org.junit.jupiter.api.Assertions.*;
public class NodeLinkedTest  {
    
    
    @Nested 
    @DisplayName("Tests for set() and get() methods")
    class setTests{
        @Test
        @DisplayName("Tests setItem() for a non empty list") 
        void testSetNonEmptyList(){
        LinkedNode<String> node = new LinkedNode<>("first", null, null);
        node.setItem("updated");
        String expectedValue = "updated";
        String obtainedValue = node.getItem();
        assertEquals(expectedValue, obtainedValue);                   
        }

        @Test
        @DisplayName("Tests getItem() for a null list") 
        void testGetNullList(){
        LinkedNode<String> node = new LinkedNode<>(null, null, null);
        String obtainedValue = node.getItem();
        assertNull(obtainedValue);                   
        }
    }
    @Nested 
    @DisplayName("Tests for first, last, previous, next and terminal methods")
    class nodeTests{
        @Test
        @DisplayName("Test for isFirstNode that should return true") 
        void testisFirstNodeTrue(){
        LinkedNode<String> node = new LinkedNode<>("first", null, null);        
        boolean obtainedValue = node.isFirstNode();
        assertTrue(obtainedValue);
        }
        @Test
        @DisplayName("Test for isFirstNode that should return false") 
        void testisFirstNodeFalse(){
        LinkedNode<String> node = new LinkedNode<>("first", null, null);
        LinkedNode<String> nextNode = new LinkedNode<>("last", node, null);
        boolean obtainedValue = nextNode.isFirstNode();
        assertFalse(obtainedValue);
        }
        @Test
        @DisplayName("Test for isLastNode that should return true") 
        void testisLastNodeTrue(){
        LinkedNode<String> node = new LinkedNode<>("first", null, null);     
        boolean obtainedValue = node.isLastNode();   
        assertTrue(obtainedValue);
        }
        @Test
        @DisplayName("Test for isLastNode that should return false") 
        void testisLastNodeFalse(){
        LinkedNode<String> node1 = new LinkedNode<>("first", null, null);        
        LinkedNode<String> node2 = new LinkedNode<>("second", null, node1);                        
        boolean obtainedValue = node2.isLastNode();  
        assertFalse(obtainedValue);
        }
        @Test
        @DisplayName("Test for isTerminalNode that should return false") 
        void testNotTerminalNodeFalse(){
            LinkedNode<String> node1 = new LinkedNode<>("first", null, null);        
            LinkedNode<String> node2 = new LinkedNode<>("second", node1, null);                        
            boolean obtainedValue = node2.isNotATerminalNode();                      
            assertFalse(obtainedValue);
        }
        @Test
        @DisplayName("Test for isTerminalNode that should return false") 
        void testNotTerminalNodeFalse2(){
            LinkedNode<String> node1 = new LinkedNode<>("first", null, null);                                           
            boolean obtainedValue = node1.isNotATerminalNode();      
            assertFalse(obtainedValue);
        }
        @Test
        @DisplayName("Test for isTerminalNode that should return true") 
        void testNotTerminalNodeTrue(){
            LinkedNode<String> node1 = new LinkedNode<>("first", null, null);        
            LinkedNode<String> node2 = new LinkedNode<>("second", node1, null);        
            LinkedNode<String> node3 = new LinkedNode<>("third", node2, node1);    
            boolean obtainedValue = node3.isNotATerminalNode();      
            assertTrue(obtainedValue);
        }        
    }
}
