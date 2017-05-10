//Generated with EGF 1.4.1.v20161010-1511
package org.polarsys.capella.core.edit;

import org.eclipse.egf.common.helper.*;
import java.util.*;
import org.eclipse.emf.ecore.*;
import org.eclipse.egf.model.pattern.*;
import org.eclipse.egf.pattern.execution.*;
import org.eclipse.egf.pattern.query.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;

public class ItemProvidergetTextoverride
    extends org.polarsys.kitalpha.emde.egf.edit.call.ItemProvider.ItemProvidergetTextoverride {
  protected static String nl;

  public static synchronized ItemProvidergetTextoverride create(String lineSeparator) {
    nl = lineSeparator;
    ItemProvidergetTextoverride result = new ItemProvidergetTextoverride();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t String[] result = new String[] { null };" + NL;
  protected final String TEXT_2 = NL + "\t\t";
  protected final String TEXT_3 = "<?, ?>";
  protected final String TEXT_4 = " ";
  protected final String TEXT_5 = " = (";
  protected final String TEXT_6 = "<?, ?>";
  protected final String TEXT_7 = ")object;";
  protected final String TEXT_8 = NL + "\t\tresult[0] = \"\" + ";
  protected final String TEXT_9 = ".getKey() + \" -> \" + ";
  protected final String TEXT_10 = ".getValue();";
  protected final String TEXT_11 = NL + "\t\tString key = crop(\"\" + ";
  protected final String TEXT_12 = ".getKey());";
  protected final String TEXT_13 = NL + "\t\tString key = \"\" + ";
  protected final String TEXT_14 = ".getKey();";
  protected final String TEXT_15 = NL + "\t\tString value = crop(\"\" + ";
  protected final String TEXT_16 = ".getValue());";
  protected final String TEXT_17 = NL + "\t\tString value = \"\" + ";
  protected final String TEXT_18 = ".getValue();";
  protected final String TEXT_19 = NL + "\t\tresult[0] = key + \" -> \" + value;";
  protected final String TEXT_20 = NL + "\t\t";
  protected final String TEXT_21 = " ";
  protected final String TEXT_22 = " = (";
  protected final String TEXT_23 = ")object;" + NL + "\t\t//begin-capella-code" + NL
      + "\t\tresult[0] = \"[\" + etString(\"_UI_";
  protected final String TEXT_24 = "_type\")  + \"]\" + \" \" + ";
  protected final String TEXT_25 = ".";
  protected final String TEXT_26 = "();";
  protected final String TEXT_27 = NL + "\t\t//end-capella-code";
  protected final String TEXT_28 = NL + "\t\tString label = crop(((";
  protected final String TEXT_29 = ")object).";
  protected final String TEXT_30 = "());";
  protected final String TEXT_31 = NL + "    \t//begin-capella-code";
  protected final String TEXT_32 = NL + "\t\tString label = ((";
  protected final String TEXT_33 = ")object).";
  protected final String TEXT_34 = "();";
  protected final String TEXT_35 = NL + "        String label = \"\";";
  protected final String TEXT_36 = NL + "        String targetName = \"\";";
  protected final String TEXT_37 = NL + "        ";
  protected final String TEXT_38 = " target = null;" + NL;
  protected final String TEXT_39 = NL + " \t\ttarget = ((";
  protected final String TEXT_40 = ") object).getTargetElement();" + NL + "\t";
  protected final String TEXT_41 = NL + " \t\ttarget = ((";
  protected final String TEXT_42 = ") object).getTarget();" + NL + "\t";
  protected final String TEXT_43 = NL + " \t\ttarget = ((";
  protected final String TEXT_44 = ") object).getInvolved();" + NL + "\t";
  protected final String TEXT_45 = NL + " \t\ttarget = ((";
  protected final String TEXT_46 = ") object).getSuper();" + NL + "\t";
  protected final String TEXT_47 = NL + " \t\ttarget = ((";
  protected final String TEXT_48 = ") object).getSuper();" + NL + "\t";
  protected final String TEXT_49 = NL + " \t\ttarget = ((";
  protected final String TEXT_50 = ") object).getUsedInterface();" + NL + "\t";
  protected final String TEXT_51 = NL + " \t\ttarget = ((";
  protected final String TEXT_52 = ") object).getImplementedInterface();" + NL + "\t";
  protected final String TEXT_53 = NL + " \t\ttarget = ((";
  protected final String TEXT_54 = ") object).getCapability();" + NL + "\t";
  protected final String TEXT_55 = NL + " \t\ttarget = ((";
  protected final String TEXT_56 = ") object).getCapability();" + NL + "\t";
  protected final String TEXT_57 = NL + " \t\ttarget = ((";
  protected final String TEXT_58 = ") object).getDeployedElement();" + NL + "\t";
  protected final String TEXT_59 = NL + "\t \tif (null != target) {" + NL + "\t\t\tif (target instanceof ";
  protected final String TEXT_60 = ") {" + NL + "\t\t\t\ttargetName = ((";
  protected final String TEXT_61 = ") target).getName();" + NL + "\t\t\t}" + NL + "" + NL
      + "\t\t\tif (null == targetName || \"\" == targetName) {";
  protected final String TEXT_62 = NL + "\t\t\t\ttargetName = \"[\" + target.eClass().getName() + \"]\";";
  protected final String TEXT_63 = NL + "\t\t\t}" + NL + "\t \t}" + NL + "\t \tlabel = \"[\" + getString(\"_UI_";
  protected final String TEXT_64 = "_type\") + \"] to \" + targetName;";
  protected final String TEXT_65 = NL + "\t\t";
  protected final String TEXT_66 = NL + "\t\t//end-capella-code" + NL + "\t  ";
  protected final String TEXT_67 = NL + "\t";
  protected final String TEXT_68 = NL + "\t  ";
  protected final String TEXT_69 = NL + "\t\t";
  protected final String TEXT_70 = " labelValue = ((";
  protected final String TEXT_71 = ")object).eGet(";
  protected final String TEXT_72 = ");" + NL + "\t  ";
  protected final String TEXT_73 = NL + "\t\t";
  protected final String TEXT_74 = " labelValue = ((";
  protected final String TEXT_75 = ")object).";
  protected final String TEXT_76 = "();" + NL + "\t  ";
  protected final String TEXT_77 = NL + "\t\tString label = labelValue == null ? null : labelValue.toString();" + NL
      + "\t";
  protected final String TEXT_78 = NL + "\t\t\tresult[0] = label == null || label.length() == 0 ?" + NL
      + "\t\t\t//begin-capella-code" + NL + "\t\t\t\"[\" + getString(\"_UI_";
  protected final String TEXT_79 = "_type\") + \"]\" : label;";
  protected final String TEXT_80 = NL + "\t\t\t//end-capella-code";
  protected final String TEXT_81 = NL + "\t\t//begin-capella-code" + NL + "\t\tresult[0] = \"[\" + getString(\"_UI_";
  protected final String TEXT_82 = "_type\") + \"]\";";
  protected final String TEXT_83 = NL + "\t\t//end-capella-code";
  protected final String TEXT_84 = NL + NL + "\t\treturn result[0];" + NL;
  protected final String TEXT_85 = NL;
  protected final String TEXT_86 = NL;

  public ItemProvidergetTextoverride() {
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
    List<Object> _ListList = null;
    //this pattern can only be called by another (i.e. it's not an entry point in execution)

    for (Object genClassParameter : genClassList) {
      for (Object genPackageParameter : genPackageList) {
        for (Object genModelParameter : genModelList) {
          for (Object _ListParameter : _ListList) {

            this.genClass = (org.eclipse.emf.codegen.ecore.genmodel.GenClass) genClassParameter;
            this.genPackage = (org.eclipse.emf.codegen.ecore.genmodel.GenPackage) genPackageParameter;
            this.genModel = (org.eclipse.emf.codegen.ecore.genmodel.GenModel) genModelParameter;
            this._List = (java.lang.String) _ListParameter;

            if (preCondition(ctx)) {
              ctx.setNode(new Node.Container(currentNode, getClass()));
              orchestration(ctx);
            }

          }
        }
      }
    }
    ctx.setNode(currentNode);
    if (ctx.useReporter()) {
      ctx.getReporter().executionFinished(OutputManager.computeExecutionOutput(ctx), ctx);
    }

    stringBuffer.append(TEXT_85);
    stringBuffer.append(TEXT_86);
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
        stringBuffer.append(TEXT_6);
      }
      stringBuffer.append(TEXT_7);
      if (!genClass.getMapEntryKeyFeature().isPropertyMultiLine()
          && !genClass.getMapEntryValueFeature().isPropertyMultiLine()) {
        stringBuffer.append(TEXT_8);
        stringBuffer.append(genClass.getSafeUncapName());
        stringBuffer.append(TEXT_9);
        stringBuffer.append(genClass.getSafeUncapName());
        stringBuffer.append(TEXT_10);
        stringBuffer.append(genModel.getNonNLS());
        stringBuffer.append(genModel.getNonNLS(2));
      } else {
        if (genClass.getMapEntryKeyFeature().isPropertyMultiLine()) {
          stringBuffer.append(TEXT_11);
          stringBuffer.append(genClass.getSafeUncapName());
          stringBuffer.append(TEXT_12);
          stringBuffer.append(genModel.getNonNLS());
        } else {
          stringBuffer.append(TEXT_13);
          stringBuffer.append(genClass.getSafeUncapName());
          stringBuffer.append(TEXT_14);
          stringBuffer.append(genModel.getNonNLS());
        }
        if (genClass.getMapEntryValueFeature().isPropertyMultiLine()) {
          stringBuffer.append(TEXT_15);
          stringBuffer.append(genClass.getSafeUncapName());
          stringBuffer.append(TEXT_16);
          stringBuffer.append(genModel.getNonNLS());
        } else {
          stringBuffer.append(TEXT_17);
          stringBuffer.append(genClass.getSafeUncapName());
          stringBuffer.append(TEXT_18);
          stringBuffer.append(genModel.getNonNLS());
        }
        stringBuffer.append(TEXT_19);
        stringBuffer.append(genModel.getNonNLS());
      }
    } else if (genClass.getLabelFeature() != null) {
      GenFeature labelFeature = genClass.getLabelFeature();
      if (labelFeature.isPrimitiveType() && !labelFeature.getGenClass().isDynamic()
          && !labelFeature.isSuppressedGetVisibility()) {
        stringBuffer.append(TEXT_20);
        stringBuffer.append(genClass.getImportedInterfaceName());
        stringBuffer.append(genClass.getInterfaceWildTypeArguments());
        stringBuffer.append(TEXT_21);
        stringBuffer.append(genClass.getSafeUncapName());
        stringBuffer.append(TEXT_22);
        stringBuffer.append(genClass.getImportedInterfaceName());
        stringBuffer.append(genClass.getInterfaceWildTypeArguments());
        stringBuffer.append(TEXT_23);
        stringBuffer.append(genClass.getName());
        stringBuffer.append(TEXT_24);
        stringBuffer.append(genClass.getSafeUncapName());
        stringBuffer.append(TEXT_25);
        stringBuffer.append(genClass.getLabelFeature().getGetAccessor());
        stringBuffer.append(TEXT_26);
        stringBuffer.append(genModel.getNonNLS());
        stringBuffer.append(genModel.getNonNLS(2));
        stringBuffer.append(TEXT_27);
      } else {
        if (labelFeature.isStringType() && !labelFeature.getGenClass().isDynamic()
            && !labelFeature.isSuppressedGetVisibility()) {
          if (labelFeature.isPropertyMultiLine()) {
            stringBuffer.append(TEXT_28);
            stringBuffer.append(genClass.getImportedInterfaceName());
            stringBuffer.append(genClass.getInterfaceWildTypeArguments());
            stringBuffer.append(TEXT_29);
            stringBuffer.append(labelFeature.getGetAccessor());
            stringBuffer.append(TEXT_30);
          } else {
            stringBuffer.append(TEXT_31);
            if (labelFeature.getName().equals("name")) {
              stringBuffer.append(TEXT_32);
              stringBuffer.append(genClass.getImportedInterfaceName());
              stringBuffer.append(genClass.getInterfaceWildTypeArguments());
              stringBuffer.append(TEXT_33);
              stringBuffer.append(labelFeature.getGetAccessor());
              stringBuffer.append(TEXT_34);
            } else {
              stringBuffer.append(TEXT_35);
              stringBuffer.append(genModel.getNonNLS());
              stringBuffer.append(TEXT_36);
              stringBuffer.append(genModel.getNonNLS());
              stringBuffer.append(TEXT_37);
              stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
              stringBuffer.append(TEXT_38);
              List<GenClass> superclasses = genClass.getAllBaseGenClasses();
              superclasses.add(genClass);
              for (GenClass cls : superclasses) {
                if ((cls.getEcoreModelElement() instanceof EClass)
                    && ((EClass) cls.getEcoreModelElement()).getName().equals("AbstractTrace")) {
                  stringBuffer.append(TEXT_39);
                  stringBuffer
                      .append(genModel.getImportedName("org.polarsys.capella.common.data.modellingcore.AbstractTrace"));
                  stringBuffer.append(TEXT_40);
                } else if ((cls.getEcoreModelElement() instanceof EClass)
                    && ((EClass) cls.getEcoreModelElement()).getName().equals("StateTransition")) {
                  stringBuffer.append(TEXT_41);
                  stringBuffer
                      .append(genModel.getImportedName("org.polarsys.capella.core.data.capellacommon.StateTransition"));
                  stringBuffer.append(TEXT_42);
                } else if ((cls.getEcoreModelElement() instanceof EClass)
                    && ((EClass) cls.getEcoreModelElement()).getName().equals("Involvement")) {
                  stringBuffer.append(TEXT_43);
                  stringBuffer
                      .append(genModel.getImportedName("org.polarsys.capella.core.data.capellacore.Involvement"));
                  stringBuffer.append(TEXT_44);
                } else if ((cls.getEcoreModelElement() instanceof EClass)
                    && ((EClass) cls.getEcoreModelElement()).getName().equals("Generalization")) {
                  stringBuffer.append(TEXT_45);
                  stringBuffer
                      .append(genModel.getImportedName("org.polarsys.capella.core.data.capellacore.Generalization"));
                  stringBuffer.append(TEXT_46);
                } else if ((cls.getEcoreModelElement() instanceof EClass)
                    && ((EClass) cls.getEcoreModelElement()).getName().equals("AbstractCapabilityGeneralization")) {
                  stringBuffer.append(TEXT_47);
                  stringBuffer.append(genModel
                      .getImportedName("org.polarsys.capella.core.data.interaction.AbstractCapabilityGeneralization"));
                  stringBuffer.append(TEXT_48);
                } else if ((cls.getEcoreModelElement() instanceof EClass)
                    && ((EClass) cls.getEcoreModelElement()).getName().equals("InterfaceUse")) {
                  stringBuffer.append(TEXT_49);
                  stringBuffer.append(genModel.getImportedName("org.polarsys.capella.core.data.cs.InterfaceUse"));
                  stringBuffer.append(TEXT_50);
                } else if ((cls.getEcoreModelElement() instanceof EClass)
                    && ((EClass) cls.getEcoreModelElement()).getName().equals("InterfaceImplementation")) {
                  stringBuffer.append(TEXT_51);
                  stringBuffer
                      .append(genModel.getImportedName("org.polarsys.capella.core.data.cs.InterfaceImplementation"));
                  stringBuffer.append(TEXT_52);
                } else if ((cls.getEcoreModelElement() instanceof EClass)
                    && ((EClass) cls.getEcoreModelElement()).getName().equals("CapabilityExploitation")) {
                  stringBuffer.append(TEXT_53);
                  stringBuffer
                      .append(genModel.getImportedName("org.polarsys.capella.core.data.ctx.CapabilityExploitation"));
                  stringBuffer.append(TEXT_54);
                } else if ((cls.getEcoreModelElement() instanceof EClass)
                    && ((EClass) cls.getEcoreModelElement()).getName().equals("CapabilityExploitation")) {
                  stringBuffer.append(TEXT_55);
                  stringBuffer
                      .append(genModel.getImportedName("org.polarsys.capella.core.data.ctx.CapabilityExploitation"));
                  stringBuffer.append(TEXT_56);
                } else if ((cls.getEcoreModelElement() instanceof EClass)
                    && ((EClass) cls.getEcoreModelElement()).getName().equals("AbstractDeployment")) {
                  stringBuffer.append(TEXT_57);
                  stringBuffer.append(genModel.getImportedName("org.polarsys.capella.core.data.cs.AbstractDeployment"));
                  stringBuffer.append(TEXT_58);
                }
              }
              stringBuffer.append(TEXT_59);
              stringBuffer.append(
                  genModel.getImportedName("org.polarsys.capella.common.data.modellingcore.AbstractNamedElement"));
              stringBuffer.append(TEXT_60);
              stringBuffer.append(
                  genModel.getImportedName("org.polarsys.capella.common.data.modellingcore.AbstractNamedElement"));
              stringBuffer.append(TEXT_61);
              stringBuffer.append(genModel.getNonNLS());
              stringBuffer.append(TEXT_62);
              stringBuffer.append(genModel.getNonNLS());
              stringBuffer.append(genModel.getNonNLS(2));
              stringBuffer.append(TEXT_63);
              stringBuffer.append(genClass.getName());
              stringBuffer.append(TEXT_64);
              stringBuffer.append(genModel.getNonNLS());
              stringBuffer.append(genModel.getNonNLS(2));
              stringBuffer.append(genModel.getNonNLS(3));
              stringBuffer.append(TEXT_65);
            }
            stringBuffer.append(TEXT_66);
          }
          stringBuffer.append(TEXT_67);
        } else {
          stringBuffer.append(TEXT_68);
          if (labelFeature.isSuppressedGetVisibility() || labelFeature.getGenClass().isDynamic()) {
            stringBuffer.append(TEXT_69);
            stringBuffer.append(genModel.getImportedName("java.lang.Object"));
            stringBuffer.append(TEXT_70);
            stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
            stringBuffer.append(TEXT_71);
            stringBuffer.append(labelFeature.getQualifiedFeatureAccessor());
            stringBuffer.append(TEXT_72);
          } else {
            stringBuffer.append(TEXT_73);
            stringBuffer.append(labelFeature.getRawImportedType());
            stringBuffer.append(TEXT_74);
            stringBuffer.append(genClass.getImportedInterfaceName());
            stringBuffer.append(genClass.getInterfaceWildTypeArguments());
            stringBuffer.append(TEXT_75);
            stringBuffer.append(labelFeature.getGetAccessor());
            stringBuffer.append(TEXT_76);
          }
          stringBuffer.append(TEXT_77);
        }
        stringBuffer.append(TEXT_78);
        stringBuffer.append(genClass.getName());
        stringBuffer.append(TEXT_79);
        stringBuffer.append(genModel.getNonNLS());
        stringBuffer.append(genModel.getNonNLS(2));
        stringBuffer.append(genModel.getNonNLS(3));
        stringBuffer.append(TEXT_80);
      }
    } else {
      stringBuffer.append(TEXT_81);
      stringBuffer.append(genClass.getName());
      stringBuffer.append(TEXT_82);
      stringBuffer.append(genModel.getNonNLS());
      stringBuffer.append(genModel.getNonNLS(2));
      stringBuffer.append(genModel.getNonNLS(3));
      stringBuffer.append(TEXT_83);
    }
    stringBuffer.append(TEXT_84);
    InternalPatternContext ictx = (InternalPatternContext) ctx;
    new Node.DataLeaf(ictx.getNode(), getClass(), "doGenerate", stringBuffer.toString());
  }
}