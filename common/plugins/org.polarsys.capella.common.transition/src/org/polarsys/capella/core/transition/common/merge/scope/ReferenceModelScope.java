/*******************************************************************************
 * Copyright (c) 2006, 2022 THALES GLOBAL SERVICES.
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

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.diffmerge.sirius.SiriusImageHelper;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * A model scope covering reference model
 */
public class ReferenceModelScope extends ContextModelScope {

    /**
     * This helper contains the required adaptations of the get, add and remove methods to handle image file paths
     * renaming
     */
    private SiriusImageHelper siriusImageHelper;

    public ReferenceModelScope(Collection<? extends EObject> elements, IContext context) {
        super(elements, context);
        initializeSiriusImageHelper(context, true);
    }

    /**
     * Handles the initialization of the adaptation for image file paths renaming.
     * 
     * @param context
     *            The context of the transition
     * @param active
     *            Whether or not the code adaptation is active
     */
    protected void initializeSiriusImageHelper(IContext context, boolean active) {
        Resource scopeResource = (Resource) context.get(ITransitionConstants.TRANSITION_SOURCE_RESOURCE);
        siriusImageHelper = new TransitionSiriusImageHelper(context, scopeResource, active);
    }

    @Override
    protected Object getDefaultOriginator() {
        return "Candidate model";
    }

    /**
     * @see org.eclipse.emf.diffmerge.impl.scopes.AbstractEditableModelScope#get(org.eclipse.emf.ecore.EObject,
     *      org.eclipse.emf.ecore.EAttribute)
     */
    @Override
    public List<Object> get(EObject source, EAttribute attribute) {
        List<Object> objects = super.get(source, attribute);
        objects = siriusImageHelper.adaptGetValue(source, attribute, objects);
        return objects;
    }

    /**
     * @see org.eclipse.emf.diffmerge.impl.scopes.AbstractEditableModelScope#add(org.eclipse.emf.ecore.EObject,
     *      org.eclipse.emf.ecore.EAttribute, java.lang.Object)
     */
    @Override
    public boolean add(EObject source, EAttribute attribute, Object value) {
        Object newValue = siriusImageHelper.adaptAddValue(source, attribute, value);
        return super.add(source, attribute, newValue);
    }

    /**
     * @see org.eclipse.emf.diffmerge.impl.scopes.AbstractEditableModelScope#remove(org.eclipse.emf.ecore.EObject,
     *      org.eclipse.emf.ecore.EAttribute, java.lang.Object)
     */
    @Override
    public boolean remove(EObject source, EAttribute attribute, Object value) {
        Object newValue = siriusImageHelper.adaptRemoveValue(source, attribute, value);
        return super.remove(source, attribute, newValue);
    }

}
