/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.ui.massactions.core.shared.column;

import java.util.Comparator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.nebula.widgets.nattable.data.validate.IDataValidator;
import org.eclipse.nebula.widgets.nattable.edit.editor.ICellEditor;
import org.eclipse.nebula.widgets.nattable.painter.cell.ICellPainter;
import org.polarsys.capella.common.ui.massactions.core.edit.editor.many.ManyRefCellEditor;
import org.polarsys.capella.common.ui.massactions.core.edit.editor.many.ManyRefTransitionalValue;
import org.polarsys.capella.common.ui.massactions.core.shared.data.convert.ManyRefDisplayConverter;
import org.polarsys.capella.common.ui.massactions.core.shared.data.validate.ManyRefDataValidator;
import org.polarsys.kitalpha.massactions.core.column.primitive.MAPrimitiveColumn;
import org.polarsys.kitalpha.massactions.core.data.compare.MADisplayComparator;
import org.polarsys.kitalpha.massactions.core.data.convert.MADisplayConverter;
import org.polarsys.kitalpha.massactions.core.painter.cell.TextAndLabelImagePainter;

/**
 * A column handling many (multiple) references.
 * 
 * @author Sandu Postaru
 *
 */
public class ManyRefColumn extends MAPrimitiveColumn {

  private MADisplayConverter displayConverter;

  @Override
  protected ICellEditor createCellEditor() {
    return new ManyRefCellEditor(this.bodyLayer, this.featureMap);
  }

  @Override
  protected IDataValidator createDataValidator() {
    return new ManyRefDataValidator();
  }

  @Override
  protected MADisplayConverter createDisplayConverter() {
    if (displayConverter == null) {
      displayConverter = new ManyRefDisplayConverter();
    }
    return displayConverter;
  }

  @Override
  protected ICellPainter createCellPainter() {
    return new TextAndLabelImagePainter();
  }

  @Override
  protected Comparator<Object> createCellComparator() {
    return new MADisplayComparator(createDisplayConverter());
  }

  @SuppressWarnings("unchecked")
  @Override
  public void setDataValue(EObject rowObject, Object newValue) {

    EStructuralFeature feature = getStructuralFeature(rowObject);
    List<EObject> currentValue = (List<EObject>) rowObject.eGet(feature);

    ManyRefTransitionalValue transactionalValue = (ManyRefTransitionalValue) newValue;
    Object newAdaptedValue = transactionalValue.adapt(currentValue);

    super.setDataValue(rowObject, newAdaptedValue);
  }
}
