/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.metric.actions;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyLiteral;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyType;
import org.polarsys.capella.core.diagram.helpers.RepresentationAnnotationHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.ui.metric.MetricMessages;

public class ProgressSetDialog extends Dialog {

  private Label dlgMessage;
  private Label dlgImage;

  private Collection<EObject> affectedObjects;

  /** Selected enumeration */
  private EnumerationPropertyLiteral selectedEnumLiteral = null;

  public enum PropagateChoice {
    NO_MODEL_ELEMENTS, ONLY_BUSINESS_ELEMENTS, ALL_CAPELLA_ELEMENTS,
  }

  private PropagateChoice propagateChoiceFiltering = PropagateChoice.ONLY_BUSINESS_ELEMENTS;

  private boolean propagateToRepresentation = false;

  private boolean useFilterStatus = false;

  private EnumerationPropertyLiteral filterStatus = null;

  private boolean cleanReview = false;

  private boolean propagateStatus = true;

  private Combo combo;

  /**
   * Constructor
   */
  protected ProgressSetDialog(Shell parentShell, Collection<EObject> affectedObjects) {
    super(parentShell);
    this.affectedObjects = affectedObjects;
  }

  /**
   * Accessor for selected enumeration literal
   */
  public EnumerationPropertyLiteral getSelectedEnum() {
    return selectedEnumLiteral;
  }

  /**
   *
   * @return Indicates whether to apply filtering when setting the status for no Model elements.
   */
  public PropagateChoice getPropagateChoiceWithoutFiltering() {
    return propagateChoiceFiltering;
  }

  /**
   *
   * @return Indicates whether to set the propagate the status for referenced graphical representations.
   */
  public boolean isPropagateToRepresentations() {
    return propagateToRepresentation;
  }

  public boolean mustPropagateStatus() {
    return propagateStatus;
  }

  public boolean mustCleanReview() {
    return cleanReview;
  }

  public boolean useFilterStatus() {
    return useFilterStatus;
  }

  public EnumerationPropertyLiteral getFilterStatus() {
    return filterStatus;
  }

