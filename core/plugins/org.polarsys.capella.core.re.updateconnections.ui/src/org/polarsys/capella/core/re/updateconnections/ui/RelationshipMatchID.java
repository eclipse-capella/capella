/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *   
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.re.updateconnections.ui;

/**
 * Instances of this class may serve as match ID objects for Diff/Merge match policies, for objects that represent some
 * kind of relationship between two other objects. These objects are called the 'left' and 'right' here. Examplary uses
 * here are:<br>
 * <ul>
 * <li>Trace Objects, where the characterizing objects are the trace source and target element.
 * <li>ComponentExchanges, PhysicalLinks, FunctionalExchanges, where the characterizing objects are the exchange/link
 * source and target ports
 * <li>PhysicalLinkEnds that represent a Port/Part relation to indicate link targets in multipart models.
 * </ul>
 * 
 * Apart from the characterizing left/right objects for an element, an additional grouping object may be provided so
 * that two instances with identical left/right object will only match if their corresponding group object also matches.
 */
public class RelationshipMatchID {

  private final Object group;
  private final Object leftObject;
  private final Object rightObject;

  /**
   * Create a new match ID consisting of the left and right end for a connection.
   * 
   * @param left
   *          a non null object representing the left end of the connection
   * @param right
   *          a non null object representing the right end of the connection
   * @param group
   *          a group object to distinguish match ids with identical left/right ends
   */
  public RelationshipMatchID(Object left, Object right, Object group) {
    this.leftObject = left;
    this.rightObject = right;
    this.group = group;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((group == null) ? 0 : group.hashCode());
    result = prime * result + ((leftObject == null) ? 0 : leftObject.hashCode());
    result = prime * result + ((rightObject == null) ? 0 : rightObject.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    RelationshipMatchID other = (RelationshipMatchID) obj;
    if (group == null) {
      if (other.group != null)
        return false;
    } else if (!group.equals(other.group))
      return false;
    if (leftObject == null) {
      if (other.leftObject != null)
        return false;
    } else if (!leftObject.equals(other.leftObject))
      return false;
    if (rightObject == null) {
      if (other.rightObject != null)
        return false;
    } else if (!rightObject.equals(other.rightObject))
      return false;
    return true;
  }

}