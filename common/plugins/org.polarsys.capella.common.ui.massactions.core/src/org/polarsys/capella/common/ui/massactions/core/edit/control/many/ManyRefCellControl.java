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
package org.polarsys.capella.common.ui.massactions.core.edit.control.many;

import java.util.Collections;
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
import org.polarsys.capella.common.ui.massactions.core.edit.editor.many.ManyRefTransitionalValue;
import org.polarsys.capella.common.ui.massactions.core.shared.helper.CommonElementsHelper;
import org.polarsys.capella.common.ui.massactions.core.shared.helper.container.CommonBQInput;
import org.polarsys.capella.common.ui.massactions.core.shared.helper.container.CommonBQResult;
import org.polarsys.capella.common.ui.massactions.core.shared.messages.Messages;
import org.polarsys.capella.core.ui.properties.helpers.DialogHelper;
import org.polarsys.kitalpha.massactions.core.control.AbstractMAPrimitiveCellControl;
import org.polarsys.kitalpha.massactions.core.helper.EObjectImageProvider;

/**
 * A cell control handling many (multiple) references.
 * 
 * @author Sandu Postaru
 *
 */
public class ManyRefCellControl extends AbstractMAPrimitiveCellControl {

  private static final int DISPLAY_VALUE_MAX_LENGTH = 60;

  private ManyRefTransitionalValue canonicalValue;
  private List<CommonBQInput> commonBQInputs;

  private Text editorValueText;
  private ToolItem editorIconToolItem;

  private final SelectionAdapter browseSelectionAdapter = new SelectionAdapter() {
    @Override
    public void widgetSelected(SelectionEvent event) {
      if (commonBQInputs != null) {

        CommonBQResult commonBusinessQueryResult = CommonElementsHelper
            .getAllCommonBusinessQueryResults(commonBQInputs);

        if (!commonBusinessQueryResult.isEmpty()) {

          List<EObject> commonAvailableElements = commonBusinessQueryResult.getCommonAvailableElements();
          List<EObject> commonCurrentElements = commonBusinessQueryResult.getCommonCurrentElements();

          commonAvailableElements.removeAll(commonCurrentElements);

          List<EObject> selectedElements = DialogHelper.openTransferDialog(ManyRefCellControl.this,
              commonCurrentElements, commonAvailableElements, Messages.MANY_REF_DIALOG_TITLE,
              Messages.MANY_REF_DIALOG_MESSAGE);

          if (selectedElements != null) {
            ManyRefTransitionalValue selection = new ManyRefTransitionalValue(selectedElements, commonCurrentElements);
            setCanonicalValue(selection);
          }
        }
      }
    }
  };

  public ManyRefCellControl(Composite parent, int style, IDisplayConverter displayConverter,
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

    ToolBar toolbar = new ToolBar(this, SWT.HORIZONTAL);

    ToolItem browse = new ToolItem(toolbar, SWT.PUSH);
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
  public Object getEditorValue() {
    Object currentCanonicalValue = getCanonicalValue();
    return getDisplayConverterValue(currentCanonicalValue);
  }

  @Override
  public void setEditorValue(Object editorValue) {
    if (editorValue instanceof String) {
      String stringValue = (String) editorValue;

      // if string is not empty => set the string
      // if is empty => leave the default value
      if (!stringValue.isEmpty()) {

        if (stringValue.length() > DISPLAY_VALUE_MAX_LENGTH) {
          stringValue = stringValue.substring(0, DISPLAY_VALUE_MAX_LENGTH - 3) + "...";
        }
        this.editorValueText.setText(stringValue);
      }
    }
  }

  @Override
  public ManyRefTransitionalValue getCanonicalValue() {
    return canonicalValue;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void setCanonicalValue(Object value) {

    // due to the dual nature of the canonical value for this editor
    // (List<EObject> for the column value but ManyRefTransitionalValue for
    // the control value)
    // we must check for the instance of the current value

    if (value != null) {

      List<EObject> actualCanonicalValue = Collections.emptyList();

      if (value instanceof List<?>) {
        actualCanonicalValue = (List<EObject>) value;

      } else if (value instanceof ManyRefTransitionalValue) {
        canonicalValue = (ManyRefTransitionalValue) value;
        actualCanonicalValue = canonicalValue.getCommonSelectedValues();
      }

      Object editorValue = getDisplayConverterValue(actualCanonicalValue);
      setEditorValue(editorValue);

      if (!actualCanonicalValue.isEmpty()) {
        Image image = EObjectImageProvider.getInstance().getImage(actualCanonicalValue.get(0));
        editorIconToolItem.setDisabledImage(image);
        editorIconToolItem.setImage(image);
      }
    }
  }
}
