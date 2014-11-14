/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.ui.toolkit.fields;

import org.eclipse.swt.widgets.Composite;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

/**
 * A field editor used as a separator in a preference page.
 */
public class SpacerFieldEditor extends LabelFieldEditor {
  /**
   * Constructor.<br>
   * Implemented as an empty label field editor.
   */
  public SpacerFieldEditor(Composite parent_p) {
    super(ICommonConstants.EMPTY_STRING, parent_p);
  }
}
