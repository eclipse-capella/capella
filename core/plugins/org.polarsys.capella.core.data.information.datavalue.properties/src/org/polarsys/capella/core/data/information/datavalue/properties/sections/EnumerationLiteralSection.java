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
package org.polarsys.capella.core.data.information.datavalue.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import org.polarsys.capella.core.data.core.properties.sections.NamedElementSection;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.information.datavalue.properties.Messages;
import org.polarsys.capella.core.data.information.datavalue.properties.controllers.EnumerationLiteralController;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.SimpleEditableSemanticField;

/**
 * The EnumerationLiteral section.
 */
public class EnumerationLiteralSection extends NamedElementSection {

  private SimpleEditableSemanticField domainValueWidget;

  @Override
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createControls(parent, aTabbedPropertySheetPage);

    Group main = getWidgetFactory().createGroup(_rootParentComposite, ""); //$NON-NLS-1$
    main.setLayout(new GridLayout(6, false));
    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = 2;
    main.setLayoutData(gd);

    boolean displayedInWizard = isDisplayedInWizard();

    domainValueWidget =
        new SimpleEditableSemanticField(main,
            Messages.getString("EnumerationLiteral.DomainValueLabel"), getWidgetFactory(), Messages.getString("EnumerationLiteral.DomainValueDefaultName"), new EnumerationLiteralController()); //$NON-NLS-1$ //$NON-NLS-2$
    domainValueWidget.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.sections.AbstractSection#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public void loadData(CapellaElement capellaElement_p) {
    super.loadData(capellaElement_p);

    domainValueWidget.loadData(capellaElement_p, DatavaluePackage.eINSTANCE.getEnumerationLiteral_DomainValue());
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == DatavaluePackage.eINSTANCE.getEnumerationLiteral()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(domainValueWidget);

    return fields;
  }
}
