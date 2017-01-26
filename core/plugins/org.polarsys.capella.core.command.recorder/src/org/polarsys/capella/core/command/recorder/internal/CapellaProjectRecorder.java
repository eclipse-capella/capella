/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.command.recorder.internal;

import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

import org.polarsys.capella.common.command.recorder.core.manager.AbstractRecorderManager;
import org.polarsys.capella.common.command.recorder.core.manager.utils.NotificationEnum;
import org.polarsys.capella.common.command.recorder.core.project.AbstractProjectRecorder;
import org.polarsys.capella.common.command.recorder.core.writer.INameResolver;
import org.polarsys.capella.common.command.recorder.core.writer.TXTWriterHelper;
import org.polarsys.capella.core.command.recorder.CapellaCommandRecorderActivator;
import org.polarsys.capella.core.command.recorder.internal.utils.CapellaProjectUtils;

/**
 *
 */
public class CapellaProjectRecorder extends AbstractProjectRecorder {
  
  /** the name resolver */
  protected INameResolver nameResolver;
  
  /**
   * Constructor
   */
  public CapellaProjectRecorder(AbstractRecorderManager manager, IProject project) {
    super(manager, project);
    
    nameResolver = new INameResolver() {
      public String getReadableName(EObject eobject) {
        return CapellaProjectUtils.getReadableName(eobject);
      }
      public String getID(EObject eobject) {
        return CapellaProjectUtils.getID(eobject);
      }
    }; 
    
  }
  
  /**
   * {@inheritDoc}
   */
  public String getExtra() {
    //FIXME
    return null;
  }
  
  /**
   * {@inheritDoc}
   */
  public void write(Writer writer) throws IOException {
   
    if (null != _events && !_events.isEmpty() ) {
      
      Map<ENotificationImpl, EObject> map = null;
      EObject root = null;
      EObject subRoot = null;
      int depth = -1;
      for (Data data: _events) {
        map = getDirectImpactedObject(data.getNotification());
        
        for (ENotificationImpl notification: map.keySet()) {

          //
          // remove extra notifications from diagrams
          //
          boolean shouldBeReported = false;
          root = (EObject) notification.getNotifier();
          if (
              null != root &&
              (
                  CapellaProjectUtils.isCapellaElement(root) || 
                  ViewpointPackage.Literals.DREPRESENTATION.isSuperTypeOf(root.eClass()) ||
                  null != CapellaProjectUtils.isDSemanticDecorator(root.eClass())
              )
          ) {
            shouldBeReported = true;
          }
          
          if (shouldBeReported) {
          
            // Notification text
            depth = 0;
            String str = NotificationEnum.getOperationEnum(notification.getEventType()).getLiteral();
            TXTWriterHelper.writeSubEntry(writer, depth, str);
            
            // Main tgt object
            root = (EObject) notification.getNotifier();
            depth++;
            
            TXTWriterHelper.writeSubEntryForEObject(
                writer,
                root,
                nameResolver,
                depth,
                false
            );
            
            // sub entry
            subRoot = map.get(notification);
            if ( null != subRoot) {
              depth++;
              
              TXTWriterHelper.writeSubEntryForEObject(
                  writer,
                  subRoot,
                  nameResolver,
                  depth,
                  true
              );             
            }
            
          }
          
        } // End shouldbereported     
      }
    }
    
    return;
  }
  
  private Map<ENotificationImpl, EObject> getDirectImpactedObject(List<Notification> notifications) {
   
    HashMap<ENotificationImpl, EObject> result = new HashMap<ENotificationImpl, EObject>();
   
    ENotificationImpl notification = null;
    
    for (Object object: notifications) {
     
      if (object instanceof ENotificationImpl) {
        notification = (ENotificationImpl) object;
        
        EObject eobj = null;
        
        // Let's get the directly impacted object
        
        try {
          switch (notification.getEventType()) {
            case Notification.SET:
              eobj = null;
            break;
            case Notification.ADD:
              if (notification.getNewValue() instanceof EObject)
                eobj = (EObject) notification.getNewValue();
            break;
            case Notification.REMOVE:
              if (notification.getOldValue() instanceof EObject)
                eobj = (EObject) notification.getOldValue();
              break;
            default:
              // Do nothing
            break;
          }
        } catch (Exception exception) {
          // DO nothing
        }
        
        // Let's store it
        result.put(notification, eobj);
      }
    }
    
    return result;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public void notifyAddSession(Session newSession) {
    IFile file = WorkspaceSynchronizer.getFile(newSession.getSessionResource());
    if (null != file && file.getProject().equals(project)){
      // directly write to this recorder's output stream. this is a workaround...
      Writer w = _manager.getOutputManager().getWriter(this);
      try {
        TXTWriterHelper.writeEntry(w, "DONE Open session " + newSession.getSessionResource().getURI()); //$NON-NLS-1$
        TXTWriterHelper.writeExtraDataLine(w, new Date().toString());
        w.flush();
      } catch (IOException e ){
        CapellaCommandRecorderActivator.getDefault().getLog().log(
            new Status(IStatus.ERROR, CapellaCommandRecorderActivator.getDefault().getPluginId(), e.getMessage(), e));
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void notifyRemoveSession(Session removedSession) {
    IFile file = WorkspaceSynchronizer.getFile(removedSession.getSessionResource());
    if (null != file && file.getProject().equals(project)){
      // directly write to this recorder's output stream. this is a workaround...
      Writer w = _manager.getOutputManager().getWriter(this);
      try {
        TXTWriterHelper.writeEntry(w, "DONE Close session " + removedSession.getSessionResource().getURI()); //$NON-NLS-1$
        TXTWriterHelper.writeExtraDataLine(w, new Date().toString());
        w.flush();
      } catch (IOException e ){
        CapellaCommandRecorderActivator.getDefault().getLog().log(
            new Status(IStatus.ERROR, CapellaCommandRecorderActivator.getDefault().getPluginId(), e.getMessage(), e));
      }
    }
  }
  
}
