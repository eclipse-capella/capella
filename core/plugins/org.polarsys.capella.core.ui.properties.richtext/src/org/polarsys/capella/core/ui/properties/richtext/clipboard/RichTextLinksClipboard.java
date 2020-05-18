/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.properties.richtext.clipboard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.kitalpha.emde.model.Element;
import org.polarsys.kitalpha.richtext.widget.tools.manager.LinkManager;
import org.polarsys.kitalpha.richtext.widget.tools.manager.LinkManagerImpl;
import org.polarsys.kitalpha.richtext.widget.tools.utils.Constants;

/**
 * A virtual Clipboard containing element references that were copied by the user, in order to be pasted as links in the
 * description section.
 * 
 */
public class RichTextLinksClipboard {

  private static RichTextLinksClipboard instance;
  private LinkManager linkManager;

  private Collection<EObject> copiedElements;

  private RichTextLinksClipboard() {
    copiedElements = new ArrayList<>();
    linkManager = new LinkManagerImpl(null);
  }

  public static RichTextLinksClipboard getInstance() {
    if (null == instance) {
      instance = new RichTextLinksClipboard();
    }
    return instance;
  }

  public void addCopiedElements(Collection<EObject> elements) {
    copiedElements.addAll(elements);
  }

  public Collection<EObject> getCopiedElements() {
    return copiedElements;
  }

  public void clearCopiedElements() {
    copiedElements.clear();
  }

  public String getCopiedElementsLinksHtml() {
    return copiedElements.stream().map(this::getElementLink).collect(Collectors.joining(" "));
  }

  /**
   * Extracts a custom html link that can be inserted in the Description section.
   * 
   * @param element
   *          the element
   * @return a custom html link that can be inserted in the Description section.
   */
  private String getElementLink(EObject element) {

    Optional<String> linkType = Optional.empty();

    if (element instanceof Element) {      
      linkType = Optional.of(Constants.MODEL_ELEMENT_LABEL);
      
    } else if (element instanceof DRepresentationDescriptor) {
      linkType = Optional.of(Constants.DIAGRAM_ELEMENT_LABEL);
      
    }

    if (linkType.isPresent()) {
      String type = linkType.get();
      String id = IdManager.getInstance().getId(element);
      String label = EObjectLabelProviderHelper.getText(element);

      return linkManager.encode(type, id, label);
    }

    return "";
  }

}
