/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.search;

public class CapellaSearchMatchOccurrence {
  private final int offset;
  private final int length;

  public CapellaSearchMatchOccurrence(int offset, int length) {
    this.offset = offset;
    this.length = length;
  }

  public int getOffset() {
    return offset;
  }

  public int getLength() {
    return length;
  }

  @Override
  public boolean equals(Object arg0) {
    if (arg0 == this) {
      return true;
    }

    if (!(arg0 instanceof CapellaSearchMatchOccurrence)) {
      return false;
    }

    CapellaSearchMatchOccurrence that = (CapellaSearchMatchOccurrence) arg0;

    if (this.length != that.length) {
      return false;
    }

    if (this.offset != that.offset) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 3;
    hashCode += hashCode * 5 + length;
    hashCode += hashCode * 5 + offset;
    return hashCode;
  }
}
