/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.mdsofa.common.descriptor.pattern.helper;

import org.polarsys.capella.common.mdsofa.common.descriptor.IDescriptor;

/**
 * Pattern descriptor handler while parsing using the pattern descriptor strategy.
 */
public interface IPatternDescriptorHandler {
  /**
   * Handle a library descriptor.
   * @param descriptor_p
   * @param parentLibraryId_p The full id of the library.
   */
  public void handleLibraryDescriptor(IDescriptor descriptor_p, String parentLibraryId_p);

  /**
   * Handle a pattern descriptor.
   * @param descriptor_p
   * @param patternFullId_p The pattern full id.
   */
  public void handlePatternDescriptor(IDescriptor descriptor_p, String patternFullId_p);

  /**
   * Handle a descriptor that is neither a library nor a pattern one.
   * @param descriptor_p
   */
  public void handleDescriptor(IDescriptor descriptor_p);
}
