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
package org.polarsys.capella.core.data.core.properties.fields;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import org.polarsys.capella.core.data.core.properties.Messages;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticCheckboxGroup;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

/**
 * The AbstractParameter customized section class.
 */
public class AbstractParameterBooleanPropertiesCheckbox extends AbstractSemanticCheckboxGroup {
  private Button _isExceptionBtn;
  private Button _isOptionalBtn;
  private Button _isStreamBtn;

  /**
   * @param parent_p
   * @param widgetFactory_p
   */
  public AbstractParameterBooleanPropertiesCheckbox(Composite parent_p, TabbedPropertySheetWidgetFactory widgetFactory_p) {
    this(parent_p, widgetFactory_p, true, true, true);
  }

  /**
   * @param parent_p
   * @param widgetFactory_p
   * @param showIsException_p
   * @param showIsOptional_p
   * @param showIsStream_p
   */
  public AbstractParameterBooleanPropertiesCheckbox(Composite parent_p, TabbedPropertySheetWidgetFactory widgetFactory_p, boolean showIsException_p, boolean showIsOptional_p, boolean showIsStream_p) {
    super(parent_p, widgetFactory_p);

    if (showIsException_p) {
      _isExceptionBtn = createButton(ModellingcorePackage.eINSTANCE.getAbstractParameter_IsException(), Messages.getString("AbstractParameter.IsExceptionLabel"), parent_p); //$NON-NLS-1$
    }
    if (showIsOptional_p) {
      _isOptionalBtn = createButton(ModellingcorePackage.eINSTANCE.getAbstractParameter_IsOptional(), Messages.getString("AbstractParameter.IsOptionalLabel"), parent_p); //$NON-NLS-1$
    }
    if (showIsStream_p) {
      _isStreamBtn = createButton(ModellingcorePackage.eINSTANCE.getAbstractParameter_IsStream(), Messages.getString("AbstractParameter.IsStreamLabel"), parent_p); //$NON-NLS-1$
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Button> getSemanticFields() {
    List<Button> fields = new ArrayList<Button>();

    fields.add(_isExceptionBtn);
    fields.add(_isOptionalBtn);
    fields.add(_isStreamBtn);

    return fields;
  }
}
