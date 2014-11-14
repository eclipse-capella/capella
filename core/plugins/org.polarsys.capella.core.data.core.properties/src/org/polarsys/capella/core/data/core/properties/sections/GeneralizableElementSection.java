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
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import org.polarsys.capella.core.ui.toolkit.helpers.SelectionDialogHelper;
import org.polarsys.capella.core.data.core.properties.Messages;
import org.polarsys.capella.core.data.core.properties.controllers.GeneralizableElementController;
import org.polarsys.capella.core.data.core.properties.fields.GeneralizableElementBooleanPropertiesCheckbox;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.model.preferences.CapellaModelPreferencesPlugin;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.CompositionMultipleSemanticField;

/**
 * The GeneralizableElement section.
 */
public abstract class GeneralizableElementSection extends NamedElementSection {

  private boolean _showIsAbstract;
  private boolean _showSuperTypes;
  private CompositionMultipleSemanticField _superTypes;
  private GeneralizableElementBooleanPropertiesCheckbox _propertiesCheckbox;

  /**
   * Default constructor.
   */
  public GeneralizableElementSection() {
    this(true, true);
  }

  /**
   * Constructor.
   * @param showSuperTypes_p
   */
  public GeneralizableElementSection(boolean showSuperTypes_p, boolean showIsAbstract_p) {
    _showSuperTypes = showSuperTypes_p;
    _showIsAbstract = showIsAbstract_p;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createControls(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    if (_showIsAbstract) {
      Group checkGroup = getWidgetFactory().createGroup(_rootParentComposite, ""); //$NON-NLS-1$
      checkGroup.setLayout(new GridLayout(5, true));
      GridData gd = new GridData(GridData.FILL_HORIZONTAL);
      gd.horizontalSpan = 2;
      checkGroup.setLayoutData(gd);
      
      _propertiesCheckbox = new GeneralizableElementBooleanPropertiesCheckbox(checkGroup, getWidgetFactory());
      _propertiesCheckbox.setDisplayedInWizard(displayedInWizard);
    }

    if (_showSuperTypes) {
      _superTypes = new CompositionMultipleSemanticField(getReferencesGroup(),
          Messages.getString("GeneralizableElementSection_SuperType_Label"), getWidgetFactory(), //$NON-NLS-1$
          new GeneralizableElementController())
      {
        /**
         * {@inheritDoc}
         */
        @Override
        protected List<EObject> openTransferDialog(Button button_p, List<EObject> currentElements_p, List<EObject> availableElements_p, String title_p, String message_p) {
          if (CapellaModelPreferencesPlugin.getDefault().isMultipleInheritanceAllowed()) {
            return super.openTransferDialog(button_p, currentElements_p, availableElements_p, title_p, message_p);
          }
          EObject firstResult = SelectionDialogHelper.simplePropertySelectionDialogWizard(availableElements_p, button_p.getShell());
          if (null != firstResult) {
            return Collections.singletonList(firstResult);
          }
          return null;
        }
      };
      _superTypes.setDisplayedInWizard(displayedInWizard);
    }
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.sections.AbstractSection#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public void loadData(CapellaElement capellaElement_p) {
    super.loadData(capellaElement_p);

    if (null != _propertiesCheckbox) {
      _propertiesCheckbox.loadData(capellaElement_p);
    }

    if (null != _superTypes) {
      _superTypes.loadData(capellaElement_p, CapellacorePackage.eINSTANCE.getGeneralizableElement_Super(),
          CapellacorePackage.eINSTANCE.getGeneralizableElement_OwnedGeneralizations());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(_propertiesCheckbox);
    fields.add(_superTypes);

    return fields;
  }
}
