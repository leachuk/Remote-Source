<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="XSD">
      <schema location="../businessCatalog/IProExpenses/ExpenseIProManagerNotes.xsd"/>
      <rootElement name="ExpenseIProManagerNotes" namespace="http://xmlns.oracle.com/bpm/bpmobject/IProExpenses/ExpenseIProManagerNotes"/>
      <param name="expenseIproManagerNotesDO" />
    </source>
    <source type="XSD">
      <schema location="../businessCatalog/IProExpenses/ExpenseIProManagerConfirm.xsd"/>
      <rootElement name="ExpenseIProManagerConfirm" namespace="http://xmlns.oracle.com/bpm/bpmobject/IProExpenses/ExpenseIProManagerConfirm"/>
      <param name="expenseIproManagerConfirmDO" />
    </source>
  </mapSources>
  <mapTargets>
    <target type="XSD">
      <schema location="../xsd/NotifyManagerOfCustomerExecutivesWorkflowTask.xsd"/>
      <rootElement name="task" namespace="http://xmlns.oracle.com/bpel/workflow/task"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 11.1.1.5.0(build 110418.1550.0174) AT [THU JUN 13 12:10:03 EST 2013]. -->
?>
<xsl:stylesheet version="1.0"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:ns2="http://xmlns.oracle.com/bpm/bpmobject/IProExpenses/ExpenseManagerOfCustomerExecutives"
                xmlns:bpel="http://docs.oasis-open.org/wsbpel/2.0/process/executable"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:ns3="http://xmlns.oracle.com/bpel/workflow/common"
                xmlns:evidence="http://xmlns.oracle.com/bpel/workflow/TaskEvidenceService"
                xmlns:bpm="http://xmlns.oracle.com/bpmn20/extensions"
                xmlns:bpmo="http://xmlns.oracle.com/bpm/bpmobject/"
                xmlns:jaxb="http://java.sun.com/xml/ns/jaxb"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator"
                xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                xmlns:med="http://schemas.oracle.com/mediator/xpath"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:xdk="http://schemas.oracle.com/bpel/extension/xpath/function/xdk"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                xmlns:ns0="http://xmlns.oracle.com/bpm/bpmobject/IProExpenses/ExpenseIProManagerNotes"
                xmlns:bpmn="http://schemas.oracle.com/bpm/xpath"
                xmlns:tns="http://xmlns.oracle.com/bpel/workflow/task"
                xmlns:ns1="http://xmlns.oracle.com/bpm/bpmobject/IProExpenses/ExpenseIProManagerConfirm"
                xmlns:ldap="http://schemas.oracle.com/xpath/extension/ldap"
                exclude-result-prefixes="xsi xsl bpmo xsd ns0 ns1 ns2 ns3 evidence jaxb tns xp20 bpws bpel bpm ora socket mhdr oraext dvm hwf med ids xdk xref bpmn ldap">
  <xsl:param name="expenseIproManagerConfirmDO"/>
  <xsl:template match="/">
    <tns:task>
      <tns:payload>
        <ns2:ExpenseManagerOfCustomerExecutives>
          <ns2:amount>
            <xsl:value-of select="$expenseIproManagerConfirmDO/ns1:ExpenseIProManagerConfirm/ns1:amount"/>
          </ns2:amount>
          <ns2:ipro_user>
            <xsl:value-of select="$expenseIproManagerConfirmDO/ns1:ExpenseIProManagerConfirm/ns1:ipro_user"/>
          </ns2:ipro_user>
          <ns2:date_incured>
            <xsl:value-of select="$expenseIproManagerConfirmDO/ns1:ExpenseIProManagerConfirm/ns1:date_incured"/>
          </ns2:date_incured>
          <ns2:notes>
            <xsl:value-of select="/ns0:ExpenseIProManagerNotes/ns0:notes"/>
          </ns2:notes>
        </ns2:ExpenseManagerOfCustomerExecutives>
      </tns:payload>
    </tns:task>
  </xsl:template>
</xsl:stylesheet>