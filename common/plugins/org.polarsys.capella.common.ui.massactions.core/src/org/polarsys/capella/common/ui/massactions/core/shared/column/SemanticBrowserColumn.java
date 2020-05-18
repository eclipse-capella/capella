/*******************************************************************************
 * Copyright (c) 2018, 2019 THALES GLOBAL SERVICES.
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

import java.util.Collection;
import java.util.Comparator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.nebula.widgets.nattable.data.validate.IDataValidator;
import org.eclipse.nebula.widgets.nattable.painter.cell.ICellPainter;
import org.polarsys.capella.common.ui.massactions.core.shared.data.convert.SemanticBrowserDisplayConverter;
import org.polarsys.capella.common.ui.massactions.core.shared.data.validate.SemanticBrowserDataValidator;
import org.polarsys.capella.common.ui.massactions.core.shared.helper.SemanticBrowserImageProvider;
import org.polarsys.capella.common.ui.toolkit.browser.category.ICategory;
import org.polarsys.kitalpha.massactions.core.column.AbstractMAColumn;
import org.polarsys.kitalpha.massactions.core.data.compare.MADisplayComparator;
import org.polarsys.kitalpha.massactions.core.data.convert.MADisplayConverter;
import org.polarsys.kitalpha.massactions.core.helper.ImageProvider;
import org.polarsys.kitalpha.massactions.core.painter.cell.TextAndLabelImagePainter;

/**
 * A column handling semantic browser queries.
 * 
 * @author Sandu Postaru
 *
 */
public class SemanticBrowserColumn extends AbstractMAColumn {

  private ICategory category;
  private MADisplayConverter displayConverter;
  private ICellPainter cellPainter;

  public void setCategory(ICategory category) {
    this.category = category;
  }

  @Override
  protected ICellPainter createCellPainter() {

    if (cellPainter == null) {
      ImageProvider imageProvider = SemanticBrowserImageProvider.getInstance();
      cellPainter = new TextAndLabelImagePainter(imageProvider);
    }

    return cellPainter;
  }

  @Override
  protected MADisplayConverter createDisplayConverter() {

    if (displayConverter == null) {
      displayConverter = new SemanticBrowserDisplayConverter();
    }

    return displayConverter;
  }

  @Override
  protected IDataValidator createDataValidator() {
    return new SemanticBrowserDataValidator();
  }

  @Override
  protected Comparator<Object> createCellComparator() {
    return new MADisplayComparator(createDisplayConverter());
  }

  @Override
  public Object getDataValue(EObject rowObject) {
    return category.compute(rowObject);
  }

  @Override
  public void setDataValue(EObject rowObject, Object newValue) {
    // do nothing
  }

  @Override
  public void dataChanged(Collection<EObject> newData) {
    // do nothing
  }

}
