/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
/* Copyright 2013. Thales. */
package org.polarsys.capella.core.model.semantic.internal;

import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.polarsys.capella.core.model.semantic.SemanticModelUtil;

/**
 * A content tree iterator that skips all non-navigable containment references.
 * 
 * <p>
 * <strong>EXPERIMENTAL</strong>. This class or interface has been added as
 * part of a work in progress. There is no guarantee that this API will
 * work or that it will remain the same. Please do not use this API without
 * consulting with the Capella team.
 * </p>
 * @since 3.0
 */
public class SemanticContentTreeIterator<E> extends EcoreUtil.ContentTreeIterator<E> {

	private static final long serialVersionUID = 1L;
	
	private final SemanticModelUtil _semanticModelUtil;
	
	public SemanticContentTreeIterator(SemanticModelUtil semanticModelUtil, Collection<?> emfObjects){
		super(emfObjects);
		_semanticModelUtil = semanticModelUtil;
	}
	
	public SemanticContentTreeIterator(SemanticModelUtil semanticModelUtil_p, Object object_p, boolean isResolveProxies_p, Logger logger_p){
		super(object_p, isResolveProxies_p);
		_semanticModelUtil = semanticModelUtil_p;
	}
	
	/**
     * Returns an iterator over the contents of eObject, skipping all non-navigable children.
     * @param eObject the parent object.
     * @return the children iterator.
     */
    @Override
    protected Iterator<? extends EObject> getEObjectChildren(EObject eObject) {
    	return _semanticModelUtil.eContents(eObject).iterator();
    }
	
}
