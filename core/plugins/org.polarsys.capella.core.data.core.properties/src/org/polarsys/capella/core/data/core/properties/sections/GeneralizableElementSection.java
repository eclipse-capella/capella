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
package org.polarsys.capella.core.data.core.properties.sections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.core.properties.Messages;
import org.polarsys.capella.core.data.core.properties.controllers.GeneralizableElementController;
import org.polarsys.capella.core.data.core.properties.fields.GeneralizableElementBooleanPropertiesCheckbox;
import org.polarsys.capella.core.model.preferences.CapellaModelPreferencesPlugin;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.CompositionMultipleSemanticField;
import org.polarsys.capella.core.ui.toolkit.helpers.SelectionDialogHelper;

/**
 * The GeneralizableElement section.
 */
public abstract class GeneralizableElementSection extends NamedElementSection {

  private boolean showIsAbstract;
  private boolean showSuperTypes;
  protected CompositionMultipleSemanticField superTypes;
  protected GeneralizableElementBooleanPropertiesCheckbox propertiesCheckbox;

  /**
   * Default constructor.
   */
  public GeneralizableElementSection() {
    this(true, true);
  }

  /**
   * Constructor.
   * @param showSuperTypes
   */
  public GeneralizableElementSection(boolean showSuperTypes, boolean showIsAbstract) {
    this.showSuperTypes = showSuperTypes;
    this.showIsAbstract = showIsAbstract;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    if (showIsAbstract) {
      propertiesCheckbox = new GeneralizableElementBooleanPropertiesCheckbox(getCheckGroup(), getWidgetFactory());
      propertiesCheckbox.setDisplayedInWizard(displayedInWizard);
    }

    if (showSuperTypes) {
      superTypes = new CompositionMultipleSemanticField(getReferencesGroup(),
          getSuperLabel(), getWidgetFactory(),
          new GeneralizableElementController())
      {
        /**
         * {@inheritDoc}
         */
        @Override
        protected List<EObject> openTransferDialog(Button button, List<EObject> currentElements, List<EObject> availableElements, String title, String message) {
          if (CapellaModelPreferencesPlugin.getDefault().isMultipleInheritanceAllowed()) {
            return super.openTransferDialog(button, currentElements, availableElements, title, message);
          }
          EObject firstResult = SelectionDialogHelper.simplePropertySelectionDialogWizard(availableElements, button.getShell());
          if (null != firstResult) {
            return Collections.singletonList(firstResult);
          }
          return null;
        }
      };
      superTypes.setDisplayedInWizard(displayedInWizard);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    if (null != propertiesCheckbox) {
      propertiesCheckbox.loadData(capellaElement);
    }

    if (null != superTypes) {
      superTypes.loadData(capellaElement, CapellacorePackage.eINSTANCE.getGeneralizableElement_Super(),
          CapellacorePackage.eINSTANCE.getGeneralizableElement_OwnedGeneralizations());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<>();

    fields.addAll(super.getSemanticFields());
    fields.add(propertiesCheckbox);
    fields.add(superTypes);

    return fields;
  }

  protected String getSuperLabel() {
    return Messages.getString("GeneralizableElementSection_SuperType_Label");
  }
}
