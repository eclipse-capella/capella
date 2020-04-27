/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
   * @param parent
   * @param widgetFactory
   */
  public AbstractParameterBooleanPropertiesCheckbox(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory) {
    this(parent, widgetFactory, true, true, true);
  }

  /**
   * @param parent
   * @param widgetFactory
   * @param showIsException
   * @param showIsOptional
   * @param showIsStream
   */
  public AbstractParameterBooleanPropertiesCheckbox(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory, boolean showIsException, boolean showIsOptional, boolean showIsStream) {
    super(parent, widgetFactory);

    if (showIsException) {
      _isExceptionBtn = createButton(ModellingcorePackage.eINSTANCE.getAbstractParameter_IsException(), Messages.getString("AbstractParameter.IsExceptionLabel"), parent); //$NON-NLS-1$
    }
    if (showIsOptional) {
      _isOptionalBtn = createButton(ModellingcorePackage.eINSTANCE.getAbstractParameter_IsOptional(), Messages.getString("AbstractParameter.IsOptionalLabel"), parent); //$NON-NLS-1$
    }
    if (showIsStream) {
      _isStreamBtn = createButton(ModellingcorePackage.eINSTANCE.getAbstractParameter_IsStream(), Messages.getString("AbstractParameter.IsStreamLabel"), parent); //$NON-NLS-1$
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
