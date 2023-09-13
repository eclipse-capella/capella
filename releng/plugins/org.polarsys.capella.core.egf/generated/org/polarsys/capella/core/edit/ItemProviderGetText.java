//Generated with EGF 1.6.4.202309111303
package org.polarsys.capella.core.edit;

import org.eclipse.egf.common.helper.*;
import java.util.*;
import org.eclipse.emf.ecore.*;
import org.eclipse.egf.model.pattern.*;
import org.eclipse.egf.pattern.execution.*;
import org.eclipse.egf.pattern.query.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;

public class ItemProviderGetText
    extends org.polarsys.kitalpha.emde.egf.edit.call.ItemProvider.ItemProvidergetTextoverride {
  protected static String nl;

  public static synchronized ItemProviderGetText create(String lineSeparator) {
    nl = lineSeparator;
    ItemProviderGetText result = new ItemProviderGetText();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t String[] result = new String[] { null };" + NL;
  protected final String TEXT_2 = NL + "\t\t";
  protected final String TEXT_3 = "<?, ?>";
  protected final String TEXT_4 = " ";
  protected final String TEXT_5 = " = (";
  protected final String TEXT_6 = ")object;";
  protected final String TEXT_7 = NL + "\t\tresult[0] = \"\" + ";
  protected final String TEXT_8 = ".getKey() + \" -> \" + ";
  protected final String TEXT_9 = ".getValue();";
  protected final String TEXT_10 = NL + "\t\tString key = crop(\"\" + ";
  protected final String TEXT_11 = ".getKey());";
  protected final String TEXT_12 = NL + "\t\tString key = \"\" + ";
  protected final String TEXT_13 = ".getKey();";
  protected final String TEXT_14 = NL + "\t\tString value = crop(\"\" + ";
  protected final String TEXT_15 = ".getValue());";
  protected final String TEXT_16 = NL + "\t\tString value = \"\" + ";
  protected final String TEXT_17 = NL + "\t\tresult[0] = key + \" -> \" + value;";
  protected final String TEXT_18 = ")object;" + NL + "\t\t//begin-capella-code" + NL
      + "\t\tresult[0] = \"[\" + etString(\"_UI_";
  protected final String TEXT_19 = "_type\")  + \"]\" + \" \" + ";
  protected final String TEXT_20 = ".";
  protected final String TEXT_21 = "();";
  protected final String TEXT_22 = NL + "\t\t//end-capella-code";
  protected final String TEXT_23 = NL + "\t\tString label = crop(((";
  protected final String TEXT_24 = ")object).";
  protected final String TEXT_25 = "());";
  protected final String TEXT_26 = NL + "    \t//begin-capella-code";
  protected final String TEXT_27 = NL + "\t\tString label = ((";
  protected final String TEXT_28 = NL + "        String label = \"\";";
  protected final String TEXT_29 = NL + "        String targetName = \"\";";
  protected final String TEXT_30 = NL + "        ";
  protected final String TEXT_31 = " target = null;" + NL;
  protected final String TEXT_32 = NL + " \t\ttarget = ((";
  protected final String TEXT_33 = ") object).getTargetElement();" + NL + "\t";
  protected final String TEXT_34 = ") object).getTarget();" + NL + "\t";
  protected final String TEXT_35 = ") object).getInvolved();" + NL + "\t";
  protected final String TEXT_36 = ") object).getSuper();" + NL + "\t";
  protected final String TEXT_37 = ") object).getUsedInterface();" + NL + "\t";
  protected final String TEXT_38 = ") object).getImplementedInterface();" + NL + "\t";
  protected final String TEXT_39 = ") object).getCapability();" + NL + "\t";
  protected final String TEXT_40 = ") object).getDeployedElement();" + NL + "\t";
  protected final String TEXT_41 = NL + "\t \tif (null != target) {" + NL + "\t\t\tif (target instanceof ";
  protected final String TEXT_42 = ") {" + NL + "\t\t\t\ttargetName = ((";
  protected final String TEXT_43 = ") target).getName();" + NL + "\t\t\t}" + NL + "" + NL
      + "\t\t\tif (null == targetName || \"\" == targetName) {";
  protected final String TEXT_44 = NL + "\t\t\t\ttargetName = \"[\" + target.eClass().getName() + \"]\";";
  protected final String TEXT_45 = NL + "\t\t\t}" + NL + "\t \t}" + NL + "\t \tlabel = \"[\" + getString(\"_UI_";
  protected final String TEXT_46 = "_type\") + \"] to \" + targetName;";
  protected final String TEXT_47 = NL + "\t\t//end-capella-code" + NL + "\t  ";
  protected final String TEXT_48 = NL + "\t";
  protected final String TEXT_49 = NL + "\t  ";
  protected final String TEXT_50 = " labelValue = ((";
  protected final String TEXT_51 = ")object).eGet(";
  protected final String TEXT_52 = ");" + NL + "\t  ";
  protected final String TEXT_53 = "();" + NL + "\t  ";
  protected final String TEXT_54 = NL + "\t\tString label = labelValue == null ? null : labelValue.toString();" + NL
      + "\t";
  protected final String TEXT_55 = NL + "\t\t\tresult[0] = label == null || label.length() == 0 ?" + NL
      + "\t\t\t//begin-capella-code" + NL + "\t\t\t\"[\" + getString(\"_UI_";
  protected final String TEXT_56 = "_type\") + \"]\" : label;";
  protected final String TEXT_57 = NL + "\t\t\t//end-capella-code";
  protected final String TEXT_58 = NL + "\t\t//begin-capella-code" + NL + "\t\tresult[0] = \"[\" + getString(\"_UI_";
  protected final String TEXT_59 = "_type\") + \"]\";";
  protected final String TEXT_60 = NL + NL + "\t\treturn result[0];" + NL;
  protected final String TEXT_61 = NL;

  public ItemProviderGetText() {
    //Here is the constructor
    StringBuffer stringBuffer = new StringBuffer();

    // add initialisation of the pattern variables (declaration has been already done).

  }

  public String generate(Object argument) throws Exception {
    final StringBuffer stringBuffer = new StringBuffer();

    InternalPatternContext ctx = (InternalPatternContext) argument;
    Map<String, String> queryCtx = null;
    IQuery.ParameterDescription paramDesc = null;
    Node.Container currentNode = ctx.getNode();

    List<Object> genClassList = null;
    //this pattern can only be called by another (i.e. it's not an entry point in execution)
    List<Object> genPackageList = null;
    //this pattern can only be called by another (i.e. it's not an entry point in execution)
    List<Object> genModelList = null;
    //this pattern can only be called by another (i.e. it's not an entry point in execution)
    List<Object> isJDK50List = null;
    //this pattern can only be called by another (i.e. it's not an entry point in execution)
    List<Object> forceDefaultCaseList = null;
    //this pattern can only be called by another (i.e. it's not an entry point in execution)
    List<Object> indentDefaultCaseList = null;
    //this pattern can only be called by another (i.e. it's not an entry point in execution)
    List<Object> _ListList = null;
    //this pattern can only be called by another (i.e. it's not an entry point in execution)

    for (Object genClassParameter : genClassList) {
      for (Object genPackageParameter : genPackageList) {
        for (Object genModelParameter : genModelList) {
          for (Object isJDK50Parameter : isJDK50List) {
            for (Object forceDefaultCaseParameter : forceDefaultCaseList) {
              for (Object indentDefaultCaseParameter : indentDefaultCaseList) {
                for (Object _ListParameter : _ListList) {

                  this.genClass = (org.eclipse.emf.codegen.ecore.genmodel.GenClass) genClassParameter;
                  this.genPackage = (org.eclipse.emf.codegen.ecore.genmodel.GenPackage) genPackageParameter;
                  this.genModel = (org.eclipse.emf.codegen.ecore.genmodel.GenModel) genModelParameter;
                  this.isJDK50 = (java.lang.Boolean) isJDK50Parameter;
                  this.forceDefaultCase = (java.lang.Boolean) forceDefaultCaseParameter;
                  this.indentDefaultCase = (java.lang.String) indentDefaultCaseParameter;
                  this._List = (java.lang.String) _ListParameter;

                  if (preCondition(ctx)) {
                    ctx.setNode(new Node.Container(currentNode, getClass()));
                    orchestration(ctx);
                  }

                }
              }
            }
          }
        }
      }
    }
    ctx.setNode(currentNode);
    if (ctx.useReporter()) {
      ctx.getReporter().executionFinished(OutputManager.computeExecutionOutput(ctx), ctx);
    }

    stringBuffer.append(TEXT_61);
    stringBuffer.append(TEXT_61);
    return stringBuffer.toString();
  }

  public String orchestration(PatternContext ctx) throws Exception {
    InternalPatternContext ictx = (InternalPatternContext) ctx;

    super.orchestration(new SuperOrchestrationContext(ictx));

    if (ictx.useReporter()) {
      Map<String, Object> parameterValues = new HashMap<String, Object>();
      parameterValues.put("genClass", this.genClass);
      parameterValues.put("genPackage", this.genPackage);
      parameterValues.put("genModel", this.genModel);
      parameterValues.put("isJDK50", this.isJDK50);
      parameterValues.put("forceDefaultCase", this.forceDefaultCase);
      parameterValues.put("indentDefaultCase", this.indentDefaultCase);
      parameterValues.put("_List", this._List);
      String outputWithCallBack = OutputManager.computeLoopOutput(ictx);
      String loop = OutputManager.computeLoopOutputWithoutCallback(ictx);
      ictx.getReporter().loopFinished(loop, outputWithCallBack, ictx, parameterValues);
    }
    return null;
  }

  public Map<String, Object> getParameters() {
    final Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put("genClass", this.genClass);
    parameters.put("genPackage", this.genPackage);
    parameters.put("genModel", this.genModel);
    parameters.put("isJDK50", this.isJDK50);
    parameters.put("forceDefaultCase", this.forceDefaultCase);
    parameters.put("indentDefaultCase", this.indentDefaultCase);
    parameters.put("_List", this._List);
    return parameters;
  }

  protected void method_doGenerate(final StringBuffer stringBuffer, final PatternContext ctx) throws Exception {

    stringBuffer.append(TEXT_1);
    if (genClass.isMapEntry()) {
      stringBuffer.append(TEXT_2);
      stringBuffer.append(genClass.getImportedInterfaceName());
      if (genModel.useGenerics()) {
        stringBuffer.append(TEXT_3);
      }
      stringBuffer.append(TEXT_4);
      stringBuffer.append(genClass.getSafeUncapName());
      stringBuffer.append(TEXT_5);
      stringBuffer.append(genClass.getImportedInterfaceName());
      if (genModel.useGenerics()) {
        stringBuffer.append(TEXT_3);
      }
      stringBuffer.append(TEXT_6);
      if (!genClass.getMapEntryKeyFeature().isPropertyMultiLine()
          && !genClass.getMapEntryValueFeature().isPropertyMultiLine()) {
        stringBuffer.append(TEXT_7);
        stringBuffer.append(genClass.getSafeUncapName());
        stringBuffer.append(TEXT_8);
        stringBuffer.append(genClass.getSafeUncapName());
        stringBuffer.append(TEXT_9);
        stringBuffer.append(genModel.getNonNLS());
        stringBuffer.append(genModel.getNonNLS(2));
      } else {
        if (genClass.getMapEntryKeyFeature().isPropertyMultiLine()) {
          stringBuffer.append(TEXT_10);
          stringBuffer.append(genClass.getSafeUncapName());
          stringBuffer.append(TEXT_11);
          stringBuffer.append(genModel.getNonNLS());
        } else {
          stringBuffer.append(TEXT_12);
          stringBuffer.append(genClass.getSafeUncapName());
          stringBuffer.append(TEXT_13);
          stringBuffer.append(genModel.getNonNLS());
        }
        if (genClass.getMapEntryValueFeature().isPropertyMultiLine()) {
          stringBuffer.append(TEXT_14);
          stringBuffer.append(genClass.getSafeUncapName());
          stringBuffer.append(TEXT_15);
          stringBuffer.append(genModel.getNonNLS());
        } else {
          stringBuffer.append(TEXT_16);
          stringBuffer.append(genClass.getSafeUncapName());
          stringBuffer.append(TEXT_9);
          stringBuffer.append(genModel.getNonNLS());
        }
        stringBuffer.append(TEXT_17);
        stringBuffer.append(genModel.getNonNLS());
      }
    } else if (genClass.getLabelFeature() != null) {
      GenFeature labelFeature = genClass.getLabelFeature();
      if (labelFeature.isPrimitiveType() && !labelFeature.getGenClass().isDynamic()
          && !labelFeature.isSuppressedGetVisibility()) {
        stringBuffer.append(TEXT_2);
        stringBuffer.append(genClass.getImportedInterfaceName());
        stringBuffer.append(genClass.getInterfaceWildTypeArguments());
        stringBuffer.append(TEXT_4);
        stringBuffer.append(genClass.getSafeUncapName());
        stringBuffer.append(TEXT_5);
        stringBuffer.append(genClass.getImportedInterfaceName());
        stringBuffer.append(genClass.getInterfaceWildTypeArguments());
        stringBuffer.append(TEXT_18);
        stringBuffer.append(genClass.getName());
        stringBuffer.append(TEXT_19);
        stringBuffer.append(genClass.getSafeUncapName());
        stringBuffer.append(TEXT_20);
        stringBuffer.append(genClass.getLabelFeature().getGetAccessor());
        stringBuffer.append(TEXT_21);
        stringBuffer.append(genModel.getNonNLS());
        stringBuffer.append(genModel.getNonNLS(2));
        stringBuffer.append(TEXT_22);
      } else {
        if (labelFeature.isStringType() && !labelFeature.getGenClass().isDynamic()
            && !labelFeature.isSuppressedGetVisibility()) {
          if (labelFeature.isPropertyMultiLine()) {
            stringBuffer.append(TEXT_23);
            stringBuffer.append(genClass.getImportedInterfaceName());
            stringBuffer.append(genClass.getInterfaceWildTypeArguments());
            stringBuffer.append(TEXT_24);
            stringBuffer.append(labelFeature.getGetAccessor());
            stringBuffer.append(TEXT_25);
          } else {
            stringBuffer.append(TEXT_26);
            if (labelFeature.getName().equals("name")) {
              stringBuffer.append(TEXT_27);
              stringBuffer.append(genClass.getImportedInterfaceName());
              stringBuffer.append(genClass.getInterfaceWildTypeArguments());
              stringBuffer.append(TEXT_24);
              stringBuffer.append(labelFeature.getGetAccessor());
              stringBuffer.append(TEXT_21);
            } else {
              stringBuffer.append(TEXT_28);
              stringBuffer.append(genModel.getNonNLS());
              stringBuffer.append(TEXT_29);
              stringBuffer.append(genModel.getNonNLS());
              stringBuffer.append(TEXT_30);
              stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
              stringBuffer.append(TEXT_31);
              List<GenClass> superclasses = new ArrayList(genClass.getAllBaseGenClasses());
              superclasses.add(genClass);
              for (GenClass cls : superclasses) {
                if ((cls.getEcoreModelElement() instanceof EClass)
                    && ((EClass) cls.getEcoreModelElement()).getName().equals("AbstractTrace")) {
                  stringBuffer.append(TEXT_32);
                  stringBuffer
                      .append(genModel.getImportedName("org.polarsys.capella.common.data.modellingcore.AbstractTrace"));
                  stringBuffer.append(TEXT_33);
                } else if ((cls.getEcoreModelElement() instanceof EClass)
                    && ((EClass) cls.getEcoreModelElement()).getName().equals("StateTransition")) {
                  stringBuffer.append(TEXT_32);
                  stringBuffer
                      .append(genModel.getImportedName("org.polarsys.capella.core.data.capellacommon.StateTransition"));
                  stringBuffer.append(TEXT_34);
                } else if ((cls.getEcoreModelElement() instanceof EClass)
                    && ((EClass) cls.getEcoreModelElement()).getName().equals("Involvement")) {
                  stringBuffer.append(TEXT_32);
                  stringBuffer
                      .append(genModel.getImportedName("org.polarsys.capella.core.data.capellacore.Involvement"));
                  stringBuffer.append(TEXT_35);
                } else if ((cls.getEcoreModelElement() instanceof EClass)
                    && ((EClass) cls.getEcoreModelElement()).getName().equals("Generalization")) {
                  stringBuffer.append(TEXT_32);
                  stringBuffer
                      .append(genModel.getImportedName("org.polarsys.capella.core.data.capellacore.Generalization"));
                  stringBuffer.append(TEXT_36);
                } else if ((cls.getEcoreModelElement() instanceof EClass)
                    && ((EClass) cls.getEcoreModelElement()).getName().equals("AbstractCapabilityGeneralization")) {
                  stringBuffer.append(TEXT_32);
                  stringBuffer.append(genModel
                      .getImportedName("org.polarsys.capella.core.data.interaction.AbstractCapabilityGeneralization"));
                  stringBuffer.append(TEXT_36);
                } else if ((cls.getEcoreModelElement() instanceof EClass)
                    && ((EClass) cls.getEcoreModelElement()).getName().equals("InterfaceUse")) {
                  stringBuffer.append(TEXT_32);
                  stringBuffer.append(genModel.getImportedName("org.polarsys.capella.core.data.cs.InterfaceUse"));
                  stringBuffer.append(TEXT_37);
                } else if ((cls.getEcoreModelElement() instanceof EClass)
                    && ((EClass) cls.getEcoreModelElement()).getName().equals("InterfaceImplementation")) {
                  stringBuffer.append(TEXT_32);
                  stringBuffer
                      .append(genModel.getImportedName("org.polarsys.capella.core.data.cs.InterfaceImplementation"));
                  stringBuffer.append(TEXT_38);
                } else if ((cls.getEcoreModelElement() instanceof EClass)
                    && ((EClass) cls.getEcoreModelElement()).getName().equals("CapabilityExploitation")) {
                  stringBuffer.append(TEXT_32);
                  stringBuffer
                      .append(genModel.getImportedName("org.polarsys.capella.core.data.ctx.CapabilityExploitation"));
                  stringBuffer.append(TEXT_39);
                } else if ((cls.getEcoreModelElement() instanceof EClass)
                    && ((EClass) cls.getEcoreModelElement()).getName().equals("CapabilityExploitation")) {
                  stringBuffer.append(TEXT_32);
                  stringBuffer
                      .append(genModel.getImportedName("org.polarsys.capella.core.data.ctx.CapabilityExploitation"));
                  stringBuffer.append(TEXT_39);
                } else if ((cls.getEcoreModelElement() instanceof EClass)
                    && ((EClass) cls.getEcoreModelElement()).getName().equals("AbstractDeployment")) {
                  stringBuffer.append(TEXT_32);
                  stringBuffer.append(genModel.getImportedName("org.polarsys.capella.core.data.cs.AbstractDeployment"));
                  stringBuffer.append(TEXT_40);
                }
              }
              stringBuffer.append(TEXT_41);
              stringBuffer.append(
                  genModel.getImportedName("org.polarsys.capella.common.data.modellingcore.AbstractNamedElement"));
              stringBuffer.append(TEXT_42);
              stringBuffer.append(
                  genModel.getImportedName("org.polarsys.capella.common.data.modellingcore.AbstractNamedElement"));
              stringBuffer.append(TEXT_43);
              stringBuffer.append(genModel.getNonNLS());
              stringBuffer.append(TEXT_44);
              stringBuffer.append(genModel.getNonNLS());
              stringBuffer.append(genModel.getNonNLS(2));
              stringBuffer.append(TEXT_45);
              stringBuffer.append(genClass.getName());
              stringBuffer.append(TEXT_46);
              stringBuffer.append(genModel.getNonNLS());
              stringBuffer.append(genModel.getNonNLS(2));
              stringBuffer.append(genModel.getNonNLS(3));
              stringBuffer.append(TEXT_2);
            }
            stringBuffer.append(TEXT_47);
          }
          stringBuffer.append(TEXT_48);
        } else {
          stringBuffer.append(TEXT_49);
          if (labelFeature.isSuppressedGetVisibility() || labelFeature.getGenClass().isDynamic()) {
            stringBuffer.append(TEXT_2);
            stringBuffer.append(genModel.getImportedName("java.lang.Object"));
            stringBuffer.append(TEXT_50);
            stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
            stringBuffer.append(TEXT_51);
            stringBuffer.append(labelFeature.getQualifiedFeatureAccessor());
            stringBuffer.append(TEXT_52);
          } else {
            stringBuffer.append(TEXT_2);
            stringBuffer.append(labelFeature.getRawImportedType());
            stringBuffer.append(TEXT_50);
            stringBuffer.append(genClass.getImportedInterfaceName());
            stringBuffer.append(genClass.getInterfaceWildTypeArguments());
            stringBuffer.append(TEXT_24);
            stringBuffer.append(labelFeature.getGetAccessor());
            stringBuffer.append(TEXT_53);
          }
          stringBuffer.append(TEXT_54);
        }
        stringBuffer.append(TEXT_55);
        stringBuffer.append(genClass.getName());
        stringBuffer.append(TEXT_56);
        stringBuffer.append(genModel.getNonNLS());
        stringBuffer.append(genModel.getNonNLS(2));
        stringBuffer.append(genModel.getNonNLS(3));
        stringBuffer.append(TEXT_57);
      }
    } else {
      stringBuffer.append(TEXT_58);
      stringBuffer.append(genClass.getName());
      stringBuffer.append(TEXT_59);
      stringBuffer.append(genModel.getNonNLS());
      stringBuffer.append(genModel.getNonNLS(2));
      stringBuffer.append(genModel.getNonNLS(3));
      stringBuffer.append(TEXT_22);
    }
    stringBuffer.append(TEXT_60);
    InternalPatternContext ictx = (InternalPatternContext) ctx;
    new Node.DataLeaf(ictx.getNode(), getClass(), "doGenerate", stringBuffer.toString());
  }
}