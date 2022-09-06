/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.properties.richtext.fields;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.sirius.common.tools.api.query.IllegalStateExceptionQuery;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.progress.UIJob;
import org.eclipse.ui.views.properties.tabbed.ISection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.model.handler.provider.CapellaReadOnlyHelper;
import org.polarsys.capella.core.model.handler.provider.IReadOnlySectionHandler;
import org.polarsys.capella.core.ui.properties.helpers.NotificationHelper;
import org.polarsys.capella.core.ui.properties.richtext.CapellaUIPropertiesRichtextPlugin;
import org.polarsys.capella.core.ui.properties.richtext.RichtextManager;
import org.polarsys.kitalpha.richtext.common.intf.MDERichTextWidget;
import org.polarsys.kitalpha.richtext.common.intf.SaveStrategy;
import org.polarsys.kitalpha.richtext.nebula.widget.MDENebulaBasedRichTextWidget;
import org.polarsys.kitalpha.richtext.widget.helper.MDERichtextWidgetHelper;

/**
 * @author Joao Barata
 */
public abstract class ElementDescriptionGroup {

  private static final String FAMILY_DEFERRED_REFRESH = CapellaUIPropertiesRichtextPlugin.PLUGIN_ID
      + ".refreshJobFamily";

  private static final String FAMILY_LOAD_DATA_REFRESH = CapellaUIPropertiesRichtextPlugin.PLUGIN_ID
      + ".loadDataJobFamily";

  /**
   * Current edited semantic element.
   */
  protected EObject semanticElement;

  /**
   * Handle semantic element's feature handled by this field.
   */
  protected EStructuralFeature semanticFeature;

  /**
   * The section where this element is created.
   */
  private ISection section;

  /**
   * Text widget.
   */
  protected MDERichTextWidget descriptionTextField;

  protected Composite descriptionContainer;

  protected GridData infoLabelGridData;

  protected Label infoLabel;

  protected Button reloadBtn;

  protected GridData reloadBtnGridData;

  protected Composite parentComposite;

  private String baseHrefPath;

  private static final String EXISTED_EDITOR_TEXT = "The description is currently opened in an editor. Please use this editor to edit your description."; //$NON-NLS-1$

  private static final String EDITOR_LOADING_TEXT = "Attempt(s) loading the editor: "; //$NON-NLS-1$

  private class SavingStrategy implements SaveStrategy {

    EObject owner;

    EStructuralFeature feature;

    // Does the saveStrategy has to perform the last save before its disposal.
    // If null, the last save is not yet there.
    // If true, the last save has to be done
    // If false, the last save has been done and another save is not welcome as the given owner may have changed
    Boolean lastSave = null;

    public SavingStrategy() {
    }

    public SavingStrategy(EObject owner, EStructuralFeature feature) {
      this.owner = owner;
      this.feature = feature;
    }

    @Override
    public void save(final String editorText, final EObject owner, final EStructuralFeature feature) {
      // Due to async 'lost focus' event, the given owner in parameter can be the next one and not the intended one

      // Usecase:
      // On ProjectExplorer, select an element. Change its description, keep edition focus, then choose another element
      // in explorer.
      // Save is called twice, one in loadData/within bind method with previous element, then another one in lostFocus
      // with new one but with old text.
      if (lastSave == null && this.owner == owner && this.feature == feature) {
        setDataValue(owner, feature, editorText);

      } else if (Boolean.TRUE.equals(lastSave)) {
        // Usecase:
        // On two opened diagrams with description tab opened, Change description of element, keep edition focus, then
        // switch tab to other diagram.
        // Last save may be lost by the previous if.

        setDataValue(owner, feature, editorText);
        lastSave = Boolean.FALSE;
      }
    }

    /**
     * Mark the save strategy to ensure that last save of the editor will be performed
     */
    public void ensureLastSave() {
      if (lastSave == null) {
        lastSave = Boolean.TRUE;
      }
    }
  }

