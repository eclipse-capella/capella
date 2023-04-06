package org.polarsys.capella.common.helpers.cache;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

public class Triplet<O1, O2, O3> {

  private O1 o1;
  private O2 o2;
  private O3 o3;

  /**
   * Constructor
   * 
   * @param o1
   * @param o2
   */
  public Triplet(O1 o1, O2 o2, O3 o3) {
    this.o1 = o1;
    this.o2 = o2;
    this.o3 = o3;
  }

  @Override
  public int hashCode() {
    int hash1 = o1.getClass().getName().hashCode();
    int hash2 = o2.hashCode();
    int hash3 = o3.hashCode();

    int result = 1;
    result = 31 * result + (o1 == null ? 0 : hash1);
    result = 31 * result + (o2 == null ? 0 : hash2);
    result = 31 * result + (o3 == null ? 0 : hash3);

    return result;
  }

  @SuppressWarnings("rawtypes")
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Triplet other = (Triplet) obj;
    if (o1 == null) {
      if (other.o1 != null)
        return false;
    } else if (!o1.getClass().getName().equals(other.o1.getClass().getName())) {
      return false;
    }
    if (o2 == null) {
      if (other.o2 != null)
        return false;
    } else if (!o2.equals(other.o2)) {
      return false;
    }
    if (o3 == null) {
      if (other.o3 != null)
        return false;
    } else if (!o3.equals(other.o3)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    String keyString = (null != o1) ? o1.toString() : ICommonConstants.EMPTY_STRING;
    String value1String = (null != o2) ? o2.toString() : ICommonConstants.EMPTY_STRING;
    String value2String = (null != o3) ? o3.toString() : ICommonConstants.EMPTY_STRING;
    return new StringBuffer(keyString).append(ICommonConstants.PARENTHESIS_OPEN_CHARACTER).append(value1String)
        .append(ICommonConstants.COMMA_CHARACTER).append(value2String)
        .append(ICommonConstants.PARENTHESIS_CLOSE_CHARACTER).toString();
  }
  
  public O1 getFirst() {
	  return o1;
  }
  
  public O2 getSecond() {
	  return o2;
  }
  
  public O3 getThird() {
	  return o3;
  }
}
