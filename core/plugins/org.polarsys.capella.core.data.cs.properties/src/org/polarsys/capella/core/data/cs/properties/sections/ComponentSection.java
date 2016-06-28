/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.core.data.core.properties.sections.GeneralizableElementSection;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.properties.controllers.AllocatedFunctionsController;
import org.polarsys.capella.core.data.cs.properties.controllers.ImplementedInterfacesController;
import org.polarsys.capella.core.data.cs.properties.controllers.UsedInterfacesController;
import org.polarsys.capella.core.data.fa.FaPackage;
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
   * @param showImplementedInterfaces
   * @param showUsedInterfaces
   * @param showAllocatedFunctions
   * @param showSuperTypes
   * @param showIsAbstract
   */
  public ComponentSection(boolean showImplementedInterfaces, boolean showUsedInterfaces, boolean showAllocatedFunctions, boolean showSuperTypes, boolean showIsAbstract) {
    super(showSuperTypes, showIsAbstract);

    _showImplementedInterfaces = showImplementedInterfaces;
    _showUsedInterfaces = showUsedInterfaces;
    _showAllocatedFunctions = showAllocatedFunctions;
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
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    if (null != _implementedInterfaces) {
      _implementedInterfaces.loadData(capellaElement, CsPackage.Literals.COMPONENT__OWNED_INTERFACE_IMPLEMENTATIONS);
    }
    if (null != _usedInterfaces) {
      _usedInterfaces.loadData(capellaElement, CsPackage.Literals.COMPONENT__OWNED_INTERFACE_USES);
    }
    if (null != _allocatedFunctions) {
      _allocatedFunctions.loadData(capellaElement, FaPackage.Literals.ABSTRACT_FUNCTIONAL_BLOCK__OWNED_FUNCTIONAL_ALLOCATION);
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