  /**
   * @param parent
   * @param widgetFactory
   */
  public ElementDescriptionGroup(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory, ISection section) {
    super();

    parentComposite = parent;

    infoLabel = widgetFactory.createLabel(parent, EXISTED_EDITOR_TEXT);
    reloadBtn = widgetFactory.createButton(parent, "Reload", SWT.PUSH);
    reloadBtn.addSelectionListener(new SelectionListener() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        loadData(semanticElement, semanticFeature);
      }

      @Override
      public void widgetDefaultSelected(SelectionEvent e) {

      }
    });

    infoLabelGridData = new GridData(SWT.FILL, SWT.CENTER, false, false);
    infoLabel.setLayoutData(infoLabelGridData);
    hideInfoText();

    reloadBtnGridData = new GridData(SWT.BEGINNING, SWT.CENTER, false, false);
    reloadBtn.setLayoutData(reloadBtnGridData);
    hideReloadButton();

    // Do not propagate the focus event to the embedded editor
    descriptionContainer = new Composite(parent, SWT.NONE) {
      @Override
      public boolean setFocus() {
        return true;
      }

      @Override
      protected void checkSubclass() {
        // Do nothing
      }
    };
    if (widgetFactory != null) {
      widgetFactory.adapt(descriptionContainer);
    }
    GridLayoutFactory.fillDefaults().extendedMargins(0, 0, 0, 0).applyTo(descriptionContainer);
    descriptionContainer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

    descriptionTextField = RichtextManager.getInstance().getRichtextWidget(descriptionContainer);
    descriptionTextField.setSaveStrategy(new SavingStrategy());

    this.section = section;
  }

  /**
   * Move the editor to the new container
   */
  public void aboutToBeShown() {
    if (updateDescriptionEditability(semanticElement, semanticFeature)) {
      try {
        if (semanticElement != null && semanticFeature != null) {
          ((SavingStrategy) descriptionTextField.getSaveStrategy()).ensureLastSave();
          descriptionTextField.bind(semanticElement, semanticFeature);
          descriptionTextField.setSaveStrategy(new SavingStrategy(semanticElement, semanticFeature));
        }
      } catch (SWTException e) {
        // Catch SWT "Permission denied" exception raised by Nebula Richtext
      } catch (IllegalStateException e2) {
        if (new IllegalStateExceptionQuery(e2).isAConnectionLostException()) {
          // Catch the potential exception in case of connection lost with server
          // (Team4Capella)
        } else {
          throw e2;
        }
      }
    }
  }

  /**
   * Remove the editor before the Property tab is disposed
   */
  public void aboutToBeHidden() {
    hideEditor();
  }

  /**
   * 
   */
  public void dispose() {
    // Do nothing
  }

  /**
   * Execute given command.
   * 
   * @param command
   */
  protected void executeCommand(final ICommand command, final EObject owner, final EStructuralFeature feature) {
    // Precondition
    if ((null == command)) {
      return;
    }
    // Command to run.
    ICommand cmd = command;
    // Encapsulate given command in a new one to enable undo / redo refresh
    // based on getAffectedObjects.
    // AbstractSection call getAffectedObjects() against the command
    // provided by the OperationHistory.
    if (command instanceof AbstractReadWriteCommand) {
      cmd = new AbstractReadWriteCommand() {
        /**
         * @see java.lang.Runnable#run()
         */
        @Override
        public void run() {
          command.run();
        }

        /**
         * @see org.polarsys.capella.common.tig.ef.command.AbstractCommand#getAffectedObjects()
         */
        @SuppressWarnings("synthetic-access")
        @Override
        public Collection<?> getAffectedObjects() {
          return Collections.singletonList(owner);
        }

        /**
         * @see org.polarsys.capella.common.tig.ef.command.AbstractCommand#getName()
         */
        @Override
        public String getName() {
          return "Model Edition"; //$NON-NLS-1$
        }

        /**
         * @see org.polarsys.capella.common.tig.ef.command.AbstractCommand#commandInterrupted()
         */
        @Override
        public void commandInterrupted() {
          commandRolledBack();
        }

        /**
         * @see org.polarsys.capella.common.tig.ef.command.AbstractCommand#commandRolledBack()
         */
        @Override
        public void commandRolledBack() {
          IReadOnlySectionHandler roHandler = CapellaReadOnlyHelper.getReadOnlySectionHandler();
          if ((roHandler != null) && roHandler.isLockedByOthers(owner))
            return;

          // Reload data >> refresh the UI.
          loadData(owner, feature);
        }
      };
    }
    // Execute it against the TED.
    ExecutionManager executionManager = TransactionHelper.getExecutionManager(owner);
    if (executionManager != null) {
      executionManager.execute(cmd);
    }
  }

  /**
   * @param enabled
   */
  public void setEnabled(boolean enabled) {
    try {
      if (descriptionTextField.isReady()) {
        descriptionTextField.setEditable(enabled);
      } else {
        scheduleDeferredRefresh();
      }
    } catch (Exception e) {
      // If setEditable throws an exception, we schedule a deferred refresh
      scheduleDeferredRefresh();
    }
  }

  protected void scheduleDeferredRefresh() {
    Job[] jobs = Job.getJobManager().find(FAMILY_DEFERRED_REFRESH);
    if (section != null && jobs.length == 0) {
      UIJob job = new UIJob("Refresh of Description editor") {

        @Override
        public IStatus runInUIThread(IProgressMonitor monitor) {
          section.refresh();
          return Status.OK_STATUS;
        }

        @Override
        public boolean belongsTo(Object family) {
          return FAMILY_DEFERRED_REFRESH.equals(family);
        }
      };
      job.setSystem(true);
      job.schedule(1000);
    }
  }

  /**
   * @param element
   * @param feature
   */
  public void loadData(EObject element, EStructuralFeature feature) {
    if (!parentComposite.isDisposed() && parentComposite.isVisible()) {
      try {
        // if editable, we update the content
        if (updateDescriptionEditability(element, feature)) {
          loadEditorContent(element, feature);
        } else {
          // if not editable, we still update the semantic element of the view. Otherwise aboutToBeShown may be called
          // without knowing semanticElement
          if (element != semanticElement) {
            semanticElement = element;
            semanticFeature = feature;
          }
        }
      } catch (SWTException e) {
        // Catch SWT "Permission denied" exception raised by Nebula Richtext
      } catch (IllegalStateException e2) {
        if (new IllegalStateExceptionQuery(e2).isAConnectionLostException()) {
          // Catch the potential exception in case of connection lost with server
          // (Team4Capella)
        } else {
          throw e2;
        }
      }
    }
  }

  protected void loadEditorContent(EObject element, EStructuralFeature feature) {
    semanticElement = element;
    semanticFeature = feature;
    if (descriptionTextField instanceof MDENebulaBasedRichTextWidget) {
      ((MDENebulaBasedRichTextWidget) descriptionTextField).setDirtyStateUpdated(true);
    }

    // We setSaveStrategy after the bind, as bind also do a saveContent on previous element.
    ((SavingStrategy) descriptionTextField.getSaveStrategy()).ensureLastSave();
    descriptionTextField.bind(semanticElement, semanticFeature);
    descriptionTextField.setSaveStrategy(new SavingStrategy(semanticElement, semanticFeature));
  }

  /**
   * Set data value i.e change given object for given feature with specified value.
   * 
   * @param object
   * @param feature
   * @param value
   */
  protected void setDataValue(final EObject object, final EStructuralFeature feature, final Object value) {
    if (NotificationHelper.isNotificationRequired(object, feature, value)) {
      AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
        @Override
        public void run() {
          object.eSet(feature, value);
        }
      };
      executeCommand(command, object, feature);
    }
  }

  /**
   * Save a description into related element.
   * 
   * @param newDescription
   */
  public void saveDescription(String newDescription) {
    descriptionTextField.setText(newDescription);
    descriptionTextField.saveContent();
  }

  public void save(String value) {
    saveDescription(value);
  }

  /**
   * Save the text field's content
   */
  public void save() {
    if (descriptionTextField.getElement() != null && descriptionTextField.getFeature() != null) {
      descriptionTextField.saveContent();
    }
  }

  /**
   * @see org.eclipse.emf.IRichTextSaveable.runtime.capella.richtext.IEEFRichTextSaveable#getValueToSave()
   */
  public String getValueToSave() {
    return descriptionTextField.getText();
  }

  /**
   * Update the editor enablement according to the given element.
   * 
   * Returns whether the editor is in editable state
   */
  protected boolean updateDescriptionEditability(EObject object, EStructuralFeature feature) {
    // Only allow to edit the description using the property view when no
    // other editor concerning the same semantic element is opened
    boolean isEditable = MDERichtextWidgetHelper.getActiveMDERichTextEditors(object).isEmpty();
    boolean isOn = RichtextManager.getInstance().isOnWidget(descriptionContainer);
    boolean isVisible = descriptionContainer.isVisible();
    boolean isValidState = ((isEditable && isVisible && isOn) || (!isEditable && !isVisible && !isOn));

    // Here, we avoid to update the view if it is already in a valid state
    if (!isValidState) {
      descriptionContainer.setVisible(isEditable);
      if (isEditable) {
        // Hide the text about "existed editor" and update the existing richtext field
        hideInfoText();
        hideReloadButton();
        descriptionTextField = showEditor();
        // As the viewer may have been recreated, we re-set the saving strategy here
        descriptionTextField.setSaveStrategy(new SavingStrategy(semanticElement, semanticFeature));
      } else {
        // Show the text about "existed editor"
        showExistedEditorText();
        hideEditor();
      }
      // Recalculate the layout
      isValidState = true;

      if (descriptionTextField.isLoading()) {
        int[] counter = { 1 };
        Job[] jobs = Job.getJobManager().find(FAMILY_LOAD_DATA_REFRESH);
        if (jobs.length == 0) {
          UIJob job = new UIJob("Load editor data") {
            @Override
            public IStatus runInUIThread(IProgressMonitor monitor) {
              if (descriptionTextField.isLoading()) {
                hideEditor();
                showEditorLoadingText(counter[0]++);
                if (counter[0] > 10) {
                  showReloadButton();
                } else {
                  schedule(1000);
                }
              } else {
                loadData(semanticElement, semanticFeature);
              }
              return Status.OK_STATUS;
            }

            @Override
            public boolean belongsTo(Object family) {
              return FAMILY_LOAD_DATA_REFRESH.equals(family);
            }
          };
          job.setSystem(true);
          job.schedule(1000);
        }
        // Stop feeding more data to editor while it's still loading
        return false;
      }
    }

    // Make sure editor's base href path is always set
    if (descriptionTextField != null) {
      descriptionTextField.setBaseHrefPath(baseHrefPath);
    }

    return isValidState && isEditable;
  }

  protected void showEditorLoadingText(int counter) {
    infoLabel.setText(EDITOR_LOADING_TEXT + counter);
    infoLabel.setVisible(true);
    infoLabelGridData.exclude = false;
    parentComposite.layout(true);
  }

  protected void showExistedEditorText() {
    infoLabel.setText(EXISTED_EDITOR_TEXT);
    infoLabel.setVisible(true);
    infoLabelGridData.exclude = false;
    parentComposite.layout(true);
  }

  protected void hideInfoText() {
    infoLabel.setVisible(false);
    infoLabelGridData.exclude = true;
    parentComposite.layout(true);
  }

  protected void showReloadButton() {
    reloadBtn.setVisible(true);
    reloadBtnGridData.exclude = false;
    parentComposite.layout(true);
  }

  protected void hideReloadButton() {
    reloadBtn.setVisible(false);
    reloadBtnGridData.exclude = true;
    parentComposite.layout(true);
  }

  protected MDERichTextWidget showEditor() {
    MDERichTextWidget widget;
    if (!RichtextManager.getInstance().isOnWidget(descriptionContainer)) {
      widget = RichtextManager.getInstance().addWidget(descriptionContainer);
    } else {
      widget = RichtextManager.getInstance().getRichtextWidget(descriptionContainer);
    }

    return widget;
  }

  protected void hideEditor() {
    if (!descriptionContainer.isDisposed() && RichtextManager.getInstance().isOnWidget(descriptionContainer)) {
      RichtextManager.getInstance().removeWidget(descriptionContainer);
    }
  }

  public boolean shouldRefresh() {
    return descriptionTextField == null || !descriptionTextField.hasFocus();
  }

  public String getBaseHrefPath() {
    return baseHrefPath;
  }

  public void setBaseHrefPath(String baseHrefPath) {
    this.baseHrefPath = baseHrefPath;
  }

}
