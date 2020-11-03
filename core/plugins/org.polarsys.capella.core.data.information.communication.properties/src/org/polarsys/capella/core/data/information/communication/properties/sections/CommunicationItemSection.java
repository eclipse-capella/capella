/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.information.communication.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.core.data.core.properties.fields.VisibilityKindGroup;
import org.polarsys.capella.core.data.core.properties.sections.GeneralizableElementSection;
import org.polarsys.capella.core.data.information.communication.CommunicationPackage;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;

/**
 * The CommunicationItem section.
 */
public abstract class CommunicationItemSection extends GeneralizableElementSection {

  private VisibilityKindGroup visibilityKindGroup;

  /**
   * Default constructor.
   */
  public CommunicationItemSection() {
    this(true, true);
  }

  /**
   * Constructor.
   * @param showSuperTypes
   */
  public CommunicationItemSection(boolean showSuperTypes, boolean showIsAbstract) {
    super(showSuperTypes, showIsAbstract);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);

    visibilityKindGroup = new VisibilityKindGroup(parent, getWidgetFactory());
    visibilityKindGroup.setDisplayedInWizard(isDisplayedInWizard());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    visibilityKindGroup.loadData(capellaElement, CommunicationPackage.eINSTANCE.getCommunicationItem_Visibility());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(visibilityKindGroup);

    return fields;
  }
}
