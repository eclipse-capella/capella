/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.fragmentation.ju.utils.initegrity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DView;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.commandline.core.AbstractCommandLine;
import org.polarsys.capella.core.sirius.analysis.tool.HashMapSet;

/**
 * This class retrieve elements which are duplicated in other resources of the session
 * 
 * Duplicated elements are grouped by resources where they are duplicated
 */
public class IntegrityChecker implements IChecker {

  @Override
  public IStatus getStatus(Session session_p, Logger logger_p, IProgressMonitor monitor_p) {

    monitor_p.beginTask(IntegrityChecker_Checking, 5);

    IProgressMonitor submonitor = new SubProgressMonitor(monitor_p, 2);

    // Retrieve a map of <id,objects>
    HashMapSet<String, EObject> objects = new HashMapSet<String, EObject>();
    for (Resource resource : session_p.getSemanticResources()) {
      TreeIterator<EObject> resourceContent = resource.getAllContents();
      while (resourceContent.hasNext()) {
        EObject object = resourceContent.next();
        String id = EcoreUtil.getID(object);
        objects.put(id, object);
      }
    }
    submonitor.worked(1);

    // for all duplicate objects, sort them per resources
    HashMapSet<String, String> idPerResources = new HashMapSet<String, String>();
    HashSet<String> idResourceMany = new HashSet<String>();
    for (String key : objects.keySet()) {
      if (objects.containsValue(key)) {
        Collection<Resource> resources = new HashSet<Resource>();
        for (EObject item : objects.get(key)) {
          resources.add(item.eResource());
        }

        boolean isMany = false;
        StringBuffer buffer = new StringBuffer();
        Iterator<Resource> itResources = resources.iterator();
        while (itResources.hasNext()) {
          Resource matchingResource = itResources.next();
          String id = matchingResource.getURI().lastSegment();
          buffer.append(id);
          if (itResources.hasNext()) {
            buffer.append(IntegrityChecker_Comma);
            isMany = true;
          }
        }

        String id = buffer.toString();
        idPerResources.put(id, key);
        if (isMany) {
          idResourceMany.add(id);
        }
      }
    }

    submonitor.worked(1);
    // Retrieve a status
    ArrayList<IStatus> statuses = new ArrayList<IStatus>();
    for (String resourcesIdentifier : idPerResources.keySet()) {

      StringBuffer buffer = new StringBuffer();
      if (!idResourceMany.contains(resourcesIdentifier)) {
        buffer.append(NLS.bind(IntegrityChecker_DuplicateElementSolo, resourcesIdentifier));

      } else {
        buffer.append(NLS.bind(IntegrityChecker_DuplicateElement, resourcesIdentifier));
      }

      for (String idDuplicate : idPerResources.get(resourcesIdentifier)) {
        buffer.append(NLS.bind(IntegrityChecker_LogElement,
            EObjectLabelProviderHelper.getText(objects.get(idDuplicate).iterator().next()), idDuplicate));
      }

      statuses.add(new Status(AbstractCommandLine.FATAL, FrameworkUtil.getBundle(this.getClass()).getSymbolicName(), buffer.toString()));
    }

    submonitor.worked(1);
    // Retrieve for each duplicated elements which diagrams use them.
    HashMapSet<DRepresentation, EObject> objectsUses = new HashMapSet<DRepresentation, EObject>();
    HashSet<DRepresentation> diagrams = new HashSet<DRepresentation>();
    Collection<DView> views = session_p.getOwnedViews();
    Collection<Resource> semanticResources = session_p.getSemanticResources();
    submonitor.beginTask(IntegrityChecker_RetrieveDiagramDuplicatedSemantic, semanticResources.size());
    submonitor.setTaskName(IntegrityChecker_RetrieveDiagramDuplicatedSemantic);

    // For all graphical elements
    for (DView airdView : views) {
      for (DRepresentation representation : new DViewQuery(airdView).getLoadedRepresentations()) {
        for (DRepresentationElement element : representation.getRepresentationElements()) {
          if ((element.getTarget() != null) && !(element.getTarget().eIsProxy())) {

            String uriFragment = EcoreUtil.getID(element.getTarget());
            if (objects.containsValue(uriFragment)) {
              objectsUses.put(representation, element.getTarget());
              diagrams.add(representation);
            }
          }
        }
      }
    }

    monitor_p.worked(1);
    for (DRepresentation diagram : diagrams) {
      StringBuffer buffer = new StringBuffer();
      buffer.append(NLS.bind(IntegrityChecker_LogDiagram, EObjectExt.getText(diagram),
          diagram.eResource().getURI().lastSegment()));

      Collection<Resource> resources = new HashSet<Resource>();
      for (EObject objectUsed : objectsUses.get(diagram)) {
        resources.add(objectUsed.eResource());
      }

      for (Resource resource : resources) {
        buffer.append(NLS.bind(IntegrityChecker_LogResource, resource.getURI().lastSegment()));

        for (EObject object : objectsUses.get(diagram)) {
          if (object.eResource().equals(resource)) {
            String uriFragment = EcoreUtil.getID(object);
            buffer.append(IntegrityChecker_Tabulation);
            buffer
                .append(NLS.bind(IntegrityChecker_LogElement, EObjectLabelProviderHelper.getText(object), uriFragment));
          }
        }
      }
      statuses.add(new Status(AbstractCommandLine.FATAL, FrameworkUtil.getBundle(this.getClass()).getSymbolicName(), buffer.toString()));
    }

    submonitor.worked(1);
    submonitor.done();
    monitor_p.done();
    if (statuses.size() > 0) {
      return new MultiStatus(FrameworkUtil.getBundle(this.getClass()).getSymbolicName(), IStatus.ERROR, statuses.toArray(new IStatus[0]),
          IntegrityChecker_FailedDuplicateElements, null);
    }

    return new Status(IStatus.OK, FrameworkUtil.getBundle(this.getClass()).getSymbolicName(), IntegrityChecker_OK);
  }
}
