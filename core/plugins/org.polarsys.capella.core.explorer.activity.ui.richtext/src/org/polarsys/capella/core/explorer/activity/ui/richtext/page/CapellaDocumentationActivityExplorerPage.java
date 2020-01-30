/*******************************************************************************
 * Copyright (c) 2017, 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.explorer.activity.ui.richtext.page;

import org.eclipse.amalgam.explorer.activity.ui.ActivityExplorerActivator;
import org.eclipse.amalgam.explorer.activity.ui.api.editor.pages.CommonActivityExplorerPage;
import org.eclipse.amalgam.explorer.activity.ui.api.manager.ActivityExplorerManager;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.ui.services.helper.FormHelper;
import org.polarsys.capella.common.ui.services.helper.FormHelper.LayoutType;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellamodeller.ModelRoot;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.model.handler.provider.CapellaReadOnlyHelper;
import org.polarsys.capella.core.model.handler.provider.IReadOnlyListener;
import org.polarsys.capella.core.model.handler.provider.IReadOnlySectionHandler;
import org.polarsys.capella.core.ui.properties.helpers.NotificationHelper;
import org.polarsys.capella.core.ui.properties.richtext.CapellaMDERichTextFactory;
import org.polarsys.kitalpha.richtext.common.intf.MDERichTextWidget;
import org.polarsys.kitalpha.richtext.common.intf.SaveStrategy;

/**
 * Capella Documentation that allows end-user to enter HTML documentation related to the model.
 */
public class CapellaDocumentationActivityExplorerPage extends CommonActivityExplorerPage implements IReadOnlyListener {

  private MDERichTextWidget documentationText;

  /**
   * Default constructor.
   */
  public CapellaDocumentationActivityExplorerPage() {
    this(ActivityExplorerManager.INSTANCE.getEditorFromSession(ActivityExplorerManager.INSTANCE.getSession()));
  }

  /**
   * Constructor.
   * 
   * @param editor
   * @param id
   * @param title
   */
  public CapellaDocumentationActivityExplorerPage(FormEditor editor) {
    super(editor, CapellaDocumentationActivityExplorerPage.class.getName(),
        Messages.CapellaDocumentationActivityExplorerPage_Title);
  }

  /**
   * 
   */
  @Override
  public void dispose() {
    super.dispose();
    if (documentationText != null) {
      documentationText.dispose();
      documentationText = null;
    }
  }

  /**
   * Create the rich text documentation widget.
   * 
   * @param parent
   */
  protected void createDocumentationText(Composite parent) {
    documentationText = new CapellaMDERichTextFactory().createMinimalRichTextWidget(parent);
    documentationText.setSaveStrategy(new SaveStrategy() {
      @Override
      public void save(final String editorText, final EObject owner, final EStructuralFeature feature) {
        if (NotificationHelper.isNotificationRequired(owner, feature, editorText)) {
          AbstractReadWriteCommand saveCommand = new AbstractReadWriteCommand() {
            public void run() {
              owner.eSet(feature, editorText);
            }
          };
          TransactionHelper.getExecutionManager(documentationText.getElement()).execute(saveCommand);
        }
      }
    });
    documentationText.bind(getDocumentedModelElement(), CapellacorePackage.Literals.CAPELLA_ELEMENT__DESCRIPTION);
    // Register with the IReadOnlySectionHandler
    register(getDocumentedModelElement());
  }

  @Override
  public void setActive(boolean active) {
    super.setActive(active);
    // Disable the section if the element is read only.
    IReadOnlySectionHandler roHandler = CapellaReadOnlyHelper.getReadOnlySectionHandler();
    if ((roHandler != null) && roHandler.isLockedByOthers(getDocumentedModelElement())) {
      setInitialEnabledState(false);
    } else {
      setInitialEnabledState(true);
    }
    
    documentationText.loadContent();
  }

  protected void setInitialEnabledState(boolean enabled) {
    if (null != documentationText) {
      documentationText.setEditable(enabled);
    }
  }

  @Override
  protected void createFormContent(IManagedForm managedForm) {
    super.createFormContent(managedForm);
    IEditorInput editorInput = getEditorInput();
    // Set the header title.
    ScrolledForm form = managedForm.getForm();
    form.setText(Messages.CapellaDocumentationActivityExplorerPage_Heading_Word + editorInput.getName());
    // Install a default layout.
    GridLayout layout = new GridLayout();
    layout.horizontalSpacing = 0;
    layout.verticalSpacing = 0;
    Composite body = form.getBody();
    body.setLayout(layout);
    // Create a composite to host the tool bar actions.
    Composite toolbarComposite = FormHelper.createCompositeWithLayoutType(managedForm.getToolkit(), body,
        LayoutType.GRID_LAYOUT, 3 /* 3 tool bar managers */, false);
    toolbarComposite.setLayoutData(new GridData(SWT.BEGINNING, SWT.TOP, false, false));
    // Create the text field.
    createDocumentationText(body);
    // Adapt widgets.
    FormHelper.adaptRecursively(managedForm.getToolkit(), body);
    // Force a layout to make sure, page is well refreshed.
    managedForm.reflow(true);
  }

  /**
   * Read from Editor input the description.
   * 
   * @return
   */
  protected String readDescriptionFromEditorInput() {
    return getDocumentedModelElement().getDescription();
  }

  /**
   * Write to Editor input the description.
   */
  protected void writeDescriptionFromEditorInput(String description) {
    getDocumentedModelElement().setDescription(description);
  }

  /**
   * Get documented element i.e the one that stores the description (i.e the entered documentation).
   * 
   * @return
   */
  protected ModelRoot getDocumentedModelElement() {
    return getCapellaProject().getOwnedModelRoots().get(0);
  }

  private Project getCapellaProject() {
    return (Project) ActivityExplorerManager.INSTANCE.getRootSemanticModel();
  }

  /**
   * @see org.eclipse.emf.eef.runtime.melody.richtext.IEEFRichTextSaveable#getValueToSave()
   */
  public String getValueToSave() {
    return readDescriptionFromEditorInput();
  }

  @Override
  public boolean isVisible() {
    boolean result = ActivityExplorerActivator.getDefault().getPreferenceStore().getBoolean(getId());
    if (predicate != null) {
      result &= predicate.isOk();
    }
    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(boolean enabled) {
    // Disable the editor when someone else is editing the same feature
    if (!enabled && null != documentationText) {
      documentationText.setEditable(enabled);
    }
  }

  @Override
  public void refreshTitleBar() {
    // Do nothing
  }

  /**
   * @param element
   * @return {@link IReadOnlySectionHandler}
   */
  protected IReadOnlySectionHandler register(EObject element) {
    return CapellaReadOnlyHelper.register(element, this);
  }
}
