/*******************************************************************************
 * Copyright (c) 2006, 2020 Corporation and others.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    IBM - Initial API and implementation 
 *    Thales - Contributor
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.ui.navigator.actions;

import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourceAttributes;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TreeEditor;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.TextActionHandler;
import org.eclipse.ui.actions.WorkspaceAction;
import org.eclipse.ui.ide.undo.MoveResourcesOperation;
import org.eclipse.ui.ide.undo.WorkspaceUndoUtil;
import org.eclipse.ui.internal.ide.IDEWorkbenchMessages;
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;
import org.eclipse.ui.internal.ide.IIDEHelpContextIds;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.mdsofa.common.helper.FileHelper;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;

/**
 * Standard action for renaming the selected resources.
 * <p>
 * This class may be instantiated; it is not intended to be subclassed.
 * </p>
 */
public class RenameResourceAction extends WorkspaceAction {    
  
  /**
   * Regex patterns used in the replacement methods.
   * See each the method documentation for more details.
   */
  private static final String START_COMMON_REGEX_PATTERN = "(>|\\/|\\\")";
  private static final String END_ENCODED_REGEX_PATTERN = "(#|<)";
  private static final String END_RAW_REGEX_PATTERN = "(&)";
  
  private static final String AND_HTML_REPRESENTATION = "&amp;"; //$NON-NLS-1$
  /**
   * Capella Project name attribute.
   */
  private static final String CAPELLA_PROJECT_NAME_ATT = "name=\""; //$NON-NLS-1$
  private static final Logger __logger = ReportManagerRegistry.getInstance()
      .subscribe(IReportManagerDefaultComponents.UI);
  /*
   * The tree editing widgets. If treeEditor is null then edit using the dialog. We keep the editorText around so that
   * we can close it if a new selection is made.
   */
  private TreeEditor treeEditor;

  private Tree navigatorTree;

  private Text textEditor;

  private Composite textEditorParent;

  private TextActionHandler textActionHandler;

  // The resource being edited if this is being done inline
  private IResource inlinedResource;

  private boolean saving = false;

  /**
   * The id of this action.
   */
  public static final String ID = PlatformUI.PLUGIN_ID + ".RenameResourceAction";//$NON-NLS-1$

  /**
   * The new path.
   */
  private IPath newPath;

  private String[] modelProviderIds;

  private Shell _shell;

  private static final String CHECK_RENAME_TITLE = IDEWorkbenchMessages.RenameResourceAction_checkTitle;

  private static final String CHECK_RENAME_MESSAGE = IDEWorkbenchMessages.RenameResourceAction_readOnlyCheck;

  private static String RESOURCE_EXISTS_TITLE = IDEWorkbenchMessages.RenameResourceAction_resourceExists;

  private static String RESOURCE_EXISTS_MESSAGE = IDEWorkbenchMessages.RenameResourceAction_overwriteQuestion;

  private static String PROJECT_EXISTS_MESSAGE = IDEWorkbenchMessages.RenameResourceAction_overwriteProjectQuestion;

  private static String PROJECT_EXISTS_TITLE = IDEWorkbenchMessages.RenameResourceAction_projectExists;

