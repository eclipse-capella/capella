/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.transition.common.handlers.log;

public interface IDiffModelType {

  public enum DiffType {
    Element, Reference, Attribute
  }

  public enum DiffScope {
    Source, Target
  }

  public static class DiffAction {

    public final static int NO_ACTION = 0;

    public final static int TARGET_MERGE = 1;

    public final static String[] DIFFACTION_TEXT = { "No predefined Action", "Propagation" };
    public final static String[] DIFFACTION_TEXT2 = { "No predefined Action", "Remove from Target" };

    public static String getText(IDiffModelViewer diff) {
      if (diff.getScopeDiff() == DiffScope.Target) {
        return DIFFACTION_TEXT2[1];
      }
      return DIFFACTION_TEXT[1];
    }

  }

}
