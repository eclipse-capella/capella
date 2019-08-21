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
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.properties.controllers.AllocatedFunctionsController;
import org.polarsys.capella.core.data.cs.properties.controllers.ImplementedInterfacesController;
import org.polarsys.capella.core.data.cs.properties.controllers.UsedInterfacesController;
import org.polarsys.capella.core.data.cs.properties.fields.IsActorBooleanPropertiesCheckbox;
import org.polarsys.capella.core.data.cs.properties.fields.IsHumanBooleanPropertiesCheckbox;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;

/**
 * The Component section.
 */
public abstract class ComponentSection extends GeneralizableElementSection {

  private boolean showIsActor;
  private boolean showIsHuman;
  private boolean showImplementedInterfaces;
  private boolean showUsedInterfaces;
  private boolean showAllocatedFunctions;
  private IsActorBooleanPropertiesCheckbox isActorCheckbox;
  private IsHumanBooleanPropertiesCheckbox isHumanCheckbox;
  private MultipleSemanticField implementedInterfaces;
  private MultipleSemanticField usedInterfaces;
  protected MultipleSemanticField allocatedFunctions;

  /**
   * Default constructor.
   */
  public ComponentSection() {
    this(true, true, true, true, true, true, true);
  }

  /**
   * Constructor.
   * @param showImplementedInterfaces
   * @param showUsedInterfaces
   * @param showAllocatedFunctions
   * @param showSuperTypes
   * @param showIsAbstract
   */
  public ComponentSection(boolean showIsActor, boolean showIsHuman, boolean showImplementedInterfaces, boolean showUsedInterfaces, boolean showAllocatedFunctions, boolean showSuperTypes, boolean showIsAbstract) {
    super(showSuperTypes, showIsAbstract);

    this.showIsActor = showIsActor;
    this.showIsHuman = showIsHuman;
    this.showImplementedInterfaces = showImplementedInterfaces;
    this.showUsedInterfaces = showUsedInterfaces;
    this.showAllocatedFunctions = showAllocatedFunctions;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createControls(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    if (showIsActor) {
      isActorCheckbox = new IsActorBooleanPropertiesCheckbox(getCheckGroup(), getWidgetFactory());
      isActorCheckbox.setDisplayedInWizard(displayedInWizard);
    }

    if (showIsHuman) {
      isHumanCheckbox = new IsHumanBooleanPropertiesCheckbox(getCheckGroup(), getWidgetFactory());
      isHumanCheckbox.setDisplayedInWizard(displayedInWizard);
    }

    if (showImplementedInterfaces) {
      implementedInterfaces =
        new MultipleSemanticField(getReferencesGroup(), Messages.ComponentSection_ImplementedInterfaces_Label, getWidgetFactory(),
            new ImplementedInterfacesController());
      implementedInterfaces.setDisplayedInWizard(displayedInWizard);
    }

    if (showUsedInterfaces) {
      usedInterfaces =
          new MultipleSemanticField(getReferencesGroup(), Messages.ComponentSection_UsedInterfaces_Label, getWidgetFactory(), new UsedInterfacesController());
      usedInterfaces.setDisplayedInWizard(displayedInWizard);
    }

    if (showAllocatedFunctions) {
      allocatedFunctions =
          new MultipleSemanticField(getReferencesGroup(), Messages.ComponentSection_AllocatedFunctions_Label, getWidgetFactory(),
              new AllocatedFunctionsController());
      allocatedFunctions.setDisplayedInWizard(displayedInWizard);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    // if the capellaElement is a component but not an actor, the IsAbstract checkbox must be disabled
    if (null != propertiesCheckbox && capellaElement instanceof Component) {
      Component component = (Component) capellaElement;
      propertiesCheckbox.setEnabled(component.isActor());
    }
    
    if (null != isActorCheckbox) {
      isActorCheckbox.loadData(capellaElement);

      // if the capellaElement is a system, the IsActor checkbox must be disabled
      isActorCheckbox.setEnabled(capellaElement != BlockArchitectureExt.getRootBlockArchitecture(capellaElement).getSystem());
    }
    
    if (null != isHumanCheckbox) {
      isHumanCheckbox.loadData(capellaElement);
      
   // if the capellaElement is a system, the IsHuman checkbox must be disabled
      isHumanCheckbox.setEnabled(capellaElement != BlockArchitectureExt.getRootBlockArchitecture(capellaElement).getSystem());
    }
    
    if (null != implementedInterfaces) {
      implementedInterfaces.loadData(capellaElement, CsPackage.Literals.COMPONENT__OWNED_INTERFACE_IMPLEMENTATIONS);
    }
    if (null != usedInterfaces) {
      usedInterfaces.loadData(capellaElement, CsPackage.Literals.COMPONENT__OWNED_INTERFACE_USES);
    }
    if (null != allocatedFunctions) {
      allocatedFunctions.loadData(capellaElement, FaPackage.Literals.ABSTRACT_FUNCTIONAL_BLOCK__OWNED_FUNCTIONAL_ALLOCATION);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<>();

    fields.addAll(super.getSemanticFields());
    fields.add(isActorCheckbox);
    fields.add(isHumanCheckbox);
    fields.add(allocatedFunctions);
    fields.add(implementedInterfaces);
    fields.add(usedInterfaces);

    return fields;
  }
}
