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
package org.polarsys.capella.core.data.cs.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import org.polarsys.capella.core.data.core.properties.sections.GeneralizableElementSection;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.properties.controllers.AllocatedFunctionsController;
import org.polarsys.capella.core.data.cs.properties.controllers.ImplementedInterfacesController;
import org.polarsys.capella.core.data.cs.properties.controllers.UsedInterfacesController;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;

/**
 * The Component section.
 */
public abstract class ComponentSection extends GeneralizableElementSection {

  private boolean _showImplementedInterfaces;
  private boolean _showUsedInterfaces;
  private boolean _showAllocatedFunctions;
  private MultipleSemanticField _implementedInterfaces;
  private MultipleSemanticField _usedInterfaces;
  protected MultipleSemanticField _allocatedFunctions;

  /**
   * Default constructor.
   */
  public ComponentSection() {
    this(true, true, true, true, true);
  }

  /**
   * Constructor.
   * @param showImplementedInterfaces_p
   * @param showUsedInterfaces_p
   * @param showAllocatedFunctions_p
   * @param showSuperTypes_p
   * @param showIsAbstract_p
   */
  public ComponentSection(boolean showImplementedInterfaces_p, boolean showUsedInterfaces_p, boolean showAllocatedFunctions_p, boolean showSuperTypes_p, boolean showIsAbstract_p) {
    super(showSuperTypes_p, showIsAbstract_p);

    _showImplementedInterfaces = showImplementedInterfaces_p;
    _showUsedInterfaces = showUsedInterfaces_p;
    _showAllocatedFunctions = showAllocatedFunctions_p;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createControls(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    if (_showImplementedInterfaces) {
      _implementedInterfaces =
        new MultipleSemanticField(getReferencesGroup(), Messages.ComponentSection_ImplementedInterfaces_Label, getWidgetFactory(),
            new ImplementedInterfacesController());
      _implementedInterfaces.setDisplayedInWizard(displayedInWizard);
    }

    if (_showUsedInterfaces) {
      _usedInterfaces =
          new MultipleSemanticField(getReferencesGroup(), Messages.ComponentSection_UsedInterfaces_Label, getWidgetFactory(), new UsedInterfacesController());
      _usedInterfaces.setDisplayedInWizard(displayedInWizard);
    }

    if (_showAllocatedFunctions) {
      _allocatedFunctions =
          new MultipleSemanticField(getReferencesGroup(), Messages.ComponentSection_AllocatedFunctions_Label, getWidgetFactory(),
              new AllocatedFunctionsController());
      _allocatedFunctions.setDisplayedInWizard(displayedInWizard);
    }
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.sections.AbstractSection#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public void loadData(CapellaElement capellaElement_p) {
    super.loadData(capellaElement_p);

    if (null != _implementedInterfaces) {
      _implementedInterfaces.loadData(capellaElement_p, CsPackage.Literals.COMPONENT__OWNED_INTERFACE_IMPLEMENTATIONS);
    }
    if (null != _usedInterfaces) {
      _usedInterfaces.loadData(capellaElement_p, CsPackage.Literals.COMPONENT__OWNED_INTERFACE_USES);
    }
    if (null != _allocatedFunctions) {
      _allocatedFunctions.loadData(capellaElement_p, FaPackage.Literals.ABSTRACT_FUNCTIONAL_BLOCK__OWNED_FUNCTIONAL_ALLOCATION);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(_allocatedFunctions);
    fields.add(_implementedInterfaces);
    fields.add(_usedInterfaces);

    return fields;
  }
}