  /**
   * Creates a new action. Using this constructor directly will rename using a dialog rather than the inline editor of a
   * ResourceNavigator.
   * 
   * @param shell
   *          the shell for any dialogs
   */
  public RenameResourceAction(Shell shell) {
    super(shell, IDEWorkbenchMessages.RenameResourceAction_text);
    setToolTipText(IDEWorkbenchMessages.RenameResourceAction_toolTip);
    setId(ID);
    PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IIDEHelpContextIds.RENAME_RESOURCE_ACTION);
    _shell = shell;
  }

  /**
   * Creates a new action.
   * 
   * @param shell
   *          the shell for any dialogs
   * @param tree
   *          the tree
   */
  public RenameResourceAction(Shell shell, Tree tree) {
    this(shell);
    this.navigatorTree = tree;
    this.treeEditor = new TreeEditor(tree);
  }

  /**
   * Check if the user wishes to overwrite the supplied resource
   * 
   * @returns true if there is no collision or delete was successful
   * @param shell
   *          the shell to create the dialog in
   * @param destination
   *          - the resource to be overwritten
   */
  private boolean checkOverwrite(final Shell shell, final IResource destination) {

    final boolean[] result = new boolean[1];

    // Run it inside of a runnable to make sure we get to parent off of the
    // shell as we are not in the UI thread.

    Runnable query = new Runnable() {
      @Override
      @SuppressWarnings("synthetic-access")
      public void run() {
        String pathName = destination.getFullPath().makeRelative().toString();
        String message = RESOURCE_EXISTS_MESSAGE;
        String title = RESOURCE_EXISTS_TITLE;
        if (destination.getType() == IResource.PROJECT) {
          message = PROJECT_EXISTS_MESSAGE;
          title = PROJECT_EXISTS_TITLE;
        }
        result[0] = MessageDialog.openQuestion(shell, title, MessageFormat.format(message, new Object[] { pathName }));
      }

    };

    shell.getDisplay().syncExec(query);
    return result[0];
  }

  /**
   * Check if the supplied resource is read only or null. If it is then ask the user if they want to continue. Return
   * true if the resource is not read only or if the user has given permission.
   * 
   * @return boolean
   */
  private boolean checkReadOnlyAndNull(IResource currentResource) {
    // Do a quick read only and null check
    if (currentResource == null) {
      return false;
    }

    // Do a quick read only check
    final ResourceAttributes attributes = currentResource.getResourceAttributes();
    if ((attributes != null) && attributes.isReadOnly()) {
      return MessageDialog.openQuestion(_shell, CHECK_RENAME_TITLE,
          MessageFormat.format(CHECK_RENAME_MESSAGE, new Object[] { currentResource.getName() }));
    }

    return true;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.actions.WorkspaceAction#createOperation(org.eclipse.core.runtime.IStatus[]) Overridden to
   * create and execute an undoable operation that performs the rename.
   * 
   * @since 3.3
   */
  @Override
  protected IRunnableWithProgress createOperation(final IStatus[] errorStatus) {
    return new IRunnableWithProgress() {
      @Override
      @SuppressWarnings({ "unchecked", "synthetic-access" })
      public void run(IProgressMonitor monitor) {
        final IResource[] resources = (IResource[]) getActionResources()
            .toArray(new IResource[getActionResources().size()]);
        // Precondition : No Sirius session must be open to rename files.
        if (!SessionManager.INSTANCE.getSessions().isEmpty()) {
          _shell.getDisplay().asyncExec(new Runnable() {
            @Override
            public void run() {
              MessageDialog.openWarning(_shell, Messages.RenameResourceAction_Session_Warning_Dialog_Title,
                  NLS.bind(Messages.RenameResourceAction_Session_Warning_Dialog_Message, resources[0].getName()));
            }
          });
          return;
        }
        // Rename is only valid for a single resource. This has already
        // been validated.
        if (resources.length == 1) {
          // check for overwrite
          IResource oldResource = resources[0];
          renameResource(oldResource, newPath, monitor, errorStatus);

          // When renaming the AIRD, rename also the associated AFM file if it has the same old name.
          if (CapellaResourceHelper.AIRD_FILE_EXTENSION.equals(oldResource.getFileExtension())) {
            IPath oldAfmPath = changeExtension(oldResource.getFullPath(), CapellaResourceHelper.AFM_FILE_EXTENSION);
            IWorkspaceRoot workspaceRoot = oldResource.getWorkspace().getRoot();
            IResource oldAfmResource = workspaceRoot.findMember(oldAfmPath);
            // If AFM exists with same name as old AIRD
            if (oldAfmResource != null) {
              String airdContent = "";
              try {
                airdContent = FileHelper.readFile(newPath.toString());
              } catch (UnsupportedEncodingException e) {
                // Ignore the error
              }
              // Encode the last segment of oldAfmPath
              String oldAfmPathEncode = URI.encodeSegment(oldAfmPath.lastSegment(), true);
              // If the AFM is referenced by the AIRD
              if (airdContent.contains("<semanticResources>" + oldAfmPathEncode)) {
                IPath newAfmPath = changeExtension(newPath, CapellaResourceHelper.AFM_FILE_EXTENSION);
                // Rename the AFM
                renameResource(oldAfmResource, newAfmPath, monitor, errorStatus);
              }
            }
          }
        }
      }
    };
  }

  private IPath changeExtension(IPath inPath, String newExtension) {
    IPath newPath = inPath;
    String oldExtension = inPath.getFileExtension();
    if (oldExtension != null && !oldExtension.isEmpty()) {
      newPath = inPath.removeLastSegments(1).append(inPath.lastSegment()
          .replace("." + CapellaResourceHelper.AIRD_FILE_EXTENSION, "." + CapellaResourceHelper.AFM_FILE_EXTENSION));
    }
    return newPath;
  }

  private void renameResource(IResource oldResource, IPath newResourcePath, IProgressMonitor monitor,
      final IStatus[] errorStatus) {
    boolean go = true;
    IWorkspaceRoot workspaceRoot = oldResource.getWorkspace().getRoot();
    IResource newResource = workspaceRoot.findMember(newResourcePath);

    if (newResource != null) {
      go = checkOverwrite(_shell, newResource);
    }
    if (go) {
      // Get current resource name.
      String resourceNameBeforeRenaming = oldResource.getName();
      MoveResourcesOperation op = new MoveResourcesOperation(oldResource, newResourcePath,
          IDEWorkbenchMessages.RenameResourceAction_operationTitle);
      op.setModelProviderIds(getModelProviderIds());
      try {
        PlatformUI.getWorkbench().getOperationSupport().getOperationHistory().execute(op, monitor,
            WorkspaceUndoUtil.getUIInfoAdapter(_shell));
        // Update files that references renaming file.
        boolean updateCapellaProjectName = CapellaResourceHelper.CAPELLA_MODEL_FILE_EXTENSION
            .equals(oldResource.getFileExtension());
        updateReferencesToFile(oldResource.getProject(), resourceNameBeforeRenaming, newResourcePath.lastSegment(),
            updateCapellaProjectName);
      } catch (UnsupportedEncodingException e) {
        errorStatus[0] = new Status(IStatus.ERROR, PlatformUI.PLUGIN_ID,
            "Rename action failed to perform because UTF-8 encoding is not supported", e);
      } catch (ExecutionException e) {
        if (e.getCause() instanceof CoreException) {
          errorStatus[0] = ((CoreException) e.getCause()).getStatus();
        } else {
          errorStatus[0] = new Status(IStatus.ERROR, PlatformUI.PLUGIN_ID, getProblemsMessage(), e);
        }
      }

    }
  }

  Composite createParent() {
    Tree tree = getTree();
    Composite result = new Composite(tree, SWT.NONE);
    TreeItem[] selectedItems = tree.getSelection();
    treeEditor.horizontalAlignment = SWT.LEFT;
    treeEditor.grabHorizontal = true;
    treeEditor.setEditor(result, selectedItems[0]);
    return result;
  }

  /**
   * Create the text editor widget.
   * 
   * @param resource
   *          the resource to rename
   */
  private void createTextEditor(final IResource resource) {
    // Create text editor parent. This draws a nice bounding rect.
    textEditorParent = createParent();
    textEditorParent.setVisible(false);
    final int inset = getCellEditorInset(textEditorParent);
    if (inset > 0) {
      textEditorParent.addListener(SWT.Paint, new Listener() {
        @Override
        @SuppressWarnings("synthetic-access")
        public void handleEvent(Event e) {
          Point textSize = textEditor.getSize();
          Point parentSize = textEditorParent.getSize();
          e.gc.drawRectangle(0, 0, Math.min(textSize.x + 4, parentSize.x - 1), parentSize.y - 1);
        }
      });
    }
    // Create inner text editor.
    textEditor = new Text(textEditorParent, SWT.NONE);
    textEditor.setFont(navigatorTree.getFont());
    textEditorParent.setBackground(textEditor.getBackground());
    textEditor.addListener(SWT.Modify, new Listener() {
      @Override
      @SuppressWarnings("synthetic-access")
      public void handleEvent(Event e) {
        Point textSize = textEditor.computeSize(SWT.DEFAULT, SWT.DEFAULT);
        textSize.x += textSize.y; // Add extra space for new
        // characters.
        Point parentSize = textEditorParent.getSize();
        textEditor.setBounds(2, inset, Math.min(textSize.x, parentSize.x - 4), parentSize.y - 2 * inset);
        textEditorParent.redraw();
      }
    });
    textEditor.addListener(SWT.Traverse, new Listener() {
      @Override
      @SuppressWarnings("synthetic-access")
      public void handleEvent(Event event) {

        // Workaround due to extra
        // traverse events
        switch (event.detail) {
        case SWT.TRAVERSE_ESCAPE:
          // Do nothing in this case
          disposeTextWidget();
          event.doit = true;
          event.detail = SWT.TRAVERSE_NONE;
          break;
        case SWT.TRAVERSE_RETURN:
          saveChangesAndDispose(resource);
          event.doit = true;
          event.detail = SWT.TRAVERSE_NONE;
          break;
        }
      }
    });
    textEditor.addFocusListener(new FocusAdapter() {
      @Override
      public void focusLost(FocusEvent fe) {
        saveChangesAndDispose(resource);
      }
    });

    if (textActionHandler != null) {
      textActionHandler.addText(textEditor);
    }
  }

  /**
   * Opens an error dialog to display the given message.
   * <p>
   * Note that this method must be called from UI thread.
   * </p>
   * 
   * @param message
   *          the message
   */
  void displayError2(String message) {
    MessageDialog.openError(_shell, getProblemsTitle(),
        (null == message) ? IDEWorkbenchMessages.WorkbenchAction_internalError : message);
  }

  /**
   * Close the text widget and reset the editorText field.
   */
  private void disposeTextWidget() {
    if (textActionHandler != null) {
      textActionHandler.removeText(textEditor);
    }

    if (textEditorParent != null) {
      textEditorParent.dispose();
      textEditorParent = null;
      textEditor = null;
      treeEditor.setEditor(null, null);
    }
  }

  /**
   * Returns the elements that the action is to be performed on. Return the resource cached by the action as we cannot
   * rely on the selection being correct for inlined text.
   * 
   * @return list of resource elements (element type: <code>IResource</code>)
   */
  @SuppressWarnings("unchecked")
  @Override
  protected List getActionResources() {
    if (inlinedResource == null) {
      return super.getActionResources();
    }

    List actionResources = new ArrayList();
    actionResources.add(inlinedResource);
    return actionResources;
  }

  /**
   * Return the currently selected resource. Only return an IResouce if there is one and only one resource selected.
   * 
   * @return IResource or <code>null</code> if there is zero or more than one resources selected.
   */
  private IResource getCurrentResource() {
    List<?> resources = getSelectedResources();
    if (resources.size() == 1) {
      return (IResource) resources.get(0);
    }
    return null;
  }

  /**
   * Returns the model provider ids that are known to the client that instantiated this operation.
   * 
   * @return the model provider ids that are known to the client that instantiated this operation.
   * @since 3.2
   */
  public String[] getModelProviderIds() {
    return modelProviderIds;
  }

  /*
   * (non-Javadoc) Method declared on WorkspaceAction.
   */
  @Override
  protected String getOperationMessage() {
    return IDEWorkbenchMessages.RenameResourceAction_progress;
  }

  /*
   * (non-Javadoc) Method declared on WorkspaceAction.
   */
  @Override
  protected String getProblemsMessage() {
    return IDEWorkbenchMessages.RenameResourceAction_problemMessage;
  }

  /*
   * (non-Javadoc) Method declared on WorkspaceAction.
   */
  @Override
  protected String getProblemsTitle() {
    return IDEWorkbenchMessages.RenameResourceAction_problemTitle;
  }

  /**
   * Get the Tree being edited.
   * 
   * @returnTree
   */
  private Tree getTree() {
    return this.navigatorTree;
  }

  /*
   * (non-Javadoc) Method declared on WorkspaceAction. Since 3.3, this method is not used, but an implementation is
   * still provided for compatibility. All work is now done in the operation created in createOperation(IStatus[]).
   */
  @Override
  protected void invokeOperation(IResource resource, IProgressMonitor monitor) {
    // Do nothing.
  }

  /**
   * Return the new name to be given to the target resource.
   * 
   * @return java.lang.String
   * @param resource
   *          the resource to query status on
   */
  protected String queryNewResourceName(final IResource resource) {
    final IWorkspace workspace = IDEWorkbenchPlugin.getPluginWorkspace();
    final IPath prefix = resource.getFullPath().removeLastSegments(1);
    IInputValidator validator = new IInputValidator() {
      @Override
      public String isValid(String string) {
        if (resource.getName().equals(string)) {
          return IDEWorkbenchMessages.RenameResourceAction_nameMustBeDifferent;
        }
        IStatus status = workspace.validateName(string, resource.getType());
        if (!status.isOK()) {
          return status.getMessage();
        }
        if (workspace.getRoot().exists(prefix.append(string))) {
          return IDEWorkbenchMessages.RenameResourceAction_nameExists;
        }
        return null;
      }
    };

    InputDialog dialog = new InputDialog(_shell, IDEWorkbenchMessages.RenameResourceAction_inputDialogTitle,
        IDEWorkbenchMessages.RenameResourceAction_inputDialogMessage, resource.getName(), validator);
    dialog.setBlockOnOpen(true);
    int result = dialog.open();
    if (result == Window.OK) {
      return dialog.getValue();
    }
    return null;
  }

  /**
   * Return the new name to be given to the target resource or <code>null<code> if the query was canceled. Rename the
   * currently selected resource using the table editor. Continue the action when the user is done.
   * 
   * @param resource
   *          the resource to rename
   */
  private void queryNewResourceNameInline(final IResource resource) {
    // Make sure text editor is created only once. Simply reset text
    // editor when action is executed more than once.
    if (textEditorParent == null) {
      createTextEditor(resource);
    }
    textEditor.setText(resource.getName());

    // Open text editor with initial size.
    textEditorParent.setVisible(true);
    Point textSize = textEditor.computeSize(SWT.DEFAULT, SWT.DEFAULT);
    textSize.x += textSize.y; // Add extra space for new characters.
    Point parentSize = textEditorParent.getSize();
    int inset = getCellEditorInset(textEditorParent);
    textEditor.setBounds(2, inset, Math.min(textSize.x, parentSize.x - 4), parentSize.y - 2 * inset);
    textEditorParent.redraw();
    textEditor.selectAll();
    textEditor.setFocus();
  }

  private String replaceSpecialCharacter(String source_p, char specialCharacter_p, String replacingSpecialCharacter_p) {
    String result = source_p;
    if (result.contains(ICommonConstants.EMPTY_STRING + specialCharacter_p)) {
      result = result.replace(ICommonConstants.EMPTY_STRING + specialCharacter_p, replacingSpecialCharacter_p);
    }
    return result;
  }

  /*
   * (non-Javadoc) Method declared on IAction; overrides method on WorkspaceAction.
   */
  @Override
  public void run() {

    if (this.navigatorTree == null) {
      IResource currentResource = getCurrentResource();
      if ((currentResource == null) || !currentResource.exists()) {
        return;
      }
      // Do a quick read only and null check
      if (!checkReadOnlyAndNull(currentResource)) {
        return;
      }
      String newName = queryNewResourceName(currentResource);
      if ((newName == null) || newName.equals("")) { //$NON-NLS-1$
        return;
      }
      newPath = currentResource.getFullPath().removeLastSegments(1).append(newName);
      super.run();
    } else {
      runWithInlineEditor();
    }
  }

  /*
   * Run the receiver using an inline editor from the supplied navigator. The navigator will tell the action when the
   * path is ready to run.
   */
  private void runWithInlineEditor() {
    IResource currentResource = getCurrentResource();
    if (!checkReadOnlyAndNull(currentResource)) {
      return;
    }
    queryNewResourceNameInline(currentResource);
  }

  /**
   * @param path
   *          the path
   * @param resource
   *          the resource
   */
  protected void runWithNewPath(IPath path, IResource resource) {
    this.newPath = path;
    super.run();
  }

  /**
   * Save the changes and dispose of the text widget.
   * 
   * @param resource
   *          - the resource to move.
   */
  protected void saveChangesAndDispose(IResource resource) {
    if (saving == true) {
      return;
    }

    saving = true;
    // Cache the resource to avoid selection loss since a selection of
    // another item can trigger this method
    inlinedResource = resource;
    final String newName = textEditor.getText();
    // Run this in an async to make sure that the operation that triggered
    // this action is completed. Otherwise this leads to problems when the
    // icon of the item being renamed is clicked (i.e., which causes the
    // rename
    // text widget to lose focus and trigger this method).
    Runnable query = new Runnable() {
      @Override
      @SuppressWarnings("synthetic-access")
      public void run() {
        try {
          if (!newName.equals(inlinedResource.getName())) {
            IWorkspace workspace = IDEWorkbenchPlugin.getPluginWorkspace();
            IStatus status = workspace.validateName(newName, inlinedResource.getType());
            if (!status.isOK()) {
              displayError2(status.getMessage());
            } else {
              IPath newPath_l = inlinedResource.getFullPath().removeLastSegments(1).append(newName);
              runWithNewPath(newPath_l, inlinedResource);
            }
          }
          inlinedResource = null;
          // Dispose the text widget regardless
          disposeTextWidget();
          // Ensure the Navigator tree has focus, which it may not if
          // the
          // text widget previously had focus.
          if ((navigatorTree != null) && !navigatorTree.isDisposed()) {
            navigatorTree.setFocus();
          }
        } finally {
          saving = false;
        }
      }
    };
    getTree().getShell().getDisplay().asyncExec(query);
  }

  /**
   * Sets the model provider ids that are known to the client that instantiated this operation. Any potential side
   * effects reported by these models during validation will be ignored.
   * 
   * @param modelProviderIds
   *          the model providers known to the client who is using this operation.
   * @since 3.2
   */
  public void setModelProviderIds(String[] modelProviderIds) {
    this.modelProviderIds = modelProviderIds;
  }

  /**
   * Set the text action handler.
   * 
   * @param actionHandler
   *          the action handler
   */
  public void setTextActionHandler(TextActionHandler actionHandler) {
    textActionHandler = actionHandler;
  }

  /**
   * Update Capella Project Name.
   * 
   * @param newContent_p
   * @param oldName_p
   * @param newName_p
   */
  private String updateCapellaProjectName(String initialContent_p, String oldName_p, String newName_p) {
    String result = null;
    int indexOfCapellaProjectName = initialContent_p.indexOf(CAPELLA_PROJECT_NAME_ATT);
    if (indexOfCapellaProjectName != -1) {
      String oldName = replaceSpecialCharacter(oldName_p, '&', AND_HTML_REPRESENTATION);
      String newName = replaceSpecialCharacter(newName_p, '&', AND_HTML_REPRESENTATION);
      // Capella project name attribute found.
      // Remove file extensions.
      oldName = oldName.substring(0, oldName.indexOf(ICommonConstants.POINT_CHARACTER));
      newName = newName.substring(0, newName.indexOf(ICommonConstants.POINT_CHARACTER));
      oldName = normalizeForName(oldName);
      newName = normalizeForName(newName);
      // We will use StringBuilder to use the method that allow us to replace in a given position with variable length
      StringBuilder content = new StringBuilder(initialContent_p);
      int start = indexOfCapellaProjectName + CAPELLA_PROJECT_NAME_ATT.length();
      int end = initialContent_p.indexOf("\"", start); //$NON-NLS-1$
      content.replace(start, end, newName);
      result = content.toString();
    }
    if (result == null) {
      return initialContent_p;
    }
    return result;
  }

  /**
   * Update references for specified members.
   * 
   * @param members_p
   * @param newName_p
   * @param oldName_p
   * @param updateCapellaProjectName_p
   * @throws CoreException
   * @throws UnsupportedEncodingException
   */
  private void updateReferencesFor(IResource[] members_p, String newName_p, String oldName_p,
      boolean updateCapellaProjectName_p) throws CoreException, UnsupportedEncodingException {
    for (IResource resource : members_p) {
      switch (resource.getType()) {
      case IResource.FILE:
        // Is current resource a capella file or a diagram one different from the file related to newName_p ?
        String fileExtension = resource.getFileExtension();
        if (CapellaResourceHelper.isCapellaResource(resource)
            || CapellaResourceHelper.AIRD_FILE_EXTENSION.equals(fileExtension)
            || CapellaResourceHelper.AIRD_FRAGMENT_FILE_EXTENSION.equals(fileExtension)
            || CapellaResourceHelper.AFM_FILE_EXTENSION.equals(fileExtension)) {
          IFile file = (IFile) resource;
          String filePath = file.getFullPath().toString();
          // Read file content when needed, to avoid useless read...
          String initialContent = null;
          String newContent = null;
          if (resource.getName().equals(newName_p)) {
            if (updateCapellaProjectName_p) {
              initialContent = FileHelper.readFile(filePath);
              // Update the capella project name according to CapellaResourceHelper.CAPELLA_MODEL_FILE_EXTENSION name,
              // otherwise Capella papeete connector
              // fails to export artefacts.
              newContent = updateCapellaProjectName(initialContent, oldName_p, newName_p);
            }
          } else {
            initialContent = FileHelper.readFile(filePath);
            newContent = updateFileContent(initialContent, oldName_p, newName_p);
          }
          // Serialize its updated content if necessary.
          if ((null != initialContent) && !initialContent.equals(newContent)) {
            FileHelper.writeFile(filePath, false, newContent);
            // Force a refresh to make sure the file is synchronized.
            file.refreshLocal(0, new NullProgressMonitor());
          }
        }
        break;
      case IResource.FOLDER:
        // Recurse to process resources contained in folder.
        updateReferencesFor(((IFolder) resource).members(), newName_p, oldName_p, false);
        break;
      }
    }
  }

  /**
   * Updates existing references of oldName to newName. </br>
   * Depending on the reference type, the oldName and newName might need to be encoded. Please reffer to each of the
   * methods for more info.
   * 
   * @param initialFileContent
   * @param oldName
   * @param newName
   * @return the updated file content.
   */
  private String updateFileContent(String initialFileContent, String oldName, String newName) {
    String updatedFileContent = replaceRaw(initialFileContent, oldName, newName);

    updatedFileContent = replaceEncoded(updatedFileContent, oldName, newName);

    updatedFileContent = replaceLegacy(updatedFileContent, oldName, newName);

    return updatedFileContent;
  }

  /**
   * Replace references to files in the description field. </br>
   * These references are not encoded.</br>
   * 
   * <b>Example:</b>
   * <ul>
   * <li>Name: <u>[X].aird</u>, Reference: <code>a href=&quot;file:///E:/workspace/[X]<b>/</b><u>[X].aird</u><b>&</b></code></li>
   * </ul>
   * 
   * @param content
   * @param oldName
   * @param newName
   * @return the new file content.
   */
  private String replaceRaw(String content, String oldName, String newName) {
    String oldNameRaw = normalizeForName(oldName);
    String newNameRaw = normalizeForName(newName);

    String regex = START_COMMON_REGEX_PATTERN + Pattern.quote(oldNameRaw) + END_RAW_REGEX_PATTERN;
    String replacement = "$1" + newNameRaw + "$2";

    return content.replaceAll(regex, replacement);
  }

  /**
   * Replace references to resources. These references are encoded. <b>Example:</b>
   * <b>Example:</b>
   * <ul>
   * <li>Name: <u>[X].capella</u>, Reference: <code>semanticResources<b>></b><u>%5BX%5D.capella</u><b><</b>/semanticResources></code></li>
   * <li>Name: <u>[X].capella</u>, Reference: <code>href="..<b>/</b><u>%5BX%5D.capella</u><b>#</b>1827f11e"</code></li>
   * <li>Name: <u>[X].capella</u>, Reference: <code>href=<b>"</b><u>%5BX%5D.capella</u><b>#</b>1827f11e"</code></li>
   * 
   * </ul>
   * 
   * @param content
   * @param oldName
   * @param newName
   * @return the new file content.
   */
  private String replaceEncoded(String content, String oldName, String newName) {
    String oldNameEncoded = normalizeForContent(oldName);
    String newNameEncoded = normalizeForContent(newName);

    String regex = START_COMMON_REGEX_PATTERN + Pattern.quote(oldNameEncoded) + END_ENCODED_REGEX_PATTERN;
    String replacement = "$1" + newNameEncoded + "$2";

    return content.replaceAll(regex, replacement);
  }

  /**
   * TODO This special case was in the previous implementation. Handle special case. </br>
   * [19/11/2020] Currently there is no way to create a project containing &, <b>this code is left for legacy
   * reasons.</b>
   * 
   * @param content
   * @param oldName
   * @param newName
   * @return the new content.
   */
  private String replaceLegacy(String content, String oldName, String newName) {
    String oldNameEncoded = normalizeForContent(oldName);

    if (oldNameEncoded.contains(Character.toString('&'))) {
      oldNameEncoded = replaceSpecialCharacter(oldNameEncoded, '&', AND_HTML_REPRESENTATION);
      String newNameEncoded = normalizeForContent(newName);

      String regex = START_COMMON_REGEX_PATTERN + Pattern.quote(oldNameEncoded) + END_ENCODED_REGEX_PATTERN;
      String replacement = "$1" + newNameEncoded + "$2";

      return content.replaceAll(regex, replacement);
    }

    return content;
  }

  /**
   * This is how links to resources are serialized
   * 
   * @param content
   * @return
   */
  private String normalizeForContent(String content) {
    String _return = normalizeForName(content);
    // Whitespaces etc. (Not all special characters...)
    return URI.encodeSegment(_return, true);
  }

  /**
   * This is how names of elements are serialized in the resource
   * 
   * @param content
   * @return
   */
  private String normalizeForName(String content) {
    String _return = null;
    try {
      // Special characters � � etc.
      _return = new String(content.getBytes("UTF8")); //$NON-NLS-1$
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return _return;
  }

  /**
   * Update files contained in specified project that reference the old resource name in their content.
   * 
   * @param project_p
   * @param oldName_p
   * @param newName_p
   * @param updateCapellaProjectName_p
   * @throws UnsupportedEncodingException
   */
  protected void updateReferencesToFile(IProject project_p, String oldName_p, String newName_p,
      boolean updateCapellaProjectName_p) throws UnsupportedEncodingException {
    try {
      updateReferencesFor(project_p.members(), newName_p, oldName_p, updateCapellaProjectName_p);
    } catch (CoreException exception_p) {
      StringBuilder loggerMessage = new StringBuilder("RenameResourceAction.updateReferencesToFile(..) _ "); //$NON-NLS-1$
      loggerMessage.append(exception_p.getMessage());
      __logger.warn(new EmbeddedMessage(loggerMessage.toString(), IReportManagerDefaultComponents.UI), exception_p);
    }
  }

  /**
   * The <code>RenameResourceAction</code> implementation of this <code>SelectionListenerAction</code> method ensures
   * that this action is disabled if any of the selections are not resources or resources that are not local.
   */
  @Override
  protected boolean updateSelection(IStructuredSelection selection) {
    disposeTextWidget();

    if (selection.size() > 1) {
      return false;
    }
    // Do not allow to rename folders.
    if (selection.getFirstElement() instanceof IFolder) {
      return false;
    }
    if (!super.updateSelection(selection)) {
      return false;
    }

    IResource currentResource = getCurrentResource();
    if ((currentResource == null) || !currentResource.exists()) {
      return false;
    }

    return true;
  }

  /**
   * Get the inset used for cell editors
   * 
   * @param c
   *          the Control
   * @return int
   */
  private static int getCellEditorInset(Control c) {
    return 1; // one pixel wide black border
  }
}
