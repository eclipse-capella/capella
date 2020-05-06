/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.core.properties.sections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.core.model.helpers.TitleBlockExt;
import org.polarsys.capella.core.ui.properties.fields.Messages;

/**
 * The TitleBlockContentSection section.
 */
public class TitleBlockContentSection extends TitleBlockContainerSection {
  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return (eObjectToTest instanceof DAnnotation &&
        TitleBlockExt.isContentTitleBlock((DAnnotation)eObjectToTest));
  }


  @Override
  public void setInput(IWorkbenchPart part, ISelection selection) {
    EObject newEObject = super.setInputSelection(part, selection);
    if (newEObject instanceof DAnnotation &&
        TitleBlockExt.isContentTitleBlock((DAnnotation)newEObject)) {
      loadData(newEObject);
    }
  }
  
  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage, Messages.TitleBlock_Label_Label);
  }
  
  @Override
  protected String getTextGroup(EObject capellaElement) {
      return ((DAnnotation)capellaElement).getDetails().get(TitleBlockExt.TITLE_BLOCK_CONTENT);
  }
}
