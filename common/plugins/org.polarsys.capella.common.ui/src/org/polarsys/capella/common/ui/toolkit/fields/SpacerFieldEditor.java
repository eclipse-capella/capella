/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
  public SpacerFieldEditor(Composite parent) {
    super(ICommonConstants.EMPTY_STRING, parent);
  }
}
