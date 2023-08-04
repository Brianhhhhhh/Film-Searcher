
/**
 * A node class to store key-value pair
 * 
 * @author Haochen Wang
 *
 */
public class Node {
  private String key; // key of the node
  private Object content; // content of the node

  /**
   * constructor for a node object
   * 
   * @param str key
   * @param obj content of the node
   */
  protected Node(String str, Object obj) {
    this.key = str;
    this.content = obj;
  }

  /**
   * getter for the key of the node
   * 
   * @return the key
   */
  protected String getKey() {
    return this.key;
  }

  /**
   * getter for the content of the node
   * 
   * @return content
   */
  protected Object getContent() {
    return this.content;
  }
}
