/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.ui.actions;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.common.ui.dialogs.ResourceDialog;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;
import org.eclipse.emf.edit.ui.action.CommandActionHandler;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.mdsofa.common.helper.StringHelper;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.model.handler.provider.CapellaReadOnlyHelper;
import org.polarsys.capella.core.model.handler.provider.IReadOnlySectionHandler;
import org.polarsys.capella.core.sirius.ui.Messages;

/**
 * A control action moves a contained object into a new resource or restores it to its containers' resource. It is implemented by creating a {@link AddCommand}
 * or {@link RemoveCommand}.
 * @since 2.2.0
 */
public class ControlAction extends CommandActionHandler {
  protected IStructuredSelection _selection;
  protected EObject _eObject;
  protected Resource _resource;
  protected boolean _canceled;

  /**
   * Constructor.
   */
  public ControlAction() {
    this(null);
  }

  /**
   * Constructor.
   * @param domain
   */
  @SuppressWarnings("hiding")
  public ControlAction(EditingDomain domain) {
    super(domain, Messages.ControlAction__UI_Control_menu_item);
  }

  /**
   * We can create the RemoveCommand for an uncontrol, but we must defer creating an AddCommand to control until run(), when the user specifies a target
   * resource.
   * @see org.eclipse.emf.edit.ui.action.CommandActionHandler#updateSelection(org.eclipse.jface.viewers.IStructuredSelection)
   */
  @Override
  public boolean updateSelection(IStructuredSelection selection) {
    _selection = selection;

    // Empty selection: do some clean up to avoid memory leak
    if (_selection.isEmpty()) {
      domain = null;
      _eObject = null;
      command = null;
      return false;
    }

    Object object = AdapterFactoryEditingDomain.unwrap(_selection.getFirstElement());
    
    // update editing domain according to the current selection
    domain = TransactionUtil.getEditingDomain(object);
    if (null == domain) {
      return false;
    }

    boolean result = domain.isControllable(object);
    _eObject = result ? (EObject) object : null;

    if(_eObject != null){
      IReadOnlySectionHandler readOnlySectionHandler = CapellaReadOnlyHelper.getReadOnlySectionHandler();
      if (readOnlySectionHandler != null) {
        result &= readOnlySectionHandler.isControllable(_eObject);
      }
    }

    if (!AdapterFactoryEditingDomain.isControlled(object)) {
      setText(Messages.ControlAction__UI_Control_menu_item);
      setDescription(Messages.ControlAction__UI_Control_menu_item_description);
      command = null;
    } else {
      setText(Messages.ControlAction__UI_Uncontrol_menu_item);
      setDescription(Messages.ControlAction__UI_Uncontrol_menu_item_description);
      if (result) {
        command = new RemoveCommand(domain, _eObject.eResource().getContents(), _eObject);
        command = new SelfAffectingCommand(Messages.ControlAction__UI_UncontrolCommand_label, command);
        result = command.canExecute();
      }
    }
    return result;
  }

  /**
   * For the control case, we need a dialog to ask for the URI and obtain the resource, then we create the command.
   * @see org.eclipse.emf.edit.ui.action.CommandActionHandler#run()
   */
  @Override
  public void run() {
    if (null == command) {
      if (null == _eObject) {
        return;
      }

      _resource = getResource();
      if (null == _resource) {
        _canceled = true;
        return;
      }

      command = new AddCommand(domain, _resource.getContents(), _eObject);
      command = new SelfAffectingCommand(Messages.ControlAction__UI_ControlCommand_label, command);
    }

    // Ensure that all proxies are resolved so that references into the
    // controlled object will be saved to reference the new resource.
    EcoreUtil.resolveAll(domain.getResourceSet());

    super.run();
  }

