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
package org.polarsys.capella.core.data.common.properties.sections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.common.properties.Messages;
import org.polarsys.capella.core.data.core.properties.sections.NamedElementSection;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.ConstraintReferenceGroup;

/**
 */
public abstract class StateEventSection extends NamedElementSection {

  private ConstraintReferenceGroup _expression;

  /**
   * 
   */
  public StateEventSection() {
    super();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);

    _expression = new ConstraintReferenceGroup(Collections.singletonMap(Messages.getString("StateEvent.Expression"), CapellacommonPackage.Literals.STATE_EVENT__EXPRESSION), false); //$NON-NLS-1$
    _expression.createControls(parent, getWidgetFactory(), isDisplayedInWizard());
    
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public abstract boolean select(Object toTest);

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);
    _expression.loadData(capellaElement);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    ArrayList<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();
    fields.addAll(super.getSemanticFields());
    fields.addAll(_expression.getFields());
    return fields;
  }

}