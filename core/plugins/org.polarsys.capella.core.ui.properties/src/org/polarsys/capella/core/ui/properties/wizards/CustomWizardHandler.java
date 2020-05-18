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
package org.polarsys.capella.core.ui.properties.wizards;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import org.polarsys.capella.common.ui.toolkit.dialogs.IDialog;
import org.polarsys.capella.core.ui.properties.wizards.filter.DefaultFilter;
import org.polarsys.capella.core.ui.properties.wizards.filter.Filter;
import org.polarsys.capella.core.ui.properties.wizards.filter.IdentityFilter;

/**
 * A Handler to open the custom capella property wizard on a model element.
 * Unless you're not already inside an executing command,
 * you probably want to run the handler on the command stack. To do so,
 * use CapellaUIPropertiesPlugin.openWizard() which already provides
 * this functionality.
 * 
 * The handler supports the notion of a filter. A filter allows to
 * open the properties editor on an element that is mapped
 * to the original element via the filter... Currently this is used
 * to open the Type of a Part instead of a Part in some cases 
 * (@see org.polarsys.capella.core.ui.properties.wizards.filter.DefaultFilter)
 * 
 */
public class CustomWizardHandler implements ICustomWizardHandler {

  private Filter<EObject, EObject> filter;

  public CustomWizardHandler() {
    this(new DefaultFilter());
  }

  /** 
   * Create a CustomWizardHandler with a custom model element filter. 
   * @param filter the filter you want to apply to model elements. pass null to use no filter.
   */
  public CustomWizardHandler(Filter<EObject, EObject> filter) {
    if (filter == null) {
      this.filter = new IdentityFilter();
    } else {
      this.filter = filter;
    }
  }

  /**
   * Open the wizard on a model element, previously applying the handler's filter.
   * {@inheritDoc}
   */
  public boolean openWizard(EObject object) {
    IDialog dialog = createWizardDialog(filter.filter(object));
    return dialog.open() == Window.OK;
  }

  /**
   * Create the wizard dialog for the given model element. The filter
   * has already been applied at this point.
   * @param modelElement
   * @return
   */
  protected IDialog createWizardDialog(EObject object) {
    Display display = PlatformUI.getWorkbench().getDisplay();
    IWorkbenchPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart();
    return new CapellaWizardDialog(display.getActiveShell(), new EditCapellaCustomPropertyWizard(part, object));
  }

}
