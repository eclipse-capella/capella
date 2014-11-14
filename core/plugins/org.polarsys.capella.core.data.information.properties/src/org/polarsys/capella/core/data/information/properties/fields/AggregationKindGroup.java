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
package org.polarsys.capella.core.data.information.properties.fields;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import org.polarsys.capella.core.data.information.AggregationKind;
import org.polarsys.capella.core.data.information.properties.Messages;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticKindGroup;

/**
 */
public class AggregationKindGroup extends AbstractSemanticKindGroup {
  private Button _aggregationBtnUnset;
  private Button _aggregationBtnAssociation;
  private Button _aggregationBtnAggregation;
  private Button _aggregationBtnComposition;

  /**
   * Constructor.
   * @param parent_p
   * @param widgetFactory_p
   */
  public AggregationKindGroup(Composite parent_p, TabbedPropertySheetWidgetFactory widgetFactory_p) {
    super(parent_p, widgetFactory_p, Messages.getString("AggregationKind.Label"), 5); //$NON-NLS-1$

    _aggregationBtnUnset = createButton(AggregationKind.UNSET);
    _aggregationBtnAssociation = createButton(AggregationKind.ASSOCIATION);
    _aggregationBtnAggregation = createButton(AggregationKind.AGGREGATION);
    _aggregationBtnComposition = createButton(AggregationKind.COMPOSITION);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Button> getSemanticFields() {
    List<Button> fields = new ArrayList<Button>();

    fields.add(_aggregationBtnUnset);
    fields.add(_aggregationBtnAssociation);
    fields.add(_aggregationBtnAggregation);
    fields.add(_aggregationBtnComposition);
    
    return fields;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Button getDefaultSemanticField() {
    return _aggregationBtnUnset;
  }
}
