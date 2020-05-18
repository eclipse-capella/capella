/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.cs.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.core.data.core.properties.sections.GeneralizableElementSection;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.properties.controllers.AllocatedFunctionsController;
import org.polarsys.capella.core.data.cs.properties.controllers.ImplementedInterfacesController;
import org.polarsys.capella.core.data.cs.properties.controllers.UsedInterfacesController;
import org.polarsys.capella.core.data.cs.properties.fields.IsActorCheckbox;
import org.polarsys.capella.core.data.cs.properties.fields.IsHumanCheckbox;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.preferences.CapellaModelPreferencesPlugin;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;

/**
 * The Component section.
 */
public abstract class ComponentSection extends GeneralizableElementSection {

  private boolean showIsHuman;
  private boolean showIsActor;
  private boolean showImplementedInterfaces;
  private boolean showUsedInterfaces;
  private boolean showAllocatedFunctions;
  protected IsHumanCheckbox isHumanCheckbox;
  protected IsActorCheckbox isActorCheckbox;
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
  public ComponentSection(boolean showIsHuman, boolean showIsActor, boolean showImplementedInterfaces, boolean showUsedInterfaces, boolean showAllocatedFunctions, boolean showSuperTypes, boolean showIsAbstract) {
    super(showSuperTypes, showIsAbstract);

    this.showIsHuman = showIsHuman;
    this.showIsActor = showIsActor;
    this.showImplementedInterfaces = showImplementedInterfaces;
    this.showUsedInterfaces = showUsedInterfaces;
    this.showAllocatedFunctions = showAllocatedFunctions;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    if (showIsHuman) {
      isHumanCheckbox = new IsHumanCheckbox(getCheckGroup(), getWidgetFactory());
      isHumanCheckbox.setDisplayedInWizard(displayedInWizard);
    }

    if (showIsActor) {
      isActorCheckbox = new IsActorCheckbox(getCheckGroup(), getWidgetFactory());
      isActorCheckbox.setDisplayedInWizard(displayedInWizard);
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

    Component component = (Component) capellaElement;
    BlockArchitecture block = BlockArchitectureExt.getRootBlockArchitecture(component);

    // if capellaElement is a component but not an actor, disable super
    if (null != superTypes) {
      superTypes.setEnabled(
          component.isActor() || CapellaModelPreferencesPlugin.getDefault().isComponentNonActorInheritanceAllowed());
    }

    // if the capellaElement is a component but not an actor, the IsAbstract checkbox must be disabled
    if (null != propertiesCheckbox) {
      propertiesCheckbox.setEnabled(component.isActor());
    }
    
    if (null != isHumanCheckbox) {
      isHumanCheckbox.loadData(component);
      
      boolean isAnOE = block instanceof OperationalAnalysis && !component.isActor();
      boolean isASystem = component == block.getSystem();
      boolean hasChildren = ComponentExt.isComposite(component);
      // then the IsHuman checkbox must be disabled
      if (isHumanCheckbox.isEnabled() && (isAnOE || isASystem || hasChildren)) {
        isHumanCheckbox.setEnabled(false);
      }
    }
    
    if (null != isActorCheckbox) {
      isActorCheckbox.loadData(component);

      boolean isInSALevel = block instanceof SystemAnalysis;
      boolean isASystem = component == block.getSystem();
      boolean cannotBecameComponent = component.isActor() && !ComponentExt.canCreateABComponent(component.eContainer());
      boolean cannotBecameActor = !component.isActor() && !ComponentExt.canCreateABActor(component.eContainer());
      // then the IsActor checkbox must be disabled
      if (isActorCheckbox.isEnabled() && (isInSALevel || isASystem || cannotBecameComponent || cannotBecameActor)) {
        isActorCheckbox.setEnabled(false);
      }
    }
    
    if (null != implementedInterfaces) {
      implementedInterfaces.loadData(component, CsPackage.Literals.COMPONENT__OWNED_INTERFACE_IMPLEMENTATIONS);
    }
    if (null != usedInterfaces) {
      usedInterfaces.loadData(component, CsPackage.Literals.COMPONENT__OWNED_INTERFACE_USES);
    }
    if (null != allocatedFunctions) {
      allocatedFunctions.loadData(component, FaPackage.Literals.ABSTRACT_FUNCTIONAL_BLOCK__OWNED_FUNCTIONAL_ALLOCATION);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<>();

    fields.addAll(super.getSemanticFields());
    fields.add(isHumanCheckbox);
    fields.add(isActorCheckbox);
    fields.add(allocatedFunctions);
    fields.add(implementedInterfaces);
    fields.add(usedInterfaces);

    return fields;
  }

  @Override
  protected String getSuperLabel() {
    return Messages.GeneralizableComponentSection_Generalized_Label;
  }
}
