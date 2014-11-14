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
package org.polarsys.capella.common.command.recorder.ui.preferences.utils;

import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * Allow to fix the text size as in {@link StringFieldEditor}
 */
public class IntegerFieldEditor2 extends IntegerFieldEditor {
  
  /** For internal use */
  private int _txtWidth;

  /**
   * Constructor
   */
  public IntegerFieldEditor2(String name, String labelText, Composite parent, int textLimit, int txtWidth) {
    
    _txtWidth = txtWidth;
    
    // e.g. super(...)
    init(name, labelText);
    setTextLimit(textLimit);
    setEmptyStringAllowed(false);
    setErrorMessage(JFaceResources.getString("IntegerFieldEditor.errorMessage"));//$NON-NLS-1$
    createControl(parent); 
  }
  
  /**
   * Constructor
   */
  public IntegerFieldEditor2(String name, String labelText, Composite parent, int textLimit) {
    this(name, labelText, parent, textLimit, StringFieldEditor.UNLIMITED);
  }
  
  @Override
  protected void doFillIntoGrid(Composite parent, int numColumns) {
    super.doFillIntoGrid(parent, numColumns);

    if ( StringFieldEditor.UNLIMITED != _txtWidth ) {
      Text text = getTextControl();
      GridData gd = new GridData();
      gd.widthHint = _txtWidth;
      text.setLayoutData(gd);        
    }
    
    return;
  }
  
}