  private void refreshWizard() {

    boolean action = propagateStatus || cleanReview;
    boolean propagateOn = propagateToRepresentation || propagateChoiceFiltering != PropagateChoice.NO_MODEL_ELEMENTS;

    // refresh OK button
    if (getButton(OK) != null) {
      getButton(OK).setEnabled(action && propagateOn);
    }

    // refresh dialog message
    if (!action) {

      dlgImage.setImage(JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_ERROR));
      dlgMessage.setText(MetricMessages.progressMonitoring_setAction_dialog_noAction_lbl);

    } else if (!propagateOn) {

      dlgImage.setImage(JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_ERROR));
      dlgMessage.setText(MetricMessages.progressMonitoring_setAction_dialog_noPropagateOn_lbl);

    } else if (propagateStatus) {

      selectedEnumLiteral = (EnumerationPropertyLiteral) combo.getData(String.valueOf(combo.getSelectionIndex()));
      if (selectedEnumLiteral == null) {
        dlgImage.setImage(JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_WARNING));
        dlgMessage.setText(MetricMessages.progressMonitoring_setAction_dialog_clear_lbl);
      } else {
        dlgImage.setImage(JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_INFO));
        dlgMessage.setText(
            NLS.bind(MetricMessages.progressMonitoring_setAction_dialog_main_lbl, selectedEnumLiteral.getLabel()));
      }

    } else {

      dlgImage.setImage(null);
      dlgMessage.setText("");
    }
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("synthetic-access")
  @Override
  protected Control createContents(Composite parent) {

    getShell().setText(MetricMessages.progressMonitoring_setAction_dialog_title);
    getShell().setMinimumSize(300, 200);

    Composite composite = new Composite(parent, SWT.NONE);
    composite.setLayout(new GridLayout(2, false));
    composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    Composite header = new Composite(composite, SWT.NONE);
    header.setLayout(new GridLayout(2, false));
    header.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true, 2, 1));

    dlgImage = new Label(header, SWT.NONE);
    GridData dlgImageLayoutData = new GridData(GridData.BEGINNING, GridData.BEGINNING, false, false, 1, 1);
    dlgImageLayoutData.widthHint = 20;
    dlgImageLayoutData.minimumWidth = 10;
    dlgImage.setLayoutData(dlgImageLayoutData);

    dlgMessage = new Label(header, SWT.NONE);
    dlgMessage.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, true, 1, 1));

    Composite propagateComposite = new Composite(composite, SWT.NONE);
    propagateComposite.setLayout(new GridLayout(2, false));
    propagateComposite.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true, 2, 1));

    // new Label(propagateComposite, SWT.NONE).setText(MetricMessages.progressMonitoring_setAction_dialog_combo_lbl);
    Button propagateStatusField = new Button(propagateComposite, SWT.CHECK);
    propagateStatusField.setText(MetricMessages.progressMonitoring_setAction_dialog_combo_lbl);
    propagateStatusField.setToolTipText(MetricMessages.progressMonitoring_setAction_dialog_combo_lbl);
    propagateStatusField.setSelection(propagateStatus);

    combo = new Combo(propagateComposite, SWT.DROP_DOWN | SWT.READ_ONLY | SWT.BORDER);
    combo.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    combo.addModifyListener(new ModifyListener() {

      @Override
      public void modifyText(ModifyEvent e) {
        refreshWizard();
      }
    });

    propagateStatusField.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {

        propagateStatus = ((Button) e.getSource()).getSelection();
        combo.setEnabled(propagateStatus);

        refreshWizard();
      }
    });

    Button cleanReviewField = new Button(propagateComposite, SWT.CHECK);
    cleanReviewField.setText(MetricMessages.progressMonitoring_clearReview_button_lbl);
    cleanReviewField.setToolTipText(MetricMessages.progressMonitoring_clearReview_button_tooltip);
    cleanReviewField.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        cleanReview = ((Button) e.getSource()).getSelection();

        refreshWizard();
      }
    });

    Group filterGroup = createFilterGroup(composite);

    // Propagate semantic elements without filtering button
    Button propagateNothingWithoutFilteringButton = new Button(filterGroup, SWT.RADIO);
    propagateNothingWithoutFilteringButton
        .setText(MetricMessages.progressMonitoring_dialog_propagate_nothing_button_lbl);
    propagateNothingWithoutFilteringButton
        .setToolTipText(MetricMessages.progressMonitoring_dialog_propagate_nothing_button_tooltip);
    propagateNothingWithoutFilteringButton.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 2, 1));
    propagateNothingWithoutFilteringButton.setSelection(propagateChoiceFiltering == PropagateChoice.NO_MODEL_ELEMENTS);
    propagateNothingWithoutFilteringButton.addSelectionListener(new SelectionAdapter() {

      @Override
      public void widgetSelected(SelectionEvent event) {
        propagateChoiceFiltering = PropagateChoice.NO_MODEL_ELEMENTS;

        refreshWizard();
      }
    });

    // Propagate semantic elements without filtering button
    Button propagateSemanticWithoutFilteringButton = new Button(filterGroup, SWT.RADIO);
    propagateSemanticWithoutFilteringButton
        .setText(MetricMessages.progressMonitoring_dialog_propagate_semantic_button_lbl);
    propagateSemanticWithoutFilteringButton
        .setToolTipText(MetricMessages.progressMonitoring_dialog_propagate_semantic_button_tooltip);
    propagateSemanticWithoutFilteringButton.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 2, 1));
    propagateSemanticWithoutFilteringButton
        .setSelection(propagateChoiceFiltering == PropagateChoice.ONLY_BUSINESS_ELEMENTS);
    propagateSemanticWithoutFilteringButton.addSelectionListener(new SelectionAdapter() {

      @Override
      public void widgetSelected(SelectionEvent event) {
        propagateChoiceFiltering = PropagateChoice.ONLY_BUSINESS_ELEMENTS;

        refreshWizard();
      }
    });

    // Propagate technical elements without filtering button
    Button propagateTechnicalWithoutFilteringButton = new Button(filterGroup, SWT.RADIO);
    propagateTechnicalWithoutFilteringButton
        .setText(MetricMessages.progressMonitoring_dialog_propagate_technical_button_lbl);
    propagateTechnicalWithoutFilteringButton
        .setToolTipText(MetricMessages.progressMonitoring_dialog_propagate_technical_button_tooltip);
    propagateTechnicalWithoutFilteringButton.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 2, 1));
    propagateTechnicalWithoutFilteringButton
        .setSelection(propagateChoiceFiltering == PropagateChoice.ALL_CAPELLA_ELEMENTS);
    propagateTechnicalWithoutFilteringButton.addSelectionListener(new SelectionAdapter() {

      @Override
      public void widgetSelected(SelectionEvent event) {
        propagateChoiceFiltering = PropagateChoice.ALL_CAPELLA_ELEMENTS;

        refreshWizard();
      }
    });

    // Propagate to representations button
    Button propagateToRepresentationButton = new Button(filterGroup, SWT.CHECK);
    propagateToRepresentationButton
        .setText(MetricMessages.progressMonitoring_dialog_propagate_to_representation_button_lbl);
    propagateToRepresentationButton
        .setToolTipText(MetricMessages.progressMonitoring_dialog_propagate_to_representation_button_tooltip);
    propagateToRepresentationButton.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 2, 1));
    propagateToRepresentationButton.setSelection(propagateToRepresentation);
    propagateToRepresentationButton.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent event) {
        propagateToRepresentation = ((Button) event.getSource()).getSelection();

        refreshWizard();
      }
    });

    Button filterStatusButton = new Button(filterGroup, SWT.CHECK);
    filterStatusButton.setText(MetricMessages.progressMonitoring_filterStatus_button_lbl);
    filterStatusButton.setToolTipText(MetricMessages.progressMonitoring_filterStatus_button_tooltip);

    final Combo comboFilter = new Combo(filterGroup, SWT.DROP_DOWN | SWT.READ_ONLY | SWT.BORDER);
    comboFilter.setEnabled(false);
    comboFilter.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    comboFilter.addModifyListener(new ModifyListener() {
      @Override
      public void modifyText(ModifyEvent e) {
        filterStatus = (EnumerationPropertyLiteral) comboFilter
            .getData(String.valueOf(comboFilter.getSelectionIndex()));
      }
    });

    filterStatusButton.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        useFilterStatus = ((Button) e.getSource()).getSelection();
        comboFilter.setEnabled(useFilterStatus);
      }
    });

    fillComboBox(combo);
    // Ask for the index to select
    int index = getSelectIndex(combo);
    combo.select(index);

    fillComboBox(comboFilter);

    return super.createContents(parent);
  }

  private void fillComboBox(Combo combo) {

    EnumerationPropertyType ept = getEnumerationPropertyType();

    // Add the literals
    int i = 0;
    for (EnumerationPropertyLiteral enumLiteral : ept.getOwnedLiterals()) {
      combo.add(enumLiteral.getLabel());
      combo.setData(String.valueOf(i), enumLiteral);
      i++;
    }

    // Add an empty string, at index 0, so that the user is able to unset the status
    combo.add("");
    combo.setData(String.valueOf(i), null);

    combo.notifyListeners(SWT.Modify, new Event());
  }

  private Group createFilterGroup(Composite composite) {
    Group filterGroup = new Group(composite, SWT.NONE);
    GridLayout filterLayout = new GridLayout(2, false);
    filterGroup.setText(MetricMessages.progressMonitoring_filters_group_lbl);
    filterGroup.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 2, 1));

    filterGroup.setLayout(filterLayout);
    filterGroup.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
    filterGroup.setFont(composite.getFont());
    return filterGroup;
  }

  private EnumerationPropertyType getEnumerationPropertyType() {
    EObject semanticElement = null;
    Iterator<EObject> iterator = affectedObjects.iterator();
    while (iterator.hasNext() && semanticElement == null) {
      EObject next = iterator.next();
      if (next instanceof CapellaElement) {
        semanticElement = next;
      } else if (next instanceof DRepresentationDescriptor) {
        semanticElement = ((DRepresentationDescriptor) next).getTarget();
      }
    }
    Assert.isNotNull(semanticElement);
    EnumerationPropertyType ept = CapellaProjectHelper.getEnumerationPropertyType(semanticElement,
        CapellaProjectHelper.PROGRESS_STATUS_KEYWORD);
    return ept;
  }

  private int getSelectIndex(Combo combo) {
    // Collect of statuses from affected objects
    Set<String> labels = new HashSet<String>();
    for (EObject obj : affectedObjects) {
      if (obj instanceof CapellaElement && ((CapellaElement) obj).getStatus() != null) {
        labels.add(((CapellaElement) obj).getStatus().getLabel());
        
      } else if (obj instanceof DRepresentationDescriptor
          && RepresentationAnnotationHelper.hasProgressStatus((DRepresentationDescriptor) obj)) {
        labels.add(RepresentationAnnotationHelper.getProgressStatus((DRepresentationDescriptor) obj).getLabel());
      }
    }
    // If all affected objects have the same status, return its index
    if (labels.size() == 1) {
      int index = combo.indexOf(labels.iterator().next());
      return index != -1 ? index : 0;
    }
    // Else return 0
    return 0;
  }

  @Override
  public boolean close() {
    affectedObjects = null;
    return super.close();
  }

}