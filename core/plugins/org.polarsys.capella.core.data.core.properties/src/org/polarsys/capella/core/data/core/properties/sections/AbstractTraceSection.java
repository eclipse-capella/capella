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
package org.polarsys.capella.core.data.core.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import org.polarsys.capella.core.data.core.properties.Messages;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.ui.properties.controllers.SimpleSemanticFieldController;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.SimpleSemanticField;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

/**
 * The AbstractTrace section.
 */
public abstract class AbstractTraceSection extends CapellaElementSection {

  private SimpleSemanticField _sourceElementField;
  private SimpleSemanticField _targetElementField;

  /**
   * {@inheritDoc}
   */
  @Override
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createControls(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    _sourceElementField = new SimpleSemanticField(getReferencesGroup(), Messages.getString("AbstractTrace.SourceElement.Label"), getWidgetFactory(), new SimpleSemanticFieldController()); //$NON-NLS-1$
    _sourceElementField.setDisplayedInWizard(displayedInWizard);
    _sourceElementField.setEnabled(false);

    _targetElementField = new SimpleSemanticField(getReferencesGroup(), Messages.getString("AbstractTrace.TargetElement.Label"), getWidgetFactory(), new SimpleSemanticFieldController()); //$NON-NLS-1$
    _targetElementField.setDisplayedInWizard(displayedInWizard);
    _targetElementField.setEnabled(false);
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.sections.AbstractSection#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public void loadData(CapellaElement capellaElement_p) {
    super.loadData(capellaElement_p);

    _sourceElementField.loadData(capellaElement_p, ModellingcorePackage.eINSTANCE.getAbstractTrace_SourceElement());
    _targetElementField.loadData(capellaElement_p, ModellingcorePackage.eINSTANCE.getAbstractTrace_TargetElement());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(_sourceElementField);
    fields.add(_targetElementField);

    return fields;
  }
}
