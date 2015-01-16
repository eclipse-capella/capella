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
package org.polarsys.capella.core.validation.ui.ide.quickfix;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.ui.toolkit.viewers.data.TreeData;
import org.polarsys.capella.core.model.helpers.intermodelInconsistencyDetection.InterModelInconsistency;

/** 
 * @author Erwan Brottier
 */
public class InterModelErrorNavigatorDialog extends EObjectNavigatorDialog {
	
  protected List<String> inconsistencyTypeNames;
	
	public InterModelErrorNavigatorDialog(List<? extends EObject> elements_p, String dialogTitle_p, String dialogMessage_p, String dialogComboLabel_p) {
    super(elements_p, dialogTitle_p, dialogMessage_p, dialogComboLabel_p, null, MessageDialog.INFORMATION,
         new String[] { org.polarsys.capella.common.ui.toolkit.dialogs.Messages.AbstractViewerDialog_OK_Title }, DEFAULT_COLOR_FOR_REVELANT_ELEMENTS);
  }
  
  public void setCycles(List<InterModelInconsistency> inconsistencies) {    
  	List<List<EObject>> involvedObjects = new ArrayList<List<EObject>>();
  	this.inconsistencyTypeNames = new ArrayList<String>();
		for (InterModelInconsistency inconsistency : inconsistencies) {
			involvedObjects.add(inconsistency.getInvolvedObjects());
			inconsistencyTypeNames.add(inconsistency.getTypeName());
		}  	
  	super.setCycles(involvedObjects);    
  }

  @Override
  protected void createSelectCycleArea(Composite parent_p) {
    int initialSelection = 0;
    Composite comp = new Composite(parent_p, SWT.NONE);
    comp.setLayout(new GridLayout(2, false));
    comp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    Label lbl = new Label(comp, SWT.NONE);
    lbl.setText(_comboLabel);
    _combo = new Combo(comp, SWT.NONE | SWT.READ_ONLY | SWT.BORDER | SWT.COLOR_WIDGET_BACKGROUND);
    GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
    _combo.setLayoutData(gd);
    _combo.setData(String.valueOf(initialSelection), _revelantElements);
    Iterator<List<EObject>> it = _cycles.iterator();
    for (int i = 0; i < _cycles.size(); i++) {
      _combo.add((i + 1) + "-" + ICommonConstants.WHITE_SPACE_CHARACTER + inconsistencyTypeNames.get(i)); //$NON-NLS-1$
      _combo.setData(String.valueOf(i), it.next());
    }
    _combo.select(initialSelection);
    _combo.addSelectionListener(new SelectionAdapter() {
      @SuppressWarnings({ "synthetic-access", "unchecked" })
      @Override
      public void widgetSelected(SelectionEvent e) {
        int idx = _combo.getSelectionIndex();
        List<EObject> list = (List<EObject>) _combo.getData(String.valueOf(idx));
        getViewer().setInput(new TreeData(list, null));
      }
    });
  }  
}
