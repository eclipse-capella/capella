/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.model.helpers.graph;

import org.polarsys.capella.core.data.cs.PhysicalPathInvolvement;

public class PhysicalPathInvolvementLink {

  private PhysicalPathInvolvement involvement;
  private PhysicalPathInvolvement source;
  private PhysicalPathInvolvement target;

  public PhysicalPathInvolvementLink(PhysicalPathInvolvement involvement, PhysicalPathInvolvement source,
      PhysicalPathInvolvement target) {
    this.involvement = involvement;
    this.source = source;
    this.target = target;
  }

  public PhysicalPathInvolvement getInvolvement() {
    return involvement;
  }

  public void setInvolvement(PhysicalPathInvolvement involvement) {
    this.involvement = involvement;
  }

  public PhysicalPathInvolvement getSource() {
    return source;
  }

  public void setSource(PhysicalPathInvolvement source) {
    this.source = source;
  }

  public PhysicalPathInvolvement getTarget() {
    return target;
  }

  public void setTarget(PhysicalPathInvolvement target) {
    this.target = target;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj == null || obj.getClass() != this.getClass()) {
      return false;
    }
    PhysicalPathInvolvementLink ppInvLink = (PhysicalPathInvolvementLink) obj;
    return involvement.equals(ppInvLink.getInvolvement())
        && (source.equals(ppInvLink.getSource()) && target.equals(ppInvLink.getTarget()))
        || (source.equals(ppInvLink.getTarget()) && target.equals(ppInvLink.getSource()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((involvement == null) ? 0 : involvement.hashCode());
    result = prime * result + ((source == null) ? 0 : source.hashCode());
    result = prime * result + ((target == null) ? 0 : target.hashCode());
    return result;
  }
}
