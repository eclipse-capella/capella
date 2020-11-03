/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.ui.massactions.core.edit.control.single;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.nebula.widgets.nattable.data.convert.IDisplayConverter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.polarsys.capella.common.ui.massactions.core.activator.MECoreCapellaActivator;
import org.polarsys.capella.common.ui.massactions.core.shared.helper.CommonElementsHelper;
import org.polarsys.capella.common.ui.massactions.core.shared.helper.container.CommonBQInput;
import org.polarsys.capella.common.ui.massactions.core.shared.helper.container.CommonBQResult;
import org.polarsys.capella.common.ui.massactions.core.shared.messages.Messages;
import org.polarsys.capella.core.ui.properties.helpers.DialogHelper;
import org.polarsys.kitalpha.massactions.core.control.AbstractMAPrimitiveCellControl;
import org.polarsys.kitalpha.massactions.core.helper.EObjectImageProvider;

/**
 * A cell control handling single references.
 * 
 * @author Sandu Postaru
 *
 */
public class SingleRefCellControl extends AbstractMAPrimitiveCellControl {

  private EObject canonicalValue;
  private List<CommonBQInput> commonBQInputs;

  private Text editorValueText;
  private ToolItem editorIconToolItem;

  private final SelectionAdapter browseSelectionAdapter = new SelectionAdapter() {
    @Override
    public void widgetSelected(SelectionEvent event) {
      if (commonBQInputs != null) {

        CommonBQResult commonBusinessQueryResult = CommonElementsHelper
            .getAllCommonBusinessQueryResults(commonBQInputs);
        List<EObject> commonAvailableElements = commonBusinessQueryResult.getCommonAvailableElements();

        EObject selectedElement = DialogHelper.openSimpleSelectionDialog(SingleRefCellControl.this,
            commonAvailableElements);

        if (selectedElement != null) {
          setCanonicalValue(selectedElement);
        }
      }
    }
  };

  public SingleRefCellControl(Composite parent, int style, IDisplayConverter displayConverter,
      Map<String, EStructuralFeature> featureMap) {
    super(parent, style, displayConverter, featureMap);

    initialize();
  }

  protected void initialize() {
    GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
    setLayoutData(gridData);

    final GridLayout gridLayout = new GridLayout(3, false);
    gridLayout.marginHeight = 0;
    gridLayout.marginWidth = 0;
    gridLayout.horizontalSpacing = 0;

    setLayout(gridLayout);

    ToolBar iconToolBar = new ToolBar(this, SWT.HORIZONTAL);
    editorIconToolItem = new ToolItem(iconToolBar, SWT.CHECK);
    editorIconToolItem.setEnabled(false);

    editorValueText = new Text(this, SWT.SINGLE);
    editorValueText.setEditable(false);
    editorValueText.setCursor(Display.getCurrent().getSystemCursor(SWT.CURSOR_ARROW));
    editorValueText.setText(Messages.NO_COMMON_VALUE_TEXT);
    editorValueText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

    ToolBar browseToolBar = new ToolBar(this, SWT.HORIZONTAL);

    ToolItem browse = new ToolItem(browseToolBar, SWT.PUSH);
    browse.setToolTipText(Messages.ELEMENT_SELECTION_TOOLTIP_TEXT);
    Image browseImage = MECoreCapellaActivator.getDefault().getImageRegistry().get(MECoreCapellaActivator.IMAGE_BROWSE);
    browse.setImage(browseImage);
    browse.addSelectionListener(browseSelectionAdapter);
  }

  @Override
  public void setSelectedRowObjects(List<EObject> selectedRowObjects) {
    super.setSelectedRowObjects(selectedRowObjects);
    this.commonBQInputs = selectedRowObjects.stream()
        .map(rowObject -> new CommonBQInput(rowObject, featureMap.get(rowObject.eClass().getName())))
        .collect(Collectors.toList());
  }

  @Override
  public void setEditorValue(Object editorValue) {
    if (editorValue instanceof String) {
      this.editorValueText.setText((String) editorValue);
    }
  }

  @Override
  public Object getEditorValue() {
    Object currentCanonicalValue = getCanonicalValue();
    return getDisplayConverterValue(currentCanonicalValue);
  }

  @Override
  public EObject getCanonicalValue() {
    return canonicalValue;
  }

  @Override
  public void setCanonicalValue(Object value) {

    if (value instanceof EObject) {

      canonicalValue = (EObject) value;

      Object editorValue = getDisplayConverterValue(canonicalValue);
      setEditorValue(editorValue);

      Image image = EObjectImageProvider.getInstance().getImage(canonicalValue);
      editorIconToolItem.setDisabledImage(image);
      editorIconToolItem.setImage(image);
    }
  }

}
