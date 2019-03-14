/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.model.helpers;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.SequenceLink;
import org.polarsys.capella.core.data.fa.SequenceLinkEnd;

public class SequenceLinkEndExt {

  private SequenceLinkEndExt() {
    
  }

  /**
   * Return all Sequence Links that have the given sequenceLinkEnd as target
   * @param sequenceLinkEnd
   * @return
   */
  public static List<SequenceLink> getIncomingSequenceLinks(SequenceLinkEnd sequenceLinkEnd) {
    List<EObject> eObjects = EObjectExt.getReferencers(sequenceLinkEnd, FaPackage.Literals.SEQUENCE_LINK__TARGET);
    return eObjects.stream().filter(SequenceLink.class::isInstance).map(SequenceLink.class::cast).collect(Collectors.toList());
  }
  
  /**
   * Return all Sequence Links that have the given sequenceLinkEnd as source
   * @param sequenceLinkEnd
   * @return
   */
  public static List<SequenceLink> getOutgoingSequenceLinks(SequenceLinkEnd sequenceLinkEnd) {
    List<EObject> eObjects = EObjectExt.getReferencers(sequenceLinkEnd, FaPackage.Literals.SEQUENCE_LINK__SOURCE);
    return eObjects.stream().filter(SequenceLink.class::isInstance).map(SequenceLink.class::cast).collect(Collectors.toList());
  }
}
