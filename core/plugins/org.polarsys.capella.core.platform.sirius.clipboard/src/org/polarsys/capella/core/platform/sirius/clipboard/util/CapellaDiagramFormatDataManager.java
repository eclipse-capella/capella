/*******************************************************************************
 * Copyright (c) 2016, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.clipboard.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.diagram.formatdata.AbstractFormatData;
import org.eclipse.sirius.diagram.ui.tools.api.format.AbstractSiriusFormatDataManager;
import org.eclipse.sirius.diagram.ui.tools.api.format.FormatDataKey;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;

/**
 * FormatDataManager used in Capella Copy/Paste operations to paste layouts or/and styles.
 */
public class CapellaDiagramFormatDataManager extends AbstractSiriusFormatDataManager {

	/**
	 * FormatKey using the EMF identifier of the given object.
	 */
  public static class CapellaDiagramFormatKey implements FormatDataKey {
		/*
		 * EMF identifier
		 */
	  private final String keyId;
		
		public CapellaDiagramFormatKey(DSemanticDecorator originalDSemanticDecorator) {
			keyId = EcoreUtil.getURI(originalDSemanticDecorator).fragment();
		}
		
		@Override
		public String getId() {
			return keyId;
		}
		
		@Override
		public int hashCode() {
		  return Objects.hashCode(keyId);
		}
		
		@Override
		public boolean equals(Object anObject) {
      if (this == anObject) {
        return true;
      }
      if (anObject instanceof CapellaDiagramFormatKey) {
        CapellaDiagramFormatKey anotherKey = (CapellaDiagramFormatKey)anObject;
        return Objects.equals(keyId, anotherKey.keyId);
      }
      return false;
		}
	}
	
	/*
	 * Storage map 
	 */
	private final Map<CapellaDiagramFormatKey, AbstractFormatData> keyToFormatData;
	
	/*
	 * Pasted to copied element Map : used to get FormatDatas of copied objects to apply them on pasted objects.
	 */
	private Map<? extends DSemanticDecorator, ? extends DSemanticDecorator> pastedToCopiedElement;
	
	public CapellaDiagramFormatDataManager() {
		keyToFormatData = new HashMap<CapellaDiagramFormatKey, AbstractFormatData>();
	}
	
	
	@Override
	public AbstractFormatData getFormatData(FormatDataKey key, RepresentationElementMapping mapping) {
		if (key instanceof CapellaDiagramFormatKey) {
			return keyToFormatData.get(key);
		}
		return null;
	}



    @Override
	public void addFormatData(FormatDataKey key, RepresentationElementMapping mapping, AbstractFormatData formatData) {
		if (key instanceof CapellaDiagramFormatKey) {
			keyToFormatData.put((CapellaDiagramFormatKey) key, formatData);
		}
	}
	
	/**
	 * Generate the key: pasted objects have the same key as their originally copied counterparts (so we can get FormatData of the originally copied elements).
	 */
	@Override
	public FormatDataKey createKey(DSemanticDecorator semanticDecorator) {
	  // Is it a pasted element ?
	  if (pastedToCopiedElement != null) {
	    // Retrieve originally copied element
	    DSemanticDecorator copiedElement = pastedToCopiedElement.get(semanticDecorator);
	    if (copiedElement != null) {
	      return new CapellaDiagramFormatKey(copiedElement);
	    }
	  }
	  return new CapellaDiagramFormatKey(semanticDecorator);
	}

	@Override
	public boolean containsData() {
		return !keyToFormatData.isEmpty();
	}


  @Override
  public void clearFormatData() {
    keyToFormatData.clear();
    pastedToCopiedElement = null;
  }

  public void setPastedToCopiedElement(Map<? extends DSemanticDecorator, ? extends DSemanticDecorator> pastedToCopiedElement) {
    this.pastedToCopiedElement = pastedToCopiedElement;
  }
}
