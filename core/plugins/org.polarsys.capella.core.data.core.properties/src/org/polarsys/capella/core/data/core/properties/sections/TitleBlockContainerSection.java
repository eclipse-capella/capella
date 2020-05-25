/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.core.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.core.diagram.helpers.TitleBlockHelper;
import org.polarsys.capella.core.model.utils.NamingHelper;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.sections.AbstractSection;

/**
 * The TitleBlockContainerSection section.
 */
public abstract class TitleBlockContainerSection extends AbstractSection {
  protected Text valueField;
  
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
    if (newEObject instanceof DAnnotation && TitleBlockHelper.isTitleBlock((DAnnotation)newEObject)) {
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
    EObject refElement = TitleBlockHelper.getReferencedElement(capellaElement);
    if(refElement != null) {
      text = NamingHelper.getDefaultTitle(refElement);
    }
    return text;
  }
}
