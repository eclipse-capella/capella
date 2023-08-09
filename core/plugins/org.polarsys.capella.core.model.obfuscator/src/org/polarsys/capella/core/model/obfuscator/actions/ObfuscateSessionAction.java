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
 *    Obeo - Do not obfuscate .aird and .airdfragment files when listed as semantic resources.
 *******************************************************************************/
package org.polarsys.capella.core.model.obfuscator.actions;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;
import org.polarsys.capella.common.mdsofa.common.misc.Couple;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.model.obfuscator.CapellaModelObfuscatorActivator;
import org.polarsys.capella.core.model.obfuscator.IImageKeys;
import org.polarsys.capella.core.model.obfuscator.IResourceObfuscator;
import org.polarsys.capella.core.sirius.ui.actions.CloseSessionAction;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;
import org.polarsys.kitalpha.ad.metadata.helpers.ViewpointMetadata;

/**
 * Obfuscate end-user selected Sirius open sessions.
 * 
 */
public class ObfuscateSessionAction extends BaseSelectionListenerAction {

  /**
   * Constructor.
   * 
   * @param text_p
   */
  public ObfuscateSessionAction() {
    super(Messages.ObfuscateModelAction_Title);
    setImageDescriptor(CapellaModelObfuscatorActivator.getDefault().getImageDescriptor(IImageKeys.IMG_OBFUSCATE_MODEL));
  }

  @Override
  public void run() {
    if (!MessageDialog.openConfirm(PlatformUI.getWorkbench().getDisplay().getActiveShell(), Messages.ObfuscateModelAction_ConfirmationDialog_Title,
        Messages.ObfuscateModelAction_ConfirmationDialog_Message)) {
      return;
    }
    obfuscate();
  }

  public void obfuscate() {
    // Let's start
    List<Couple<Session, IFile>> sessions = SessionHelper.getSessionsFromSelection(getStructuredSelection());
    // Loop over sessions to obfuscate them.
    for (Couple<Session, IFile> sessionCouple : sessions) {
      Session session = sessionCouple.getKey();
      Collection<Resource> semanticResources = session.getSemanticResources();
      ExecutionManager executionManager = TransactionHelper.getExecutionManager(session);

      // Obfuscate semantic resources.
      for (Resource resource : semanticResources) {
        URI uri = resource.getURI();
        if (uri.fileExtension() != null
            && !(uri.fileExtension().equals(ViewpointMetadata.STORAGE_EXTENSION))
            && !CapellaResourceHelper.isAirdResource(uri)) {
          obfuscateSemanticResource(resource, executionManager);
        }    
      }
      // Obfuscate all representations.
      for (DRepresentationDescriptor descriptor : DialectManager.INSTANCE.getAllRepresentationDescriptors(session)) {
        obfuscateRepresentation(descriptor, executionManager);
      }

      // obfuscation additional resources through extension point.
      for (Resource resource : getComplementaryResources(semanticResources)) {
        obfuscateComplementaryResource(resource, executionManager);
      }

      // additional migration on aird file when session is opened
      obfuscateFile(sessionCouple.getValue(), session);

      // Semantic resources obfuscated, save the session and close it.
      session.save(new NullProgressMonitor());
      CloseSessionAction closeSessionAction = new CloseSessionAction();
      closeSessionAction.selectionChanged(new StructuredSelection(sessionCouple.getValue()));
      closeSessionAction.run();

      //Perform others operations on session file
      obfuscateFile(sessionCouple.getValue());

    }
  }

  /**
   * additional migration on aird file when session is opened
   * @param value_p
   * @param session_p
   */
  protected void obfuscateFile(IFile value_p, Session session_p) {
    //Nothing here
  }

  /**
   * additional migration on aird file (session is closed)
   * @param value_p
   */
  protected void obfuscateFile(IFile value_p) {
    //Nothing here
  }

  /**
   * retrieve complementary resources
   * @param semanticResources
   * @return
   */
  protected Set<Resource> getComplementaryResources(Collection<Resource> semanticResources) {
    Set<Resource> result = new HashSet<Resource>();
    /* resources loaded in the resourceSet */
    result.addAll(semanticResources.iterator().next().getResourceSet().getResources());
    return result;
  }

  /**
   * obfuscate complementary resources
   * @param resource_p
   * @param executionManager_p
   */
  protected void obfuscateComplementaryResource(final Resource resource_p, ExecutionManager executionManager_p) {
    executionManager_p.execute(new AbstractReadWriteCommand() {
      public void run() {
        IConfigurationElement[] element =
            ExtensionPointHelper.getConfigurationElements(CapellaModelObfuscatorActivator.getDefault().getPluginId(), "Obfuscator");
        for (IConfigurationElement ce : element) {
          IResourceObfuscator obfuscator = (IResourceObfuscator) ExtensionPointHelper.createInstance(ce, "obfuscatorClass");
          if ((obfuscator != null) && obfuscator.isApplicableOn(resource_p)) {
            obfuscator.obfuscate(resource_p);
          }
        }
      }
    });
  }

  /**
   * Obfuscate specified representation.
   * 
   * @param representation_p
   * @param executionManager_p
   */
  protected void obfuscateRepresentation(DRepresentationDescriptor descriptor_p, ExecutionManager executionManager_p) {
    executionManager_p.execute(new ObfuscateRepresentationCommand(descriptor_p));
  }

  /**
   * Obfuscate given resource content.
   * 
   * @param resource_p
   * @param executionManager_p
   */
  protected void obfuscateSemanticResource(Resource resource_p, ExecutionManager executionManager_p) {
    executionManager_p.execute(new ObfuscateSemanticResourceCommand(resource_p));
  }

}
