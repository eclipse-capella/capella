/*******************************************************************************
 * Copyright (c) 2017, 2018 THALES GLOBAL SERVICES.
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
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.layout.GridData;
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

  protected GridData existedEditorLayoutData;

  protected Label existedEditorLabel;

  protected Composite parentComposite;

  private static final String EXISTED_EDITOR_TEXT = "The description is currently opened in an editor. Please use this editor to edit your description."; //$NON-NLS-1$

  private class SavingStrategy implements SaveStrategy {

    EObject owner;

    EStructuralFeature feature;

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
      // On ProjectExplorer, select an element. Change its description, then choose another element in explorer.
      // Save is called twice, one in loadData/within bind method with previous element, then another one in lostFocus
      // with new one but with old text.
      if (this.owner == owner && this.feature == feature) {
        setDataValue(owner, feature, editorText);
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

    existedEditorLabel = widgetFactory.createLabel(parent, EXISTED_EDITOR_TEXT);
    existedEditorLayoutData = new GridData();
    existedEditorLabel.setLayoutData(existedEditorLayoutData);
    existedEditorLabel.setVisible(false);
    existedEditorLayoutData.exclude = true;

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
          descriptionTextField.bind(semanticElement, semanticFeature);
          descriptionTextField.setSaveStrategy(new SavingStrategy(semanticElement, semanticFeature));
        }
      } catch (SWTException e) {
        // Catch SWT "Permission denied" exception raised by Nebula Richtext
      }
    }
  }

  /**
   * Remove the editor before the Property tab is disposed
   */
  public void aboutToBeHidden() {
    RichtextManager.getInstance().removeWidget(descriptionContainer);
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
    descriptionContainer.setEnabled(enabled);
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
    try {
      // if editable, we update the content
      if (updateDescriptionEditability(element, feature)) {

        // If we have set or changed the element, then we 'bind' it to the richtext
        if (element != semanticElement || descriptionTextField.getElement() == null) {
          semanticElement = element;
          semanticFeature = feature;
          if (descriptionTextField instanceof MDENebulaBasedRichTextWidget) {
            ((MDENebulaBasedRichTextWidget) descriptionTextField).setDirtyStateUpdated(true);
          }

          // We setSaveStrategy after the bind, as bind also do a saveContent on previous element.
          descriptionTextField.bind(semanticElement, semanticFeature);
          descriptionTextField.setSaveStrategy(new SavingStrategy(semanticElement, semanticFeature));

        } else {
          // Otherwise we just reload the content on the same element
          descriptionTextField.loadContent();
        }

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
    }
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
    descriptionTextField.saveContent();
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
        existedEditorLabel.setVisible(false);
        existedEditorLayoutData.exclude = true;
        descriptionTextField = RichtextManager.getInstance().addWidget(descriptionContainer);
        // As the viewer may have been recreated, we re-set the saving strategy here
        descriptionTextField.setSaveStrategy(new SavingStrategy(semanticElement, semanticFeature));

      } else {
        // Show the text about "existed editor"
        existedEditorLabel.setVisible(true);
        existedEditorLayoutData.exclude = false;
        RichtextManager.getInstance().removeWidget(descriptionContainer);
      }
      // Recalculate the layout
      parentComposite.layout(true);
      isValidState = true;
    }

    return isValidState && isEditable;
  }

  public boolean shouldRefresh() {
    return descriptionTextField == null || !descriptionTextField.hasFocus();
  }
}
