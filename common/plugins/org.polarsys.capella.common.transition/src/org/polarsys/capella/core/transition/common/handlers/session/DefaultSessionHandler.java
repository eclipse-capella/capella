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

package org.polarsys.capella.core.transition.common.handlers.session;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.resource.AirdResource;
import org.eclipse.sirius.viewpoint.DAnalysis;

import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class DefaultSessionHandler implements ISessionHandler {

  /**
   * {@inheritDoc}
   */
  public IStatus init(IContext context) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public IStatus dispose(IContext context) {
    return Status.OK_STATUS;
  }

  public EObject getEObjectFromId(String id, IContext context) {

    EObject element = null;

    if (id != null) {

      Iterator<Resource> resources = getRelatedResources(context).iterator();
      while (resources.hasNext() && (element == null)) {
        Resource resource = resources.next();
        element = resource.getEObject(id);
      }
    }

    return element;
  }

  @SuppressWarnings("unchecked")
  protected Collection<Resource> getRelatedResources(IContext context) {
    EObject root = ((EObject) context.get(ITransitionConstants.TRANSITION_SOURCE_ROOT));

    //if session is opened, return all defined resources from session
    if (root != null) {
      Collection<Resource> resources = new HashSet<Resource>();
      Session session = SessionManager.INSTANCE.getSession(root);

      if (session != null) {
        Collection<Resource> sessionResources = new HashSet<Resource>();
        if (session.getSemanticResources() != null) {
          sessionResources.addAll(session.getSemanticResources());
        }
        if (session.getReferencedSessionResources() != null) {
          sessionResources.addAll(session.getReferencedSessionResources());
        }

        for (Resource resource : sessionResources) {
          if (!(resource instanceof AirdResource)) {
            resources.add(resource);

          } else {
            for (EObject rootAird : ((AirdResource) resource).getContents()) {
              if (rootAird instanceof DAnalysis) {
                DAnalysis analysis = (DAnalysis) rootAird;
                if (analysis.getModels() != null) {
                  for (EObject model : analysis.getModels()) {
                    if ((model != null) && !(model.eIsProxy())) {
                      if ((model.eResource() != null)) {
                        resources.add(model.eResource());
                      }
                    }
                  }
                }
              }
            }
          }
        }
        return resources;
      }
    }
    if (root != null) {
      return Collections.singletonList(root.eResource());
    }

    //otherwise
    return Collections.EMPTY_LIST;
  }

  /**
   * {@inheritDoc}
   */
  public String getId(EObject element, IContext context) {
    if (element == null) {
      return null;
    }
    EAttribute attribute = element.eClass().getEIDAttribute();
    if (attribute != null) {
      String id = (String) element.eGet(attribute);
      if (id != null) {
        return id;
      }
    }
    if (element.eResource() != null) {
      return element.eResource().getURIFragment(element);
    }
    return EcoreUtil.getID(element);
  }
}