  /**
   * @return
   */
  protected Resource getResource() {
    ControlResourceDialog dialog =
        new ControlResourceDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), domain, _eObject.eResource(), _eObject);
    dialog.open();
    return dialog.getResource();
  }

  /**
   * @param workbenchPart
   */
  public void setActiveWorkbenchPart(IWorkbenchPart workbenchPart) {
    if (workbenchPart instanceof IEditingDomainProvider) {
      domain = ((IEditingDomainProvider) workbenchPart).getEditingDomain();
    }
  }

  protected class SelfAffectingCommand extends CommandWrapper {
    /**
     * @param label
     * @param command
     */
    @SuppressWarnings("hiding")
    public SelfAffectingCommand(String label, Command command) {
      super(label, command);
    }

    @Override
    public Collection<?> getResult() {
      return _selection.toList();
    }

    @Override
    public Collection<?> getAffectedObjects() {
      return _selection.toList();
    }
  }

  /**
   * A save-type {@link ResourceDialog resource dialog} that attempts to create the specified resource and load it, if it already exists.
   * @deprecated since {@link DesignerControlAction} owns its dialog.
   */
  protected static class ControlResourceDialog extends ResourceDialog {
    protected EditingDomain _domain;
    protected Resource _resource;
    protected Resource _currentResource;
    protected EObject _controledObject;

    /**
     * @param parent
     * @param domain
     * @param currentResource
     */
    public ControlResourceDialog(Shell parent, EditingDomain domain, Resource currentResource, final EObject controledObject) {
      super(parent, Messages.ControlAction_Window_Title, SWT.SAVE);
      _domain = domain;
      _currentResource = currentResource;
      _controledObject = controledObject;
    }

    /**
     * Create the content of this dialog.<br>
     * The code is copied from {@link #createDialogArea(Composite)} super method.
     * @param parent
     * @return
     */
    protected Control doCreateDialogArea(Composite parent) {
      // Code from Dialog.createDialogArea.
      // Create a composite with standard margins and spacing
      Composite composite = new Composite(parent, SWT.NONE);
      GridLayout layout = new GridLayout();
      layout.marginHeight = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
      layout.marginWidth = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
      layout.verticalSpacing = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
      layout.horizontalSpacing = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
      composite.setLayout(layout);
      composite.setLayoutData(new GridData(GridData.FILL_BOTH));
      applyDialogFont(composite);

      // Label.
      Label resourceURILabel = new Label(composite, SWT.NULL);
      resourceURILabel.setText(Messages.ControlAction_Label_Title);
      resourceURILabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
      // Text field.
      uriField = new Text(composite, SWT.SINGLE | SWT.BORDER);
      uriField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
      return composite;
    }

    /**
     * @see org.eclipse.emf.common.ui.dialogs.ResourceDialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea(Composite parent) {
      final Control control = doCreateDialogArea(parent);
      final StringBuilder defaultURI = new StringBuilder();
      defaultURI.append(URI.decode(_controledObject.eResource().getURI().trimFileExtension().toString()));
      defaultURI.append(ICommonConstants.UNDERSCORE_CHARACTER);
      defaultURI.append(EMFCoreUtil.getName(_controledObject));
      defaultURI.append('.');
      defaultURI.append(CapellaResourceHelper.CAPELLA_FRAGMENT_FILE_EXTENSION);
      this.uriField.setText(URI.encodeFragment(defaultURI.toString(), true));
      return control;
    }

    /**
     * Creates and, if it already exists, loads the specified resource. This implementation verifies that a resource can be opened for that URI, that the
     * resource is not the object's current container, and that it is not read-only in the editing domain. If there is an existing resource with that URI, it
     * prompts before overriding or adding to it.
     */
    @Override
    protected boolean processResources() {
      List<URI> uris = getURIs();
      if (uris.isEmpty()) {
        return false;
      }

      URI uri = uris.get(0);
      // Precondition: URI extension must be CAPELLA_FRAGMENT_EXTENSION_FILE value.
      if (!CapellaResourceHelper.isCapellaFragment(uri)) {
        // Warn the end-user, that the extension is not as expected.
        String formattedMessage =
            StringHelper.formatMessage(Messages.ControlAction_Fragmentation_Error_Message,
                new String[] { ICommonConstants.POINT_CHARACTER + CapellaResourceHelper.CAPELLA_FRAGMENT_FILE_EXTENSION });
        MessageDialog.openError(getShell(), Messages.ControlAction_Window_Title, formattedMessage);
        return false;
      }
      ResourceSet resourceSet = _domain.getResourceSet();
      Resource theResource = resourceSet.getResource(uri, false);
      boolean resourceInSet = theResource != null;

      if (theResource == _currentResource) {
        MessageDialog.openError(getShell(), EMFEditUIPlugin.INSTANCE.getString("_UI_InvalidURI_label"), EMFEditUIPlugin.INSTANCE //$NON-NLS-1$
            .getString("_WARN_AlreadyInResource")); //$NON-NLS-1$
        return false;
      }
      if (_domain.isReadOnly(theResource)) {
        MessageDialog.openError(getShell(), EMFEditUIPlugin.INSTANCE.getString("_UI_InvalidURI_label"), EMFEditUIPlugin.INSTANCE //$NON-NLS-1$
            .getString("_WARN_ReadOnlyResource")); //$NON-NLS-1$
        return false;
      }

      boolean resourceExists = false;
      try {
        InputStream stream = resourceSet.getURIConverter().createInputStream(uri);
        if (stream != null) {
          resourceExists = true;
          stream.close();
        }
      } catch (IOException exception) {
        // Ignore
      }

      boolean resourceBad = false;
      if (!resourceInSet) {
        theResource = resourceSet.createResource(uri);
        if (null == theResource) {
          MessageDialog.openError(getShell(), EMFEditUIPlugin.INSTANCE.getString("_UI_InvalidURI_label"), EMFEditUIPlugin.INSTANCE //$NON-NLS-1$
              .getString("_WARN_CannotCreateResource")); //$NON-NLS-1$
          return false;
        }

        if (resourceExists) {
          try {
            theResource = resourceSet.getResource(uri, true);
          } catch (RuntimeException exception) {
            EMFEditUIPlugin.INSTANCE.log(exception);
            resourceBad = theResource.getContents().isEmpty();
          }
        }
      }

      boolean result = true;
      if (resourceBad) {
        result = MessageDialog.openQuestion(getShell(), EMFEditUIPlugin.INSTANCE.getString("_UI_ExistingResource_label"), EMFEditUIPlugin.INSTANCE //$NON-NLS-1$
            .getString("_WARN_ReplaceResource")); //$NON-NLS-1$
      } else if (resourceExists) {
        result = MessageDialog.openQuestion(getShell(), EMFEditUIPlugin.INSTANCE.getString("_UI_ExistingResource_label"), EMFEditUIPlugin.INSTANCE //$NON-NLS-1$
            .getString("_WARN_AddToResource")); //$NON-NLS-1$
      }

      if (!result && !resourceInSet && theResource != null) {
        theResource.unload();
        resourceSet.getResources().remove(theResource);
      } else {
        _resource = theResource;
      }
      return result;
    }

    /**
     * @return The resource
     */
    public Resource getResource() {
      return _resource;
    }
  }
}
