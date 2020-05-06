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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.model.helpers.TitleBlockExt;
import org.polarsys.capella.core.model.utils.NamingHelper;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.Messages;
import org.polarsys.capella.core.ui.properties.fields.TextValueGroup;
import org.polarsys.capella.core.ui.properties.sections.AbstractSection;

/**
 * The TitleBlockContainerSection section.
 */
public class TitleBlockContainerSection extends AbstractSection {
  protected Text valueField;
  
  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return (eObjectToTest instanceof DAnnotation
        && TitleBlockExt.isTitleBlock((DAnnotation)eObjectToTest));
  }

  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);
    createTextGroup(parent, Messages.TitleBlock_Reference_Label);
  }
  
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage, String textLabel) {
    super.createContents(parent, aTabbedPropertySheetPage);
    createTextGroup(parent, textLabel);
  }
  
  protected void createTextGroup(Composite parent, String label) {
    Composite main = getWidgetFactory().createComposite(parent);
    main.setLayout(new GridLayout(3, false));
    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = ((GridLayout) parent.getLayout()).numColumns;
    main.setLayoutData(gd);

    getWidgetFactory().createCLabel(main, label);
    valueField = getWidgetFactory().createText(main, "");
    valueField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();
    return fields;
  }
  
  @Override
  public void setInput(IWorkbenchPart part, ISelection selection) {
    EObject newEObject = super.setInputSelection(part, selection);
    if (newEObject instanceof DAnnotation && TitleBlockExt.isTitleBlock((DAnnotation)newEObject)) {
      loadData(newEObject);
    }
  }
  
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);
    setTextGroup(capellaElement);
  }
  
  protected void setTextGroup(EObject capellaElement) {
    valueField.setText(getTextGroup(capellaElement));
    valueField.setEnabled(false);
    valueField.setTouchEnabled(false);
  }
  
  protected String getTextGroup(EObject capellaElement) {
    String text = "";
    EObject refElement = TitleBlockExt.getReferencedElement(capellaElement);
    if(refElement != null) {
      text = NamingHelper.getDefaultTitle(refElement);
    }
    return text;
  }
}
