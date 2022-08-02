/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.common.merge.scope;

import java.util.List;

import org.eclipse.emf.diffmerge.sirius.SiriusImageHelper;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * An adaptation of the original SiriusImageHelper to retrieve element project name based on a provided resource
 */
public class TransitionSiriusImageHelper extends SiriusImageHelper {

    protected IContext context;

    private boolean active = true;

    private Resource scopeResource;

    /**
     * The constructor for the adaptation of the original SiriusImageHelper
     * 
     * @param context
     *            The context of the transition
     * @param scopeResource
     *            The resource used to retrieve the project name
     */
    public TransitionSiriusImageHelper(IContext context, Resource scopeResource) {
        this.context = context;
        this.scopeResource = scopeResource;
    }


    /**
     * The constructor allowing to override the activation of the code adaptation
     * @param context The context of the transition 
     * @param scopeResource The resource used to retrieve the project name
     * @param active Whether or not the adaptation is active
     */
    public TransitionSiriusImageHelper(IContext context, Resource scopeResource, boolean active) {
        this.context = context;
        this.scopeResource = scopeResource;
        this.active = active;
    }

    @Override
    public List<Object> adaptGetValue(EObject source, EAttribute attribute, List<Object> objects) {
        if (!active) {
            return objects;
        }
        return super.adaptGetValue(source, attribute, objects);
    }

    @Override
    public Object adaptAddValue(EObject source, EAttribute attribute, Object value) {
        if (!active) {
            return value;
        }
        return super.adaptAddValue(source, attribute, value);
    }

    @Override
    public Object adaptRemoveValue(EObject source, EAttribute attribute, Object value) {
        if (!active) {
            return value;
        }
        return super.adaptRemoveValue(source, attribute, value);
    }

    @Override
    protected String getMainProjectName(EObject source) {
        return getProjectFromUri(scopeResource.getURI(), scopeResource.getResourceSet().getURIConverter());
    }

}
